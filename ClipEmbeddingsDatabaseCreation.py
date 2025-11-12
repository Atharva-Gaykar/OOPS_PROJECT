import torch
from transformers import CLIPProcessor, CLIPModel
from langchain.schema import Document
from torch.nn.functional import normalize
import json
from tqdm import tqdm
import chromadb


model_name = "openai/clip-vit-base-patch32"
processor = CLIPProcessor.from_pretrained(model_name)
model = CLIPModel.from_pretrained(model_name)


checkpoint = torch.load("ImageModel.pth", map_location="cpu")
if "model_state_dict" in checkpoint:
    state_dict = checkpoint["model_state_dict"]
else:
    state_dict = checkpoint
model.load_state_dict(state_dict, strict=False)

device = "cuda" if torch.cuda.is_available() else "cpu"
model.to(device).eval()
print(f" CLIP model loaded on {device}")


with open("OOPS_PROJECT_DATA.json", "r", encoding="utf-8") as f:
    data = json.load(f)
Data = [Document(page_content=d["page_content"], metadata=d["metadata"]) for d in data]


def generate_text_embeddings(docs, model, processor, device, batch_size=16):
    model.eval()
    all_embeddings = []
    texts = [d.page_content for d in docs]

    with torch.no_grad():
        for i in tqdm(range(0, len(texts), batch_size), desc="Encoding texts"):
            batch = texts[i:i + batch_size]
            inputs = processor(text=batch, return_tensors="pt", padding=True, truncation=True).to(device)
            text_emb = model.get_text_features(**inputs)
            text_emb = normalize(text_emb, p=2, dim=-1)
            all_embeddings.append(text_emb.cpu())

    return torch.cat(all_embeddings, dim=0)

embeddings_tensor = generate_text_embeddings(Data, model, processor, device)
embedding_list = embeddings_tensor.tolist()
print(f" Generated embeddings for {len(embedding_list)} documents")

# -------------------------------
# Store in Chroma (manual embeddings)
# -------------------------------
client = chromadb.PersistentClient(path="./OOPS_CLIP_DB")
collection = client.get_or_create_collection(name="oops_clip_collection")

texts = [doc.page_content for doc in Data]
metadatas = [doc.metadata for doc in Data]

# Add embeddings to Chroma
collection.add(
    ids=[f"doc_{i}" for i in range(len(texts))],
    documents=texts,
    embeddings=embedding_list,
    metadatas=metadatas
)
print(" Stored all embeddings in Chroma successfully!")





