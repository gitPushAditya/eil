# What We Skipped: Pandas Features Beyond the Basics

You’ve now built a solid foundation in using pandas for data analysis and machine learning. But pandas is a massive library with many powerful features we haven’t covered in detail. This guide highlights some of the additional capabilities you may want to explore as you continue your data science journey.

---

## 1. **Advanced Indexing and Selection**

- **MultiIndex (Hierarchical Indexing):**  
  DataFrames can have multiple levels of row and/or column indexes. Useful for representing more complex data structures (e.g., time series with multiple dimensions).
  ```python
  df_multi = df.set_index(['year', 'month'])
  ```
- **The `.at[]` and `.iat[]` accessors:**  
  Fast access to single values by label (at) or integer position (iat).
  ```python
  df.at[5, 'col']
  df.iat[2, 3]
  ```
- **Query Method:**  
  Use string expressions to filter data.
  ```python
  df.query('age > 21 and gender == "M"')
  ```

---

## 2. **Working with Dates and Times**

- **Datetime Indexing:**  
  Pandas has powerful tools for parsing, manipulating, and indexing by dates.
  ```python
  df['date'] = pd.to_datetime(df['date'])
  df.set_index('date', inplace=True)
  ```
- **Resampling and Time Series Analysis:**  
  Easily group data by time intervals (day, week, month, etc.).
  ```python
  df.resample('M').sum()
  ```

---

## 3. **Merging, Joining, and Concatenation**

- **Concatenation:**  
  Stack DataFrames vertically or horizontally.
  ```python
  pd.concat([df1, df2], axis=0)  # Stack rows
  pd.concat([df1, df2], axis=1)  # Add columns
  ```
- **Merge (SQL-style joins):**  
  Combine DataFrames based on common columns or indexes.
  ```python
  pd.merge(df1, df2, on='id', how='left')
  ```
- **Join:**  
  A simpler syntax for joining on the index.
  ```python
  df1.join(df2, lsuffix='_left', rsuffix='_right')
  ```

---

## 4. **Pivot Tables and Reshaping**

- **Pivot Table:**  
  Summarize and aggregate data like Excel’s pivot table.
  ```python
  df.pivot_table(index='category', columns='type', values='value', aggfunc='mean')
  ```
- **Melt:**  
  Unpivot a DataFrame from wide to long format.
  ```python
  pd.melt(df, id_vars=['id'], value_vars=['math', 'science'])
  ```
- **Stack/Unstack:**  
  Transform between wide and long formats using MultiIndex.
  ```python
  df.stack()
  df.unstack()
  ```

---

## 5. **Categorical Data**

- **Category dtype:**  
  Store categorical variables efficiently and order them.
  ```python
  df['grade'] = pd.Categorical(df['grade'], categories=['A', 'B', 'C'], ordered=True)
  ```

---

## 6. **Working with Text Data**

- **Vectorized string operations:**  
  Pandas `.str` methods support powerful pattern matching and extraction.
  ```python
  df['email'].str.contains('@gmail.com')
  df['name'].str.extract(r'(\w+), (\w+)')
  df['address'].str.split(',', expand=True)
  ```

---

## 7. **Missing Data – Advanced Tools**

- **Interpolate:**  
  Fill missing values by interpolating.
  ```python
  df['value'].interpolate(method='linear')
  ```
- **Drop columns/rows conditionally:**  
  ```python
  df.dropna(axis=1, thresh=10)  # Drop columns with fewer than 10 non-NA values
  ```

---

## 8. **Efficient Operations & Performance**

- **Vectorization:**  
  Avoid loops—pandas is optimized for operations on entire columns.
- **Applymap:**  
  Apply a function to every element of a DataFrame.
  ```python
  df.applymap(lambda x: str(x).upper())
  ```
- **Using `.pipe()` for method chaining:**  
  ```python
  (df.pipe(func1)
     .pipe(func2, arg1=...)
     .pipe(func3))
  ```

---

## 9. **Input/Output with Other Formats**

- **Read and write Excel files:**
  ```python
  df = pd.read_excel('file.xlsx')
  df.to_excel('out.xlsx', index=False)
  ```
- **Read/write JSON, HTML, Parquet, HDF5, SQL and more:**
  ```python
  df = pd.read_json('file.json')
  df.to_parquet('file.parquet')
  ```

---

## 10. **Styling DataFrames (for reporting/visualization)**

- **Highlight, format, and render DataFrames prettily in Jupyter Notebooks:**
  ```python
  df.style.highlight_max(axis=0)
  df.style.format({'price': '${:,.2f}'})
  ```

---

## 11. **Advanced GroupBy Operations**

- **Multiple Aggregations:**
  ```python
  df.groupby('category').agg({'value': ['mean', 'max', 'min']})
  ```
- **Transform and Filter:**
  ```python
  df.groupby('group')['value'].transform('zscore')
  df.groupby('group').filter(lambda x: x['value'].mean() > 10)
  ```

---

## 12. **Window Functions / Rolling Calculations**

- **Rolling Mean/Sum/Etc.:**
  ```python
  df['rolling_avg'] = df['sales'].rolling(window=3).mean()
  ```
- **Expanding:**
  ```python
  df['cum_sum'] = df['sales'].expanding().sum()
  ```

---

## 13. **Custom Data Types and Extensions**

- **Custom extension types:**  
  For advanced use cases, pandas supports custom data types and extension arrays.

---

## 14. **Error Handling and Options**

- **Custom error handling:**  
  Use `errors='ignore'`, `errors='coerce'` in conversions.
- **Display options:**  
  Control how much data or how many columns pandas shows.
  ```python
  pd.set_option('display.max_rows', 100)
  pd.set_option('display.float_format', '{:.2f}'.format)
  ```

---

## 15. **Integration with Other Libraries**

- **Seamless with NumPy:**  
  Use NumPy functions on DataFrames.
- **Visualization:**  
  Integrate with matplotlib, seaborn, plotly, etc.
- **Machine Learning:**  
  DataFrames are the standard input/output for scikit-learn and other ML libraries.

---

## 16. **Debugging and Profiling**

- **.info() and .memory_usage():**  
  Understand DataFrame structure and memory consumption.
- **pandas-profiling:**  
  Generate automated EDA reports.

---

## 17. **Pandas Ecosystem & Extensions**

- **Dask:**  
  For scalable, out-of-core DataFrames when data is too large for memory.
- **GeoPandas:**  
  For geospatial data.
- **PandasGUI, pandas-profiling, Sweetviz:**  
  For interactive exploration and reporting.

---

## 18. **Resources for Further Learning**

- [Pandas Official Documentation](https://pandas.pydata.org/docs/)
- [Pandas User Guide](https://pandas.pydata.org/pandas-docs/stable/user_guide/index.html)
- [10 Minutes to pandas (Official)](https://pandas.pydata.org/pandas-docs/stable/user_guide/10min.html)
- [Awesome Pandas (GitHub)](https://github.com/tommyod/awesome-pandas)
- [Practical Business Python](https://pbpython.com/)

---

## ⚡️ **Next Steps**

As you advance, try searching the docs or Stack Overflow for any new data challenge you encounter. The pandas ecosystem is huge—don’t worry about learning it all at once. Practice, experiment, and refer to documentation as needed!

---