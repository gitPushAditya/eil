# Saving, Exiting, and Sharing Jupyter Notebooks

Jupyter Notebooks are not only a fantastic all-in-one workspace for data science and machine learning—they're also designed for easy saving, exiting, and sharing with others (including via GitHub)!

---

## Saving Your Work

- **Auto-save:** Jupyter Notebooks auto-save your work frequently.
- **Manual Save:**
  - Click the **Save** icon in the toolbar, or
  - Use keyboard shortcuts:  
    - **Windows/Linux:** `Ctrl + S`  
    - **Mac:** `Command + S`
  - In command mode (blue border, no pencil), you can also press `S` to save.

> **Tip:** Get into the habit of saving often to avoid losing progress.

---

## Exiting and Shutting Down

1. **Shut Down a Notebook:**
   - From the Jupyter Dashboard, click the **Running** tab.
   - Click **Shutdown** next to the notebook you wish to close.
   - Or, close the notebook tab in your browser and stop the server in your terminal.

2. **Stop the Jupyter Server (from Terminal):**
   - In the terminal where Jupyter is running, press `Ctrl + C`.
   - You'll be prompted with `Shutdown this notebook server (y/[n])?`
   - Type `y` and press **Enter**.

3. **Deactivate Your Environment:**
   - In the terminal, type:
     ```sh
     conda deactivate
     ```
   - This returns you to your base environment.

---

## Restarting Work the Next Day

1. **Open Terminal/Anaconda Prompt**
2. **Navigate to Your Project Folder:**
   ```sh
   cd Desktop\ML_course\sample_project
   ```
3. **Activate Your Environment:**
   ```sh
   conda activate ./env
   ```
4. **Launch Jupyter Notebook:**
   ```sh
   jupyter notebook
   ```
5. **Open Your Notebook and Resume Work:**
   - Click your `.ipynb` file in the dashboard.
   - **Remember:** Kernel state is reset!  
     - All variable/state is cleared.
     - Rerun your code cells (top to bottom) to restore your workspace.

---

## Sharing Your Notebook

- **Send the `.ipynb` file:**  
  - You can share the notebook file directly with teammates.
  - They need the same data files and environment to run everything smoothly.
- **Share via GitHub:**  
  - Upload your notebook and data files to a GitHub repository for version control and collaboration.
- **Reproducibility:**  
  - Sharing the environment (`env` folder, or better, an `environment.yml` file) ensures others can recreate your setup.

---

## Key Things to Remember

- Jupyter Notebooks **auto-save**, but manual saves are good practice.
- Always **shut down** notebooks and the Jupyter server before closing your terminal or computer.
- **Kernels reset** on restart—rerun all code cells when you resume work.
- Notebooks, data, and environments can be **shared** for collaboration and reproducibility.
- Jupyter Notebooks are the “all-in-one workspace” for code, documentation, data, and results.

---

> **Up Next:**  
> You’ll dive deeper into this workflow—loading data, analyzing it, and using various data science and machine learning tools directly inside your Jupyter Notebook!