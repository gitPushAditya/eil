# Step 4 - Features – Understanding and Engineering the Information in Your Data


After defining your problem, understanding your data, and setting your evaluation metric, it’s time for **Step 4: Features**. In machine learning, features are at the heart of what makes a model successful—they’re the pieces of information your model uses to learn patterns and make predictions.

---

## What Are Features?

- **Features** are individual measurable properties or characteristics of your data.
- You might also hear them called **feature variables**.
- In simple terms, features are the inputs your model uses to predict an output (target).

### Example

Suppose you’re building a model to predict whether a patient has heart disease:

- **Features:** Body weight, sex, average resting heart rate, chest pain rating, etc.
- **Target:** Whether or not the patient has heart disease (yes/no).

---

## Types of Features

### 1. Numerical Features

- **Definition:** Quantitative values (numbers).
- **Examples:** Body weight, age, blood pressure.

### 2. Categorical Features

- **Definition:** Qualitative values, representing categories or groups.
- **Examples:** Sex (male/female), smoker status (yes/no), blood type (A/B/AB/O).

### 3. Derived Features

- **Definition:** New features created by transforming or combining existing ones (**feature engineering**).
- **Examples:**
  - Creating a “Visited in last year” feature from hospital visit timestamps.
  - Calculating BMI from height and weight.

**Feature engineering** is the process of creating derived features to help the model learn more effectively.

---

## Features in Unstructured Data

- **Unstructured data** (like images, audio, or text) also has features, although they’re less obvious.
- **Example:**  
  - In images of dogs, features might be patterns like four leg-like shapes or circular shapes for eyes.
  - Machine learning algorithms automatically discover these features while training; you don’t need to specify them manually.

---

## Feature Coverage

- **Definition:** The proportion of samples in your dataset that contain a specific feature.
- **Why it matters:**  
  - ML algorithms learn best when all samples have values for each feature.
  - If a feature is missing in most samples (low coverage), it’s usually best to exclude it or collect more data.

**Example:**  
If only 10 out of 100 patient records have a “Most Eaten Foods” feature, this feature has just 10% coverage—likely not enough to be useful for training a model.

---

## Best Practices for Features

- **Aim for complete feature coverage:** Most (ideally all) samples should have values for each feature you use.
- **Choose features that are relevant and informative for the target you want to predict.
- **Experiment with feature engineering:** Creating new features from existing data can greatly improve your model’s performance.
- **Be aware of missing values:** Handle them appropriately (e.g., impute, drop, or collect more data).

---

## Reflection

- Think about a recent problem you solved—what data did you use?
  - Were your features numerical (numbers), categorical (labels), or did you create new ones by combining information (derived)?
- As you progress, pay attention to the kinds of features in your projects; good feature selection and engineering often make the biggest difference in model performance.

---

