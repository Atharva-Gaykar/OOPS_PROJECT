
---


## 🧠 **Project Overview**

This project integrates a **Java-based CLI/UI client** with a **Python FastAPI backend** to create an intelligent **food search engine**.
It supports both **text-based** and **image-based** food retrieval using advanced **AI models** — including **CLIP**, **LangChain**, **Cohere**, and **Google Gemini** — for semantic understanding and similarity matching.

Users can:

* Search dishes via text or image input
* Retrieve detailed recipes and nutrition facts from a local JSON database
* Run a local calorie calculator implemented entirely in Java (OOP-based)

---



## 🖥️ **Languages Used**

1. **Java** → Client-side application

   * Handles user input (text or image)
   * Sends HTTP requests to FastAPI API
   * Parses and displays JSON responses
   * Loads and displays food data from JSON files

2. **Python** → Backend (FastAPI server)

   * Hosts REST API endpoints
   * Runs ML models for image/text similarity (CLIP)
   * Uses LangChain retrievers and Cohere/Gemini models
   * Integrates ChromaDB for vector storage and retrieval



---

## ☕ **Java Libraries Used**

| Library                               | Purpose                                       |
| ------------------------------------- | --------------------------------------------- |
| **Gson (com.google.gson)**            | JSON serialization/deserialization            |
| **java.io.***                         | File handling, reading JSON, and HTTP streams |
| **java.net.***                        | HTTP request handling (HttpURLConnection)     |
| **java.util.***                       | Lists, Scanners, and Collections              |
| **java.nio.charset.StandardCharsets** | Character encoding for network I/O            |
| **java.util.stream.Collectors**       | Stream API for reading input/output streams   |

---

## 🐍 **Python Libraries Used**

| Library                          | Purpose                                             |
| -------------------------------- | --------------------------------------------------- |
| **torch**                        | Deep learning backend for CLIP model                |
| **transformers (Hugging Face)**  | For CLIP model and text/image embeddings            |
| **PIL (Pillow)**                 | Image handling and processing                       |
| **chromadb**                     | Vector database for semantic search                 |
| **fastapi**                      | Building and serving REST API                       |
| **dotenv (python-dotenv)**       | Loading environment variables                       |
| **cohere**                       | Language model for text generation/retrieval        |
| **google-generativeai (Gemini)** | Google Generative AI integration                    |
| **langchain-google-genai**       | LangChain integration for Gemini models             |
| **langchain-huggingface**        | LangChain integration for Hugging Face embeddings   |
| **langchain-community**          | Chroma and BM25 retrievers                          |
| **langchain-core**               | Prompt templates, output parsers, document handling |
| **io / os**                      | File and path management                            |
| **json**                         | JSON serialization/deserialization                  |
| **typing**                       | Type hinting and structure                          |
| **retriever_module (custom)**    | Your custom retriever initialization and logic      |

---

## 🌐 **Data Formats Used**

| Format                | Usage                                                    |
| --------------------- | -------------------------------------------------------- |
| **JSON**              | Used for dish data, API communication, and configuration |
| **Numeric IDs**       | API responses return numeric IDs for dish retrieval      |
| **Text/Image Inputs** | Queries are sent as plain text or image paths to FastAPI |

---



## ⚙️ **Workflow Summary**

### **1. User Interaction (Java - Front Layer)**

* The Java console application serves as the **user interface**.
* The user selects one of three options:

  1. **Text-based Query**
  2. **Image-based Query**
  3. **Calorie Calculator (Local Only)**
* Depending on the selection, Java either:

  * Sends the query to the FastAPI server (for Text/Image).
  * Or runs the calorie calculator locally without any API call.

---

### **2. Text-based Query Flow**

* The user inputs a **text query** (e.g., “Paneer Butter Masala”).
* The query is **sanitized** in Java to remove unwanted characters.
* Java sends the sanitized text to the **FastAPI backend**.
* FastAPI:

  * Uses **CLIP** + **LangChain retrievers** to find the most relevant dish.
  * Returns a **numeric dish ID** as a JSON response (e.g., `{ "id": 42 }`).
* Java parses the JSON using **Gson** and extracts the dish ID.
* Using that ID, Java loads the `OOPS_DATA.json` file and prints:

  * Dish name
  * Course, diet type
  * Ingredients
  * Flavor profile
  * Nutrition facts
  * Recipe

---

### **3. Image-based Query Flow**

* The user inputs the **path to an image file**.
* Java sends this image path to the FastAPI server.
* FastAPI:

  * Uses the **CLIP model** to encode the image and match it with stored text/image embeddings.
  * Returns a **numeric dish ID** (in JSON format).
* Java:

  * Parses the returned ID using Gson.
  * Finds the matching dish in `OOPS_DATA.json`.
  * Displays all details as in the text query flow.

---

### **4. Calorie Calculator Flow (Local Only)**

* If the user selects **option 3**, Java executes:

  ```java
  Calorie.main(new String[]{});
  ```
* This feature runs **entirely within the OOPS (Java) project**, without contacting the FastAPI backend.
* The calculator computes and displays calorie or nutrition data locally.

---

### **5. Data Storage and Integration**

* **Dataset:** `OOPS_DATA.json` — contains all dish metadata, recipes, and nutrition facts.
* **Integration:**

  * Java ↔ FastAPI communication happens via **HTTP requests** returning simple JSON (`{ "id": <int> }`).
  * Data parsing and display are handled entirely on the Java side using **Gson**.

---

### **6. Technology Stack Summary**

* **Frontend (CLI-based):** Java (OOP design)
* **Backend (API):** Python (FastAPI)
* **AI Modules:** CLIP (for text/image embedding), LangChain retrievers (BM25 + Chroma + Cohere/GenAI)
* **Data Format:** JSON
* **Libraries Used:**

  * *Java:* Gson, Scanner, FileReader
  * *Python:* FastAPI, CLIP, Torch, PIL, LangChain, ChromaDB, Cohere, Google GenAI, dotenv

---



## 📘 **Optional (Future Expansion)**

* **HTML/CSS/JS Frontend**: You can replace the Java console interface with a browser-based UI that calls the same FastAPI endpoints.
* **Docker Deployment**: Containerize FastAPI for cloud deployment (e.g., Render, Railway, AWS EC2).
* **Audio Model** integration.
* **Database Integration**: Replace local JSON with a real DB (MySQL or MongoDB).

---


