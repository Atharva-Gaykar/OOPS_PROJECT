# 👥 Project Contributions

This project integrates a **Java-based CLI client** with a **Python FastAPI backend** powered by a **fine-tuned CLIP model** for multimodal food recognition and retrieval.  
Each team member contributed to distinct aspects of the system, as detailed below.

---

## 🧠 **Atharva Gaykar**
**Role:** Deep Learning & Backend Integration  
- Fine-tuned the **CLIP model** specifically for food image–text embeddings.  
- Developed the **FastAPI server** (`MODEL_API.py`) to serve model predictions via REST API.  
- Implemented **retrievers for text and image embeddings** (`Text_embeddings_oops.py`, `retrieval_module.py`, `ClipEmbeddingsDatabaseCreation.py`).  
- Built **API handling logic in Java** for query interaction (`ClipImageTestQuery.java`, `CliptestQuery.java`, `Calorie.java`).  
- Integrated the fine-tuned model with the Java client for seamless **cross-language inference**.

---

## 🧮 **Pratham**
**Role:** Feature Development & Data Preparation  
- Developed the **Calorific Food Quantifier** feature (`Calorie.java`) to compute and display nutritional metrics for dishes.  
- Collected and curated the dataset used in model training and testing.  
- Created and maintained the project’s structured **data source file** (`OOPS_DATA.json`).  
- Contributed to the **final report** and documentation.

---

## 🧾 **Aditya**
**Role:** Core Functionality & Output Formatting  
- Implemented the **JSON parsing and data extraction logic** (`Main.java`).  
- Designed the **output formatting system** for structured and user-friendly display in the CLI interface.  
- Ensured accurate mapping between the **retrieved dish ID** and its details in `OOPS_DATA.json`.  
- Contributed to system testing and refinement.

---

## 🔗 **Anant**
**Role:** Integration & Input Handling  
- Implemented **interconnections between Java modules** for unified execution flow.  
- Developed **input handling and request routing logic** in (`Main.java`, `ClipImageTestQuery.java`, `CliptestQuery.java`, `Calorie.java`).  
- Assisted in **linking Java client with FastAPI server** through HTTP POST communication.  
- Contributed to debugging and optimizing user interaction flow.

---

### 🏁 **Summary**
| Member | Key Focus | Primary Files |
|---------|------------|----------------|
| **Atharva** | CLIP Fine-tuning, FastAPI Backend, Model API | `MODEL_API.py`, `ClipEmbeddingsDatabaseCreation.py`, `retrieval_module.py`, `ClipImageTestQuery.java`, `CliptestQuery.java` |
| **Pratham** | Calorie Computation, Data Collection, Report | `Calorie.java`, `OOPS_DATA.json` |
| **Aditya** | JSON Parsing, Output Formatting | `Main.java` |
| **Anant** | File Integration, Input Handling, Communication Flow | `Main.java`, `ClipImageTestQuery.java`, `CliptestQuery.java`, `Calorie.java` |

---


