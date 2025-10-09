# Pandas Advanced Practice Worksheet

This worksheet is designed to challenge your understanding of pandas for data analysis and manipulation. The questions range from intermediate to advanced and encourage you to use a wide variety of pandas features. For data manipulation tasks, sample CSV schemas are provided. You can generate your own data based on these schemas, or use online generators (e.g., [Mockaroo](https://mockaroo.com/)).

---

## **Sample CSV Files**

### 1. `employees.csv`
| id | name         | department | salary | hire_date  | manager_id | performance_score | email                |
|----|--------------|------------|--------|------------|------------|-------------------|----------------------|
| 1  | Alice Smith  | Sales      | 72000  | 2018-05-01 | 3          | 4.6               | alice@company.com    |
| 2  | Bob Jones    | HR         | 68000  | 2017-08-11 |            | 4.2               | bob@company.com      |
| 3  | Carol Evans  | Sales      | 95000  | 2015-01-15 |            | 4.9               | carol@company.com    |
| ...| ...          | ...        | ...    | ...        | ...        | ...               | ...                  |

### 2. `sales.csv`
| sale_id | employee_id | product   | units | price_per_unit | sale_date   |
|---------|-------------|-----------|-------|----------------|-------------|
| 1001    | 1           | Laptop    | 5     | 1200           | 2023-02-15  |
| 1002    | 3           | Keyboard  | 15    | 45             | 2023-02-16  |
| ...     | ...         | ...       | ...   | ...            | ...         |

### 3. `products.csv`
| product_id | product_name | category   | cost_price | selling_price | stock | discontinued |
|------------|--------------|------------|------------|---------------|-------|--------------|
| P001       | Laptop       | Electronics| 900        | 1200          | 45    | False        |
| P002       | Keyboard     | Electronics| 20         | 45            | 100   | False        |
| ...        | ...          | ...        | ...        | ...           | ...   | ...          |

---

## **Questions**

---

### **Section 1: Data Loading & Basic Operations**

1. Load `employees.csv` into a DataFrame. Display the first and last 3 rows.
2. Check for missing values in all columns. Which columns have missing values and how many?
3. What is the data type of each column? Convert `hire_date` to a `datetime` type if not already.
4. Find the number of unique departments. List them.
5. List all employees in the "Sales" department hired after January 1, 2017.

---

### **Section 2: Indexing, Selection, and Filtering**

6. Set the `id` as the DataFrame index. What are the advantages of this?
7. Retrieve all employees whose performance score is above the department average.
8. Find all employees whose names contain "son" or "ev".
9. Display the top 5 highest paid employees in each department.
10. List all employees who report directly to Carol Evans (manager_id matches her id).

---

### **Section 3: Data Aggregation & Grouping**

11. What is the average salary by department? Sort the results descending.
12. Find the median performance score for each department.
13. For each manager (manager_id), compute the total salary of their direct reports.
14. Which department has the largest salary variance?
15. Add a column to indicate whether each employee's salary is above or below the department average.

---

### **Section 4: Data Merging & Combining**

16. Load `sales.csv` and merge with `employees.csv` to attach employee names to each sale.
17. Merge the sales data with `products.csv` to include product details for each sale.
18. For each sale, compute the total sale value (`units * price_per_unit`). Add as a new column.
19. Which employee generated the highest total sales value in 2023?
20. List all products that have never been sold.

---

### **Section 5: Advanced Data Manipulation**

21. Create a pivot table showing total units sold by employee and product category.
22. Which month in 2023 had the highest total sales value? Plot a bar chart of monthly sales.
23. For each department, calculate the rolling 3-month average salary of new hires (by hire_date).
24. Find all employees who have been with the company for more than 5 years and have a performance score below 4.0.
25. Replace all emails ending with `@company.com` with `@corp.com`.

---

### **Section 6: Missing Data & Outliers**

26. In `products.csv`, fill missing stock values with the median stock for that category.
27. Identify and list outliers in `salary` using the IQR method.
28. For employees with missing `manager_id`, assign the manager as the department's most senior employee (earliest `hire_date`).

---

### **Section 7: Categorical Data & Encoding**

29. Convert the `department` column to a categorical dtype and order them alphabetically.
30. In `products.csv`, flag discontinued products as a new boolean column `is_discontinued`.

---

### **Section 8: Time Series & Datetime**

31. In `sales.csv`, convert `sale_date` to datetime and set as index. Resample sales to weekly totals.
32. Find the average sale value per week in Q2 2023.
33. For each product, plot the cumulative units sold over time.
34. Find the top 3 products with the largest increase in units sold between the first and last quarter of 2023.

---

### **Section 9: Text & String Operations**

35. In `employees.csv`, extract first and last names into separate columns.
36. Find all employees whose names are palindromes (e.g. "Ada", "Bob").
37. In `products.csv`, extract the brand name if the product name is in the format "Brand Product".
38. Create a summary table showing the count of unique email domains among employees.

---

### **Section 10: Exporting & Advanced Operations**

39. Save a DataFrame of all sales in 2023 where the product was discontinued to `discontinued_sales_2023.csv`.
40. Write a function to mask all but the last 3 characters of employee emails (e.g., `a******@c***.com`).

---

## **Bonus Challenges**

41. Using a MultiIndex (department, hire_date), calculate the mean salary for each department per hire month.
42. For each employee, calculate their total sales value as a percentage of their department's total sales.
43. Identify the top 5 employees by performance score, and for each, list their top 2 products sold by total value.

---

## **Instructions**

- Use the sample schemas to generate your own CSV data for testing.
- Try to solve each task using idiomatic pandas code.
- For visualizations, use `matplotlib` or `seaborn`.
- Document your answers and code in a Jupyter notebook.
- If you get stuck, consult the [pandas documentation](https://pandas.pydata.org/docs/).

---

**Good luck! Mastering these exercises will prepare you for advanced data wrangling and real-world data science projects.**