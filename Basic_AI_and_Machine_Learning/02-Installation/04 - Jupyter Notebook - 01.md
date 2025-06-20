# Introduction to Jupyter Notebooks: Your Data Science Workspace

Welcome to the hands-on section! In this lesson, you'll get familiar with one of the most important tools in machine learning and data science: **Jupyter Notebooks**.

---

## What is a Jupyter Notebook?

A Jupyter Notebook is an interactive workspace for writing and running code, documenting your work, and visualizing results—all in one place. It's widely used for data analysis, machine learning, and exploratory programming.

---

## Typical Workflow

1. **Create a Project Folder:**  
   Organize your work by keeping each project in its own folder.

2. **Set Up and Activate Your Environment:**  
   Use `conda activate <path-to-env>` to activate your custom environment, so your tools are available.

3. **Launch Jupyter Notebook:**  
   With your environment activated and inside your project folder, run:
   ```sh
   jupyter notebook
   ```
   This opens the Jupyter dashboard in your web browser, showing the files in your project folder.

---

## Jupyter Dashboard Overview

- **File Browser:** See all files and folders in your current directory.
- **Upload Button:** Add data files (e.g. CSVs, images) to your project.
- **New Button:** Create new notebooks, text files, folders, or terminals.
- **Tabs:**  
  - **Running:** Lists active notebooks and terminals.  
  - **Notebooks:** Shows notebooks currently open.  
  - **Clusters:** Not commonly used for beginner projects.

---

## Creating and Working with Notebooks

- Click **New → Python 3** to create a new notebook.
- The notebook appears as a new file with a `.ipynb` extension (stands for "IPython Notebook").
- To rename your notebook, click on the title at the top and enter a new name.

---

## Jupyter Notebook Interface

- **Title:** Name of your notebook.
- **Menu Bar:** File, Edit, View, etc.—controls for your notebook.
- **Toolbar:** Quick access to cell controls (run, stop, add, etc.).
- **Cell:** The fundamental unit of a notebook.  
  - **Code Cell:** Run Python code here.
  - **Markdown Cell:** Write formatted text here.

---

## Example: Running Code

1. Click on a cell and type:
   ```python
   print("Hello, world!")
   ```
2. Press `Shift + Enter` to run the cell. The output appears below the cell.

3. To write text, change the cell type to **Markdown** (from the toolbar or menu), then type your text and press `Shift + Enter`.

---

## Uploading Files

- Use the **Upload** button to add data files (like `.csv` or images) to your project.
- These files appear both in the Jupyter dashboard and your project folder.

---

## Saving and Managing Notebooks

- Click the **Save** icon or use `Ctrl+S`/`Cmd+S` to save your work.
- Notebooks are saved as `.ipynb` files in your project folder.

---

## Stopping and Restarting

- To stop the notebook, return to your command prompt and press `Ctrl+C`.
- You can reopen your notebook any time by running `jupyter notebook` in your environment and clicking the notebook file.

---

## Key Takeaways

- **Jupyter Notebooks** are your main workbench for data science and ML projects.
- Code runs interactively—see output immediately, experiment quickly.
- Organize each project in its own folder with its own environment.
- Upload data and manage files directly from the dashboard.

---

> In the next lesson, you'll learn more about the different things you can do with cells and how to get the most out of your Jupyter notebooks!