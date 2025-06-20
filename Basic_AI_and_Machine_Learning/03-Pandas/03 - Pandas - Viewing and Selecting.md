# Viewing and Selecting Data in Pandas

In this lesson, you'll learn various ways to view and select data in a pandas DataFrame. These techniques are essential for exploring, understanding, and filtering your data.

---

## 📋 Table of Contents

1. [Viewing the Top and Bottom Rows: `head()` and `tail()`](#viewing-the-top-and-bottom-rows-head-and-tail)
2. [Selecting by Index vs. Position: `.loc` vs. `.iloc`](#selecting-by-index-vs-position-loc-vs-iloc)
3. [Selecting Columns](#selecting-columns)
4. [Boolean Indexing (Filtering)](#boolean-indexing-filtering)
5. [Tips and Notes](#tips-and-notes)

---

## Viewing the Top and Bottom Rows: `head()` and `tail()`

- **View the first n rows (default 5):**
  ```python
  car_sales.head()        # First 5 rows
  car_sales.head(7)       # First 7 rows
  ```
- **View the last n rows (default 5):**
  ```python
  car_sales.tail()
  car_sales.tail(3)
  ```

---

## Selecting by Index vs. Position: `.loc` vs. `.iloc`

### `.loc` – Select by Index Label

- **Works with the DataFrame's index values (can be numbers, strings, etc.).**
- **Example with custom index:**
  ```python
  animals = pd.Series(['cat', 'dog', 'bird', 'panda', 'snake'], index=[0, 3, 9, 8, 3])
  animals.loc[3]           # Returns items with index label 3
  animals.loc[9]           # Returns item with index label 9
  ```

- **Example with DataFrame:**
  ```python
  car_sales.loc[3]         # Row with index label 3
  ```

### `.iloc` – Select by Integer Position

- **Works with the positional index (starting from 0).**
- **Example with Series:**
  ```python
  animals.iloc[3]          # The 4th item (index 3)
  ```
- **Example with DataFrame:**
  ```python
  car_sales.iloc[3]        # The 4th row (position 3)
  ```

**Key Difference:**
- `.loc[]` uses index labels (what you see in the leftmost column).
- `.iloc[]` uses integer positions (like list indexing).

### **Slicing with `.loc` and `.iloc`**

- **`.iloc[:3]`**: Up to position 3 (not including 3).
- **`.loc[:3]`**: Up to and including index label 3.

---

## Selecting Columns

- **Bracket notation (works with any column name):**
  ```python
  car_sales["make"]
  car_sales["odometer"]
  ```
- **Dot notation (only works if column name has no spaces or special characters):**
  ```python
  car_sales.make
  ```
  > Note: `car_sales.odometer_km` will error if the column is named `odometer_km` (with a space or special character).

---

## Boolean Indexing (Filtering)

- **Filter rows where a column matches a value:**
  ```python
  car_sales[car_sales["make"] == "Toyota"]
  ```
- **Filter by numeric condition:**
  ```python
  car_sales[car_sales["odometer"] > 100000]
  ```
- **Combine multiple conditions (using `&` for AND, `|` for OR):**
  ```python
  car_sales[(car_sales["make"] == "Toyota") & (car_sales["odometer"] > 100000)]
  ```

---

## Tips and Notes

- Use **Markdown headings** in your notebook (e.g., `## Viewing and Selecting Data`) to keep things organized.
- `.loc[]` and `.iloc[]` can be used for both rows and columns (e.g., `df.loc[row_index, column_label]`).
- Boolean indexing is very powerful for filtering your DataFrame to just the rows you want to analyze.
- Practice with different combinations and review the pandas documentation for more complex selections.

---
