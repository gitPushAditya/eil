# Step-by-Step Guide: Setting up Anaconda for Data Science & Machine Learning (Windows)

## 1. Download Anaconda

1. Open your web browser.
2. Go to the [official Anaconda Distribution download page](https://www.anaconda.com/products/distribution).
3. Click **Download** for Windows (choose the latest Python 3.x version, 64-bit recommended).
4. Save the `.exe` installer to your computer.

---

## 2. Install Anaconda

1. Locate the downloaded Anaconda installer in your **Downloads** folder.
2. Double-click the installer to run it.
3. Go through the setup steps:
    - Click **Next**.
    - Agree to the license.
    - Select **Just Me** (recommended).
    - Choose the installation directory (default is fine, e.g. `C:\Users\<YourName>\Anaconda3`).
    - Keep the default settings for advanced options.
    - Click **Install** and wait for the process to complete.
    - Click **Finish** when done.

---

## 3. Test Anaconda Installation

1. Click the **Start** menu and search for **"Anaconda Prompt"**.
2. Open the Anaconda Prompt. You should see a command window that starts with `(base)`.

---

## 4. Create a Project Folder

1. In the Anaconda Prompt, navigate to your Desktop:
    ```sh
    cd Desktop
    ```
2. Create a new project folder (e.g., "my_anaconda_project"):
    ```sh
    mkdir my_anaconda_project
    ```
3. Change into your new project folder:
    ```sh
    cd my_anaconda_project
    ```

---

## 5. Create a Conda Environment for Your Project

1. Run the following command to create a new environment (e.g., named `myenv`) with essential packages:
    ```sh
    conda create -n myenv pandas numpy matplotlib scikit-learn jupyter
    ```
2. When prompted, type `y` and hit **Enter** to proceed with the installation.

---

## 6. Activate Your Environment

1. Activate the environment with:
    ```sh
    conda activate myenv
    ```
2. The prompt should now start with `(myenv)`.

---

## 7. Launch Jupyter Notebook

1. Still in your project folder and with your environment activated, launch Jupyter Notebook:
    ```sh
    jupyter notebook
    ```
2. Your web browser will open the Jupyter interface in your project folder.

---

## 8. Test Your Environment in Jupyter Notebook

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

## 9. Closing and Managing Your Environment

- **To stop Jupyter Notebook:**  
  Go to the Anaconda Prompt where it is running and press `Ctrl+C`.
- **To deactivate the environment:**  
    ```sh
    conda deactivate
    ```
- **To reactivate later:**  
    ```sh
    conda activate myenv
    ```
- **To launch Jupyter again:**  
  Ensure your environment is activated, then run:
    ```sh
    jupyter notebook
    ```

---

> **Tip:** Repeat steps 4–9 for each new project to create isolated, reproducible environments.