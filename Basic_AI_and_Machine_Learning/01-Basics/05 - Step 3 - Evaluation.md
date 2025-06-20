# Step 3 - Evaluation

After defining your problem and understanding your data, it’s time to set the standards for success. This is where **evaluation metrics** come in. Every ML project aims to extract insights from data and predict the future in some way, but how do you know if your model is actually performing well? That’s the job of evaluation metrics.

---

## What is an Evaluation Metric?

An **evaluation metric** is a quantitative measure of how well a machine learning algorithm achieves your project’s goal. It helps you answer the key question:

> **What defines success for us?**

Setting an evaluation metric aligns your team and stakeholders with a clear, measurable target.

---

## Common Evaluation Metrics by Problem Type

### 1. **Classification Problems**
When predicting categories (e.g., does a patient have heart disease: yes/no):

- **Accuracy**:  
  - Percentage of correct predictions out of all predictions.
  - Good for balanced datasets.
- **Precision**:  
  - Of all items predicted as positive, how many are actually positive?
  - Important when false positives are costly.
- **Recall**:  
  - Of all actual positive cases, how many did the model identify correctly?
  - Important when missing a positive case is costly (e.g., medical diagnoses).
- **F1 Score**:  
  - The harmonic mean of precision and recall. Useful when classes are imbalanced.

> **Example:**  
> Predicting heart disease with patient records:  
> - Desired metric = Over 99% accuracy due to the critical nature of the problem.

---

### 2. **Regression Problems**
When predicting a continuous value (e.g., house price, car sale price):

- **Mean Absolute Error (MAE)**:  
  - Average of the absolute differences between predicted and actual values.
  - Easy to interpret.
- **Mean Squared Error (MSE)**:  
  - Average of squared differences between predicted and actual values.
  - Heavily penalizes large errors.
- **Root Mean Squared Error (RMSE)**:  
  - Square root of MSE; same units as the target variable.

> **Example:**  
> Predicting car sale prices:  
> - Desired metric = Minimize MAE or MSE (the closer to zero, the better).

---

### 3. **Recommendation Problems**
When suggesting items (e.g., products, movies) and only care about the best options:

- **Precision at K (e.g., Precision@10)**:  
  - Of the top K recommendations, how many are actually relevant to the user?
  - Focuses on the quality of the top results rather than all results.

> **Example:**  
> Recommending the top 10 products:  
> - Metric = Precision@10 (what fraction of the top 10 recommendations are good matches).

---

## Why Set an Evaluation Metric Early?

- **Focuses Your Efforts:**  
  Everyone on the project works towards the same measurable goal.
- **Sets Clear Expectations:**  
  Stakeholders know what success looks like (e.g., 95% accuracy, MAE less than $5,000).
- **Guides Model Selection:**  
  Helps you choose the right models and tune them for what matters most.

> **Real-World Example:**  
> In a project to predict the at-fault party from car insurance claims using text, the business defined success as achieving at least 95% accuracy. This meant the model could only misclassify 1 out of every 20 claims.

---

## Evaluation Metrics Can Change

It’s perfectly normal for your metric to evolve as you learn more about your data and problem. You might start with accuracy and later realize precision or recall is more important, or adjust your target as you see what’s achievable.

---

## Reflect: Measuring in Everyday Life

- What do you measure in your daily activities?
  - (e.g., Time to complete a task, test scores, customer satisfaction)
- Do you use different metrics for different tasks?
  - (e.g., Speed for races, quality for writing, accuracy for calculations)

Thinking about how you measure success in everyday tasks will make it easier to set smart evaluation metrics for your ML projects.

---
