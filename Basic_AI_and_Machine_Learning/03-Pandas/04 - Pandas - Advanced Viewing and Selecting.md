# Advanced Viewing, Grouping, and Visualizing Data in Pandas

You've now learned the basics of viewing and selecting data in pandas. Let's build on that foundation with more advanced techniques: `pd.crosstab`, `groupby`, and simple visualizations—all essential for data exploration and analysis!

---

## 1. Comparing Columns with `pd.crosstab()`

Use `pandas.crosstab()` to compare two columns and display their relationship as a table (contingency table).

```python
import pandas as pd

# Example: Compare 'make' and 'doors' in the car_sales DataFrame
pd.crosstab(car_sales["make"], car_sales["doors"])
```

**What does this do?**

- Rows: unique values in the first column ("make")
- Columns: unique values in the second column ("doors")
- Values: counts of occurrences for each combination

**Use case:**  
Quickly see how many cars of each make have 3, 4, or 5 doors, etc.

---

## 2. Grouping and Aggregating with `.groupby()`

Use `.groupby()` to group your DataFrame by one or more columns and then perform an aggregation (like mean, sum, count).

```python
# Group by 'make' and calculate mean for each group
car_sales.groupby("make").mean(numeric_only=True)
```

- Each row: one group (e.g., each car make)
- Columns: aggregated statistics (e.g., average odometer, average doors, etc.)

**You can group by any column and call a variety of aggregation functions:**

```python
car_sales.groupby("make").max()
car_sales.groupby("color").sum(numeric_only=True)
```

---

## 3. Visualization in Pandas

### Plotting a Column

```python
car_sales["odometer"].plot()
```
- Plots the values in the "odometer" column.
- Requires `matplotlib` (already installed if you used conda to set up your environment).

### Display Plots Inline in Jupyter

If your plots don’t show up, run these lines at the top of your notebook:

```python
import matplotlib.pyplot as plt
%matplotlib inline
```
- The `%matplotlib inline` "magic" makes plots display inside your notebook.

---

## 4. Histograms for Distribution

Histograms show the distribution (spread) of a numerical column.

```python
car_sales["odometer"].hist()
```

- Reveals the range, common values, and any outliers in your data.

---

## 5. Handling Non-Numeric Columns for Plotting

If you get an error like `TypeError: no numeric data to plot`, check the data type:

```python
car_sales["Price"].dtype
```

If it's an `object` (i.e., string), you need to convert it to numbers:

```python
# Remove $ , . from price and convert to integer
car_sales["Price"] = car_sales["Price"].str.replace('[\$\,\.]', '', regex=True).astype(int)
```

> **Note:** In newer pandas, always use `regex=True` in `str.replace` for regular expressions.

---

## 6. Plotting Your Converted Price Column

```python
car_sales["Price"].plot()
plt.show()
```

- Now you can visualize the "Price" column as a numeric variable.

---

## 7. Practice and Explore

- Try combining `.groupby()` with multiple columns and different aggregations.
- Experiment with more columns in `pd.crosstab()`.
- Explore other plot types like `.plot(kind="bar")`, `.hist()`, etc.
- If you need to transform string columns to numbers, remember the `str.replace(..., regex=True)` trick!

---
