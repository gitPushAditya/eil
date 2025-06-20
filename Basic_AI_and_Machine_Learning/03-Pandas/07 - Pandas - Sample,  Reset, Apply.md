# Sampling, Shuffling, Resetting Index, and Applying Functions in Pandas

As you prepare to use your data for machine learning, it's crucial to know how to shuffle, sample, and transform your DataFrame. These steps help you randomize your data and create new features for modeling. Let's walk through the key concepts:

---

## 1. **Shuffling Rows with `sample()`**

To shuffle all the rows in a DataFrame (random order):

```python
# Shuffle all rows (fraction=1 means 100% of data)
car_sales_shuffled = car_sales.sample(frac=1)
```
- The `sample()` method shuffles or samples a fraction of the DataFrame.
- The `frac` parameter is the fraction of rows to return; `frac=1` returns all rows in random order.

### **Sample a Subset**

```python
# Get a random 20% sample of the data
car_sales_sample = car_sales.sample(frac=0.2)
```
- Very useful for testing code on large datasets.

---

## 2. **Resetting the Index**

After shuffling, the DataFrame’s index will be out of order. Use `reset_index()` to make the index sequential again.

```python
# Reset index and drop the old index column
car_sales_shuffled.reset_index(drop=True, inplace=True)
```
- `drop=True`: Removes the old index column.
- `inplace=True`: Modifies the DataFrame in place.

---

## 3. **Applying Functions to Columns with `.apply()`**

You can use `.apply()` to apply a function (including lambda functions) to each element in a column.

```python
# Convert odometer from kilometers to miles
car_sales["odometer_miles"] = car_sales["odometer"] / 1.6
```
Or, using `.apply()` and a lambda function:

```python
car_sales["odometer_miles"] = car_sales["odometer"].apply(lambda x: x / 1.6)
```
- `lambda x: x / 1.6` is an anonymous (inline) function dividing each value by 1.6.

---

## 4. **Key Takeaways**

- **Shuffle rows** with `.sample(frac=1)` and reset the index with `.reset_index(drop=True)`.
- **Sample** subsets of your data using `.sample(frac=...)`.
- **Apply** custom or built-in functions to columns using `.apply()`.
- Always remember to **reassign** the result or use `inplace=True` if you want changes to persist.

---

## 5. **Practice**

- Shuffle your DataFrame and reset the index.
- Try sampling a small subset for quick testing.
- Write your own lambda function and use `.apply()` to create a new column or transform an existing one.

---
