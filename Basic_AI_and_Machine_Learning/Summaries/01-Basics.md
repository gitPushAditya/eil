# Basics of Machine Learning – Summary

## What is Machine Learning?
- Subfield of AI focused on enabling computers to learn from data and make predictions/decisions without explicit programming.
- Learns from data using algorithms (e.g., linear regression, decision trees, neural networks).
- Applications: image/speech recognition, spam detection, recommendations, etc.
- **Formula:** Data + Algorithms → Model (for predictions/decisions)

---

## The Machine Learning Framework

### Three Main Parts
1. **Data Collection:** Gather and prepare data.
2. **Data Modeling:** Apply ML algorithms to extract insights/build models.
3. **Deployment:** Make trained models available to users (apps, APIs).

### Six Steps of Data Modeling
1. **Problem Definition**
2. **Data**
3. **Evaluation**
4. **Features**
5. **Modeling**
6. **Experimentation**

---

## Step 1: Problem Definition
- **Key Question:** What problem are we trying to solve?
- Use ML when the solution requires learning from data (not just rules).
- Types of ML problems:
  - **Supervised Learning:** Data with labels (classification, regression).
  - **Unsupervised Learning:** Data without labels (clustering, recommendations).
  - **Transfer Learning:** Adapting a model trained on one task to another.
  - **Reinforcement Learning:** Learning by interacting with an environment (rewards/penalties).

---

## Step 2: Understanding the Data
- **Types of Data:**
  - **Structured:** Tables (rows/columns), e.g., CSV files.
  - **Unstructured:** Images, text, audio, video.
  - **Streaming:** Real-time, continuously generated data.
- **Workflow:** Load data → Explore/analyze (pandas, matplotlib) → Build models (scikit-learn) → Iterate.

---

## Step 3: Evaluation
- **Key Question:** How will we measure success?
- **Evaluation Metrics:**
  - **Classification:** Accuracy, Precision, Recall, F1 Score.
  - **Regression:** MAE, MSE, RMSE.
  - **Recommendation:** Precision@K.
- Set metrics early to focus efforts and guide model selection.

---

## Step 4: Features
- **Features:** Measurable properties/inputs used by the model.
  - **Numerical:** Quantitative (e.g., age, weight).
  - **Categorical:** Qualitative (e.g., gender, blood type).
  - **Derived:** Created from other features (feature engineering).
- **Best Practices:**
  - Aim for complete feature coverage.
  - Choose relevant/informative features.
  - Handle missing values appropriately.
  - Experiment with feature engineering.

---

## Step 5: Modeling
### Part 1: The Three Sets
- **Training Set:** Used to fit the model (70–80%).
- **Validation Set:** Used to tune/choose models (10–15%).
- **Test Set:** Used for final evaluation (10–15%).
- **Goal:** Generalization—model performs well on new, unseen data.

### Part 2: Choosing a Model
- Select models based on data type/problem (e.g., decision trees for structured data, neural networks for images/text).
- Use prebuilt algorithms from libraries (scikit-learn, TensorFlow, etc.).
- Train only on training data; never let the model see validation/test data during training.
- Start simple, experiment, and iterate.

### Part 3: Model Tuning (Hyperparameter Tuning)
- Adjust model hyperparameters (settings external to the model) to optimize performance.
- Tune using the validation set.
- Examples: number of trees (Random Forest), learning rate (Neural Network), regularization strength.
- Use systematic approaches (e.g., grid search) and be patient.

### Part 4: Model Comparison
- Evaluate models on the test set for real-world performance.
- **Underfitting:** Poor on both train/test (model too simple).
- **Overfitting:** Great on train, poor on test (model too complex).
- **Prevention:** Keep test data separate, ensure similar data distributions.
- Compare models fairly (same data splits, consider speed/resource usage).

---

## Step 6: Experimentation
- ML is **iterative**—loop through steps, refine, and improve.
- Try new models, tune hyperparameters, engineer new features, gather more data.
- **Tool fluency:** Learn which tools/libraries fit each step (pandas, numpy, scikit-learn, TensorFlow, MLflow, etc.).
- **Key Takeaways:**
  - Experimentation is ongoing—don’t expect perfection on the first try.
  - Each iteration brings new insights and better results.
  - Hands-on practice is the best way to master ML.

---

## Quick Reference Table
| Step             | Key Question                        | Example (House Price Project)   |
|------------------|-------------------------------------|---------------------------------|
| Problem          | What are we solving?                | Predict house prices            |
| Data             | What data do we have?               | Historical sales, features      |
| Evaluation       | How will we measure success?        | ≥95% accuracy                   |
| Features         | What do we know about the data?     | Rooms, area, location, etc.     |
| Modeling         | What model/algorithm should we use? | Linear regression, decision tree|
| Experimentation  | How do we improve results?          | Try new features, tune models   |

---

**Summary:**
- ML is about learning from data to make predictions/decisions.
- Follow a structured framework: define the problem, understand data, set metrics, engineer features, model, and experiment.
- Keep the process iterative and hands-on for best results.
