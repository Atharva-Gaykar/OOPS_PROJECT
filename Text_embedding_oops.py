import os
import json
import cohere
from langchain_community.vectorstores import Chroma
from langchain_core.documents import Document
from langchain_huggingface import HuggingFaceEmbeddings
import torch
print(" Loading model and processor...")

device = "cuda" if torch.cuda.is_available() else "cpu"
print(f" model loaded on {device}")

embeddings = HuggingFaceEmbeddings(
    model_name="sentence-transformers/multi-qa-MiniLM-L6-cos-v1",
    model_kwargs={"device": "cuda"},
    encode_kwargs={"batch_size": 4}
)


with open("OOPS_PROJECT_DATA.json", "r", encoding="utf-8") as f:
    data = json.load(f)
Data_part = [Document(page_content=d["page_content"], metadata=d["metadata"]) for d in data]




vectorstore1 = Chroma(
    persist_directory="./Embedding_oops_db",
    embedding_function=embeddings
)

 
vectorstore1.add_documents(Data_part)
vectorstore1.persist()
print("✅ Stored all embeddings in Chroma successfully!")
