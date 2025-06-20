# Manipulating Data in Pandas

As you dive deeper into data science and machine learning, manipulating data with pandas becomes an essential skill. Here, you'll learn how to perform string transformations, handle missing values, and understand the difference between reassignment and the `inplace` parameter.

---

## 1. String Methods

You can use string methods on columns with text data (type `object`).  
For example, to convert all values in the "make" column to lowercase:

```python
car_sales["make"] = car_sales["make"].str.lower()
```

- `.str` lets you access string functions (like `.lower()`, `.replace()`, etc.) on pandas columns.
- Reassignment is required to save the change to the DataFrame.

---

## 2. Reassignment vs. `inplace=True`

Many pandas methods don't change the DataFrame unless you:

- **Reassign** the result to the column or DataFrame, or
- **Use the `inplace=True` parameter** (if available).

**Example:**

```python
# Reassignment
car_sales["make"] = car_sales["make"].str.lower()

# Using inplace (where supported)
car_sales["make"].str.lower(inplace=True)  # Note: Not all methods support inplace!
```

Most string methods do **not** support `inplace=True`, so reassignment is common.

---

## 3. Handling Missing Data

### **Importing a DataFrame with Missing Values**

```python
car_sales_missing = pd.read_csv("car_sales_missing_data.csv")
```

- Missing values appear as `NaN` in pandas.

### **Filling Missing Values**

Suppose you want to fill missing values in the "odometer" column with the mean value:

```python
# Calculate the mean (excluding NaN)
mean_odometer = car_sales_missing["odometer"].mean()

# Fill missing values (reassignment)
car_sales_missing["odometer"] = car_sales_missing["odometer"].fillna(mean_odometer)
```

Or use `inplace=True`:

```python
car_sales_missing["odometer"].fillna(mean_odometer, inplace=True)
```

---

## 4. Dropping Rows with Missing Values

To remove all rows with any missing values:

```python
car_sales_no_missing = car_sales_missing.dropna()
```

Or, to modify the DataFrame in place:

```python
car_sales_missing.dropna(inplace=True)
```

- **Without `inplace=True`**: Returns a new DataFrame and leaves the original unchanged.
- **With `inplace=True`**: Changes the original DataFrame directly.

---

## 5. When to Use Reassignment vs. `inplace=True`

- **Reassignment** gives you more control and avoids accidental data loss (you can always revert to the original).
- **`inplace=True`** modifies the DataFrame directly, which can be convenient but may overwrite your data.

**Tip:** Many data scientists prefer reassignment for safety and reproducibility.

---

## 6. Exporting Your Cleaned Data

After manipulation, you can export your DataFrame:

```python
car_sales_no_missing.to_csv("car_sales_cleaned.csv", index=False)
```

---

## 7. Practice Suggestions

- Try using string methods (`.str.upper()`, `.str.contains()`, etc.) on text columns.
- Experiment with filling missing values using different strategies (mean, median, constant).
- Practice dropping rows/columns with missing values.
- Explore the pandas documentation for `.fillna()` and `.dropna()` to see all available options.

---

> **Next:**  
> You'll learn about more advanced data manipulation (like replacing values, mapping, and combining DataFrames) as you continue your pandas journey!