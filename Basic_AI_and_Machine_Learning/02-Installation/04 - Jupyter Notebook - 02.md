# Getting Hands-On with Jupyter Notebooks: Cells, Keyboard Shortcuts, and Practical Usage

Welcome! Now that you have your Jupyter Notebook running, it's time to explore what you can actually do inside it. This guide will help you master key features and keyboard shortcuts for efficient data science and machine learning workflow.

---

## 1. **Running Code Cells**

- **Write code in a cell** (e.g., `print("Hello world. I'm learning about Jupyter Notebooks!")`)
- **Run the cell:**  
  - Press `Shift + Enter`  
  - The output appears below, and the cell’s execution number increases (e.g., `In [13]`).
- **Cell numbering:**  
  - Tracks order of execution, useful for debugging and understanding workflow.

---

## 2. **Cell Types: Code and Markdown**

- **Code cells:** Run Python code (or other supported languages).
- **Markdown cells:** Write formatted text for documentation, project notes, and explanations.

### **Switching Between Cell Types (Keyboard-First!):**
- **Edit mode:** Green border/pencil icon (press `Enter` to enter).
- **Command mode:** Blue border/no pencil (press `Esc` to enter).
- **Change cell type:**
  - In command mode:
    - `M` = Markdown
    - `Y` = Code
- **Run cell:** `Shift + Enter`

---

## 3. **Efficient Cell Management (Keyboard Shortcuts)**

- **Insert cell below:** `Esc`, then `B`
- **Insert cell above:** `Esc`, then `A`
- **Delete cell:** `Esc`, then press `D` twice (`D D`)
- **Switch edit/command modes:** `Enter` / `Esc`

---

## 4. **Mixing Code and Documentation**

Example:  
```markdown
# Heart Disease Project

This project is about classifying whether or not a patient has heart disease.
```
- Use Markdown cells for **titles, explanations, and notes**.
- Use Code cells for **data loading, analysis, and modeling**.

---

## 5. **Importing and Using Data**

- **Import pandas:**  
  ```python
  import pandas as pd
  ```
- **Read a CSV file:**  
  ```python
  df = pd.read_csv("heart_disease.csv")  # Use tab to autocomplete filename
  ```
- **View first few rows:**  
  ```python
  df.head()      # First 5 rows
  df.head(10)    # First 10 rows
  ```

---

## 6. **Displaying Data and Visualizations**

- **Matplotlib for plotting:**
  ```python
  import matplotlib.pyplot as plt
  df['target'].value_counts().plot(kind='bar')
  plt.show()
  ```
- **If a plot doesn't show, make sure to (re-)import matplotlib and rerun the plot cell.**

---

## 7. **Adding Images and Rich Content**

- **Embed an image in Markdown:**
  ```markdown
  ![Framework](6-step-ml-framework.png)
  ```

---

## 8. **Notebook Organization Example**

```markdown
# 1. Problem Definition
Predict heart disease.

# 2. Data
*Description of dataset, preview of data table, etc.*

# 3. Evaluation Metric
...

# 4. Features
...

# 5. Modeling
...

# 6. Experimentation
...
```

---

## 9. **Running Terminal Commands**

- **Use `!` to run shell commands from a code cell:**  
  ```python
  !dir         # On Windows, lists files/folders
  !ls          # On Mac/Linux, lists files/folders
  ```
- **Great for checking files in your project directory directly from within the notebook.**

---

## 10. **Key Takeaways**

- Jupyter Notebooks let you combine code, text, visualizations, and shell commands in one place.
- Learn keyboard shortcuts early for maximum productivity.
- Cells execute in the order you run them, not just from top to bottom.
- Keep project documentation and code together using Markdown and code cells.
- You can upload data/images directly in the Jupyter dashboard and access them from your notebook.

---

> **Practice Tip:**  
> Try mixing markdown cells (with project descriptions, headers, notes) and code cells (data, plots, models) as you build your projects!