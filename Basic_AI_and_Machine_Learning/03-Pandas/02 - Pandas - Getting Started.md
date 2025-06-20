# Getting Started with Pandas: Series, DataFrame, Importing, and Exporting Data

In this lesson, you'll get hands-on experience with pandas' two main data types, how to create them, and how to import/export data using CSV files in a Jupyter Notebook. This workflow is foundational for all data analysis and machine learning projects in Python.

---

## 1. **Set Up Your Environment and Jupyter Notebook**

- Open your terminal/Anaconda Prompt.
- Navigate to your project folder.
- Activate your environment:
    ```sh
    conda activate path\to\your\env
    ```
- Launch Jupyter Notebook:
    ```sh
    jupyter notebook
    ```
- In the Jupyter dashboard, create a new Python 3 notebook. Name it, for example, `Introduction to Pandas`.

---

## 2. **Importing pandas**

```python
import pandas as pd
```
- The convention is to use `pd` as the alias for pandas.

---

## 3. **Pandas Data Types: Series and DataFrame**

### **Series (1D data)**
A pandas Series is a single column of data.

```python
# Example: Car brands as a Series
car_brands = pd.Series(["Toyota", "Honda", "BMW", "Ford"])
car_brands
```

**Output:**
```
0    Toyota
1     Honda
2       BMW
3      Ford
dtype: object
```

### **Another Example: Colors Series**

```python
colors = pd.Series(["Red", "Blue", "White"])
colors
```

---

### **DataFrame (2D data)**

A DataFrame is a table of data with rows and columns (like a spreadsheet).

```python
# Create a DataFrame from Series
car_data = pd.DataFrame({
    "Car Make": car_brands,
    "Color": colors
})
car_data
```

**Output:**
```
  Car Make  Color
0   Toyota    Red
1    Honda   Blue
2      BMW  White
```

---

## 4. **Import Data from a CSV File**

You'll almost always work with real datasets stored as CSV files.

- Place your CSV file (e.g., `carsales.csv`) in your project folder.

```python
car_sales = pd.read_csv("carsales.csv")
car_sales
```

### **Tab Autocomplete**
- In Jupyter, you can start typing a filename and press `Tab` to autocomplete.

---

## 5. **Anatomy of a DataFrame**

- **Index:** The leftmost column (default starts at 0).
- **Row:** Horizontal group of values (index + data).
- **Column:** Vertical group with a column name.
- **Data:** Individual values.
- **Axis:** `axis=0` (rows), `axis=1` (columns).
- **Column names:** The headers at the top.

> If you see `axis=0` or `axis=1` in pandas functions, remember:
> - `axis=0` → operation by rows
> - `axis=1` → operation by columns

---

## 6. **Export Data to a CSV File**

Once you've manipulated your DataFrame, you can export it:

```python
car_sales.to_csv("exported_carsales.csv")
```
- This will include the DataFrame’s index as a column.

**To exclude the index:**

```python
car_sales.to_csv("exported_carsales.csv", index=False)
```

---

## 7. **Re-import to Check Export**

```python
exported_car_sales = pd.read_csv("exported_carsales.csv")
exported_car_sales
```

---

## 8. **Key Takeaways**

- **Series:** 1D labeled array (single column of data).
- **DataFrame:** 2D labeled data table (rows and columns).
- **Import data:** `pd.read_csv("file.csv")`
- **Export data:** `df.to_csv("file.csv", index=False)`
- **Always check your DataFrame’s contents after importing/exporting!**

---

### Next: Learn to explore, describe, and manipulate your data using pandas’ most useful functions!