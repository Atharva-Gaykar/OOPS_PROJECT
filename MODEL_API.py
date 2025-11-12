import json
import torch
from transformers import CLIPProcessor, CLIPModel
from torch.nn.functional import normalize
from PIL import Image
import chromadb
from fastapi import FastAPI, UploadFile, Form
from fastapi.responses import JSONResponse
import os
import io
from retriever_module import initialize_retriever, return_docs, combine_function


model_name = "openai/clip-vit-base-patch32"
processor = CLIPProcessor.from_pretrained(model_name)
model = CLIPModel.from_pretrained(model_name)

# Load your fine-tuned checkpoint if available
checkpoint = torch.load("ImageModel.pth", map_location="cpu")
if "model_state_dict" in checkpoint:
    state_dict = checkpoint["model_state_dict"]
else:
    state_dict = checkpoint
model.load_state_dict(state_dict, strict=False)

device = "cuda" if torch.cuda.is_available() else "cpu"
model.to(device).eval()
print(f" CLIP model loaded on {device}")

retriever, query_expansion_chain = initialize_retriever("OOPS_PROJECT_DATA.json", "./Embedding_oops_db")

client = chromadb.PersistentClient(path="./OOPS_CLIP_DB")
collection = client.get_collection("oops_clip_collection")
print(f"Loaded Chroma collection with {collection.count()} documents")

app = FastAPI(title="CLIP Retrieval API", version="1.0")


# def encode_text(query: str):
#     with torch.no_grad():
#         inputs = processor(text=[query], return_tensors="pt", padding=True, truncation=True).to(device)
#         query_emb = model.get_text_features(**inputs)
#         query_emb = normalize(query_emb, p=2, dim=-1)
#     return query_emb.cpu().tolist()[0]


def encode_image_from_path(image_path: str):
    """Encode image from given file path"""
    image = Image.open(image_path).convert("RGB")
    with torch.no_grad():
        inputs = processor(images=image, return_tensors="pt").to(device)
        image_emb = model.get_image_features(**inputs)
        image_emb = normalize(image_emb, p=2, dim=-1)
    return image_emb.cpu().tolist()[0]

def query_chroma_image(embedding, top_k=1):
    results = collection.query(
        query_embeddings=[embedding],
        n_results=top_k
    )
    metas = results["metadatas"][0]
    return metas[0]["id"] if metas and "id" in metas[0] else None


# -----------------------------------
# API Endpoints
# -----------------------------------


@app.post("/query/text")
async def query_text(query: str = Form(...), top_k: int = Form(3)):
    """
    Search food items using text query.
    Uses LangChain retrievers with query expansion.
    """
    try:
        output = combine_function(query, top_k)
        food_id = output[0]["id"]

        if food_id is None:
            return JSONResponse(content={"message": "No matching documents found."}, status_code=404)

        return JSONResponse(content={"id": food_id})
    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)



@app.post("/query/image")
async def query_image_path(image_path: str = Form(...), top_k: int = Form(3)):
    """Search using local image path and return only the food ID."""
    try:
        if not os.path.exists(image_path):
            return JSONResponse(content={"error": "File not found", "path": image_path}, status_code=400)

        embedding = encode_image_from_path(image_path)
        food_id = query_chroma_image(embedding, top_k)

        if food_id is None:
            return JSONResponse(content={"message": "No matching documents found."}, status_code=404)

        return JSONResponse(content={"id": food_id})
    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)


if __name__ == "__main__":
    import uvicorn
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    print(f"\n Chatbot using device: {device}\n")
    uvicorn.run(app,host="127.0.0.1",port=8500)