# Step-by-Step Guide: Setting up Miniconda for Data Science & Machine Learning (Windows)

## 1. Download Miniconda

1. Open your web browser.
2. Search for **"Miniconda download"** or go directly to: [Miniconda Download Page](https://docs.conda.io/en/latest/miniconda.html)
3. On the download page, choose the **Windows** version.
   - Select the latest **Python 3.x 64-bit** installer (recommended, e.g. "Miniconda3 Windows 64-bit Python 3.x").
4. Download the `.exe` installer to your computer.

---

## 2. Install Miniconda

1. Locate the downloaded installer in your **Downloads** folder.
2. Double-click the installer to run it.
3. Go through the setup steps:
    - Click **Next**.
    - Agree to the license.
    - Select **Just Me** (recommended for most users).
    - Choose the installation directory (default is fine, e.g. `C:\Users\<YourName>\Miniconda3`).
    - Keep the default settings for advanced options.
    - Click **Install** and wait for the process to complete.
    - Click **Finish** when done.

---

## 3. Test Miniconda Installation

1. Click the **Start** menu and search for **"Anaconda Prompt"** or **"Miniconda Prompt"**.
2. Open the prompt. You should see a command window that starts with `(base)`.

---

## 4. Create a Project Folder

1. In the Anaconda/Miniconda Prompt, navigate to your Desktop:
    ```sh
    cd Desktop
    ```
2. Create a new project folder (e.g., "sample_project_1"):
    ```sh
    mkdir sample_project_1
    ```
3. Change into your new project folder:
    ```sh
    cd sample_project_1
    ```

---

## 5. Create a Custom Conda Environment in the Project Folder

1. Run the following command to create an environment named `env` in your project folder with essential packages:
    ```sh
    conda create --prefix ./env pandas numpy matplotlib scikit-learn
    ```
2. When prompted, type `y` and hit **Enter** to proceed with the installation.

---

## 6. Activate Your Environment

1. Activate the environment with:
    ```sh
    conda activate ./env
    ```
2. The command prompt should now show the path to your active environment.

---

## 7. Install Jupyter Notebook in the Environment

1. With your environment activated, run:
    ```sh
    conda install jupyter
    ```
2. When prompted, type `y` and hit **Enter**.

---

## 8. Launch Jupyter Notebook

1. Still in your project folder and with your environment activated, launch Jupyter Notebook:
    ```sh
    jupyter notebook
    ```
2. Your web browser will open the Jupyter interface in your project folder.

---

## 9. Test Your Environment in Jupyter Notebook

1. Start a new Python 3 notebook.
2. In a new cell, type and run:
    ```python
    import pandas as pd
    import numpy as np
    import matplotlib.pyplot as plt
    import sklearn
    ```
3. If there are no errors, your environment is set up correctly.

---

## 10. Closing and Managing Your Environment

- **To stop Jupyter Notebook:**  
  Go to the Anaconda/Miniconda Prompt where it is running and press `Ctrl+C`.
- **To deactivate the environment:**  
    ```sh
    conda deactivate
    ```
- **To reactivate later:**  
    ```sh
    conda activate ./env
    ```
- **To launch Jupyter again:**  
  Ensure you are in your project folder and your environment is activated, then run:
    ```sh
    jupyter notebook
    ```

---

> **Tip:** Repeat steps 4–10 for each new project to create isolated, reproducible environments.