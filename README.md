# 🍽️ AI-Based Food Recognition & Calorie Analysis System

An integrated AI-powered food search engine that combines a fine-tuned CLIP model (for image–text similarity) with a Java-based client interface. Users can query by text or image, and receive detailed dish information — including ingredients, nutrition facts, and recipes — all retrieved from a structured dataset.

## 🚀 Project Overview

This project bridges Deep Learning (Python) and Software Engineering (Java) through a modular client-server design:

- **Frontend / CLI Client (Java)**: Handles user inputs, displays formatted results, and communicates with the backend through HTTP requests.
- **Backend / FastAPI (Python)**: Hosts a fine-tuned CLIP model and LangChain retrievers for multimodal food data search. Responds to Java queries with the matching dish `id`.

## ⚙️ Key Features

✅ **Text-based Food Query**: Input a textual description (e.g., "spicy paneer curry") or recipe-based query (e.g., "How to make samosa?", "what is recipe of idli?") to fetch the closest dish.

✅ **Image-based Recognition**: Upload or provide a path to a food image — the model predicts its matching dish.

✅ **Calorie & Nutrition Analyzer**: Compute nutritional composition (Calorie count, macronutrients).

✅ **Seamless Cross-Language Integration**: Java communicates with a Python FastAPI server using JSON over HTTP.

✅ **Fine-tuned CLIP Model**: Optimized for food domain embeddings.

## 📋 Prerequisites

### Python Requirements
- Python 3.8 or higher
- pip package manager

### Java Requirements
- Java JDK 11 or higher
- Java IDE (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/atharva-Gaykar/OOPS_PROJECT.git
cd OOPS_PROJECT
```

### 2. Python Backend Setup

#### Install Dependencies
```bash
# Create a virtual environment (recommended)
python -m venv venv

# Activate virtual environment
# On Windows:
venv\Scripts\activate
# On macOS/Linux:
source venv/bin/activate

# Install required packages
pip install -r requirements.txt
```

#### Download Fine-tuned CLIP Model

Place your fine-tuned CLIP model files in the appropriate directory (update path in `MODEL_API.py` if needed).

### 3. Java Frontend Setup

#### Compile Java Files
```bash
# Navigate to Java source directory
cd src

# Compile all Java files
javac *.java

# Or if using package structure:
javac -d bin src/**/*.java
```

Alternatively, open the project in your Java IDE and build the project.

## 🚀 Running the Application

### Step 1: Start the Python Backend Server
```bash
# Make sure you're in the project root directory with virtual environment activated
cd backend  # or wherever MODEL_API.py is located

# Start the FastAPI server
uvicorn MODEL_API:app --reload --host 0.0.0.0 --port 8000
```

The server will start at `http://localhost:8000`

You should see output like:
```
INFO:     Uvicorn running on http://0.0.0.0:8000 (Press CTRL+C to quit)
INFO:     Started reloader process
INFO:     Started server process
INFO:     Waiting for application startup.
INFO:     Application startup complete.
```

### Step 2: Run the Java Client

Open a new terminal window (keep the Python server running):
```bash
# Navigate to the directory containing compiled Java classes
cd src  # or bin if you compiled to a separate directory

# Run the Main class
java Main
```

Or run from your Java IDE by executing the `Main.java` file.

## 📖 Usage Guide

### Text-Based Query

1. Select option for text query when prompted
2. Enter your food description or recipe query
   - Example: "butter chicken"
   - Example: "How to make biryani?"
3. View the dish details, ingredients, and nutritional information

### Image-Based Query

1. Select option for image query when prompted
2. Provide the full path to your food image
   - Example: `/path/to/your/food_image.jpg`
   - Supported formats: JPG, PNG, JPEG
3. View the recognized dish with complete details

### Calorie Analysis

1. After querying a dish, select the calorie analysis option
2. View detailed nutritional breakdown including:
   - Total calories
   - Macronutrient distribution (proteins, carbs, fats)
   - Other nutritional metrics

## 📁 Project Structure (This is not the folder structure we are using as of now)
```
OOPS_PROJECT/
│
├── backend/
│   ├── MODEL_API.py                          # FastAPI server
│   ├── Text_embeddings_oops.py               # Text embedding processing
│   ├── ClipEmbeddingsDatabaseCreation.py     # Database creation
│   └── requirements.txt                       # Python dependencies
│
├── src/
│   ├── Main.java                             # Entry point
│   ├── ClipImageTestQuery.java               # Image query handler
│   ├── CliptestQuery.java                    # Text query handler
│   ├── Calorie.java                          # Calorie API handler
│   ├── CalorieCalculator.java                # Calorie computation logic
│   ├── Dish.java                             # Dish model
│   ├── NutritionFacts.java                   # Nutrition data model
│   └── DisplayDishes.java                    # Output formatting
│
├── data/
│   └── OOPS_DATA.json                        # Food database
│
└── README.md                                 # This file
```

## 🔧 Configuration

### Backend Configuration

Edit `MODEL_API.py` to configure:
- Model path
- Server host and port
- API endpoints

### Frontend Configuration

Update API endpoint URLs in Java files if your backend runs on a different host/port:
- `ClipImageTestQuery.java`
- `CliptestQuery.java`
- `Calorie.java`

Default backend URL: `http://localhost:8000`

## 🐛 Troubleshooting

### Python Backend Issues

**Error: Module not found**
```bash
pip install -r requirements.txt
```

**Error: Port already in use**
```bash
# Use a different port
uvicorn MODEL_API:app --reload --port 8001
# Update the port in Java files accordingly
```

### Java Frontend Issues

**Error: Connection refused**
- Ensure the Python backend server is running
- Check if the correct URL and port are configured

**Error: Class not found**
- Ensure all Java files are compiled
- Check your classpath settings

**Error: Unable to parse JSON**
- Verify OOPS_DATA.json is in the correct location
- Check JSON file formatting

## 👥 Team Members

- **Anant Srivastava** - BT24CSH034
- **Atharva Gaykar** - BT24CSH047
- **Aditya Singh** - BT24CSH051
- **Pratham Dwivedi** - BT24CSH063

## 🎓 Academic Information

**Course**: Object-Oriented Programming Mini Project  
**Instructor**: Mrs. Puja Gudadhe (Adjunct Assistant Professor - Indian Institute of Information Technology, Nagpur)

## 📝 License

This project is developed for academic purposes.

## 🤝 Contributing

This is an academic project. For suggestions or issues, please open an issue on GitHub.

## 📧 Contact

For queries, reach out through GitHub issues or contact any team member.

---

**Note**: Ensure both the Python backend and Java frontend are running simultaneously for the application to work properly.
