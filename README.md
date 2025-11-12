# 🍽️ AI-Based Food Recognition & Calorie Analysis System

An integrated **AI-powered food search engine** that combines a **fine-tuned CLIP model** (for image–text similarity) with a **Java-based client interface**.  
Users can query by **text** or **image**, and receive detailed **dish information** — including ingredients, nutrition facts, and recipes — all retrieved from a structured dataset.  
Additionally, a **Calorific Food Quantifier** estimates total nutritional values.

---

## 🚀 **Project Overview**

This project bridges **Deep Learning (Python)** and **Software Engineering (Java)** through a modular client-server design:

- **Frontend / CLI Client (Java):**  
  Handles user inputs, displays formatted results, and communicates with the backend through HTTP requests.
  
- **Backend / FastAPI (Python):**  
  Hosts a fine-tuned **CLIP model** and **LangChain retrievers** for multimodal food data search.  
  Responds to Java queries with the matching dish `id`.

---

## ⚙️ **Key Features**

✅ **Text-based Food Query:**  
Input a textual description (e.g., “spicy paneer curry”) or recipe based query (e.g.,"How to make samosa?","what is recipe of idli?")  to fetch the closest dish.

✅ **Image-based Recognition:**  
Upload or provide a path to a food image — the model predicts its matching dish.

✅ **Calorie & Nutrition Analyzer:**  
Compute nutritional composition (Calorie count).

✅ **Seamless Cross-Language Integration:**  
Java communicates with a Python FastAPI server using JSON over HTTP.

✅ **Fine-tuned CLIP Model:**  
Optimized for food domain embeddings .

SYSTEM ARCHITECTURE
<img width="1428" height="370" alt="image" src="https://github.com/user-attachments/assets/39c85460-312c-4f27-8b24-23d0c2e17600" />





