# Creating and Dropping Columns in Pandas

In this lesson, you'll learn how to create new columns in a pandas DataFrame from Series, lists, calculations, and single values. You'll also see how to drop columns you no longer need—all essential skills for manipulating data in data science and machine learning projects.

---

## 1. **Creating a Column from a Series**

You can assign a pandas Series to a new column in your DataFrame. If the Series is shorter than the DataFrame, pandas will fill the rest with `NaN` (missing values).

```python
import pandas as pd

seats_column = pd.Series([5, 5, 5, 5, 5])
car_sales["seats"] = seats_column
car_sales.head()
```
- The new column appears on the right.
- Missing values are filled with `NaN`.

### **Filling Missing Values**

```python
car_sales["seats"].fillna(5, inplace=True)
```

---

## 2. **Creating a Column from a Python List**

If you want to use a list, its length **must match** the DataFrame's length.

```python
fuel_economy = [7.5, 9.2, 5.0, 9.6, 8.7, 3.0, 4.5, 6.8, 7.2, 5.9]
car_sales["fuel_per_100km"] = fuel_economy
```
- If lengths don’t match, pandas will raise a `ValueError`.

---

## 3. **Creating a Column from Calculations**

You can use arithmetic between columns to create new columns.

```python
car_sales["total_fuel_used"] = (car_sales["odometer"] / 100) * car_sales["fuel_per_100km"]
```
- This calculates total liters of fuel used over the car's lifetime.

---

## 4. **Creating a Column from a Single Value**

This sets the same value for every row.

```python
car_sales["number_of_wheels"] = 4
car_sales["passed_road_safety"] = True  # Boolean column
```

---

## 5. **Checking Data Types**

```python
car_sales.dtypes
```
- You can have columns of type `int`, `float`, `object` (string), `bool`, etc.

---

## 6. **Dropping Columns**

To remove a column:

```python
car_sales = car_sales.drop("total_fuel_used", axis=1)
```
- `axis=1` specifies that you are dropping a column (not a row).
- Reassignment is necessary, or use `inplace=True`:

```python
car_sales.drop("total_fuel_used", axis=1, inplace=True)
```

---

## 7. **Key Points & Tips**

- Creating new columns is as easy as `df["new_column"] = ...`
- When using lists, length must match the DataFrame.
- For Series, pandas will align by index and fill with `NaN` for missing indices.
- Always remember to reassign or use `inplace=True` when modifying DataFrames.
- Use column calculations to derive new insights from your data.

---

## 8. **Practice**

- Try adding your own custom column (from a formula, list, or single value).
- Try dropping a column you don't need.
- Experiment with different data types (int, float, bool, object).

---
