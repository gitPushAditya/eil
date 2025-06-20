# Describing Data in Pandas

In this lesson, you'll learn how to describe your data using pandas, including key attributes and functions that provide statistical summaries and information about your DataFrame. These are essential first steps in any data analysis workflow!

---

## 📋 Table of Contents

1. [Attributes vs. Functions](#attributes-vs-functions)
2. [Describing Data Types and Structure](#describing-data-types-and-structure)
3. [Statistical Summaries](#statistical-summaries)
4. [Working with Series](#working-with-series)
5. [Other Useful Functions](#other-useful-functions)
6. [Important Note on `.mean()`](#important-note-on-mean)

---

## Attributes vs. Functions

- **Attribute:** No parentheses `()`; describes a property of the object (e.g., `car_sales.dtypes`)
- **Function:** Has parentheses `()`; performs an operation (e.g., `car_sales.describe()`)

---

## Describing Data Types and Structure

### 1. **Check Column Data Types**

```python
car_sales.dtypes
```
- Shows the data type (`int64`, `object`, etc.) for each column.

---

### 2. **Get Column Names**

```python
car_sales.columns
```
- Returns an Index object containing the column names.

```python
column_list = car_sales.columns
```

---

### 3. **Get Index Range**

```python
car_sales.index
```
- Shows the index range (row labels) of your DataFrame.

---

## Statistical Summaries

### 4. **Quick Statistical Overview**

```python
car_sales.describe()
```
- Returns count, mean, std, min, max, quartiles for numerical columns.

> **Note:** Only numeric columns are included. If a column (like `Price`) is an object, it won't appear here.

---

### 5. **Info About DataFrame**

```python
car_sales.info()
```
- Gives index range, column names, data types, non-null counts, and memory usage.

---

### 6. **Mean and Sum**

#### Mean (Average):

```python
car_sales.mean(numeric_only=True)
```
- Returns the mean of all numeric columns.

#### Sum:

```python
car_sales.sum(numeric_only=True)
```
- Sums all numeric columns (if you want sum of all numeric columns at once).

#### Mean/Sum for a Single Column:

```python
car_sales["doors"].mean()
car_sales["doors"].sum()
```

---

## Working with Series

You can also perform statistical operations on pandas Series:

```python
car_prices = pd.Series([3000, 15000, 80000])
car_prices.mean()  # Average price
car_prices.sum()   # Total price
```

---

## Other Useful Functions

- **Length of DataFrame (number of rows):**
  ```python
  len(car_sales)
  ```
- **Try these common stats:**
  ```python
  car_sales.median(numeric_only=True)
  car_sales["odometer"].max()
  car_sales["odometer"].min()
  ```

---

## Important Note on `.mean()`

> **Update:**  
> In recent versions of pandas, you must use `numeric_only=True` when calling `.mean()` or similar functions on a DataFrame that includes both numeric and non-numeric columns:
>
> ```python
> car_sales.mean(numeric_only=True)
> ```
>
> - This tells pandas to only use numeric columns.
> - Otherwise, you'll get an error.

---

## Practice Suggestions

- Try running each function/attribute on your own DataFrame.
- Create your own Series and experiment with statistical functions like `.mean()`, `.sum()`, `.max()`, `.min()`, etc.
- Use `car_sales.info()` and `car_sales.describe()` as a starting point whenever you get a new dataset.

---
