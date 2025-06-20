# # Step 5: Modeling – Part 1: The Three Sets (Train, Validation, Test)

Now that we’ve defined the problem, understood our data, set an evaluation metric, and explored features, we arrive at **Step 5: Modeling**. This is where we use machine learning algorithms to try and solve our problem. 

Modeling is a multi-part process, and the first (and most important!) concept to understand is the **Three Sets**: the **training set**, **validation set**, and **test set**.

---

## Why Do We Split Data?

To evaluate how well your machine learning model will perform on truly new data (i.e., data it hasn’t seen before), it’s crucial to simulate this scenario by splitting your dataset. Otherwise, you risk building a model that simply memorizes the data (overfitting) rather than learning general patterns that can be applied to future, unseen examples.

---

## The Three Sets Explained

### 1. **Training Set**
- **Purpose:** Used to train (fit) the model.
- **Typical Size:** 70–80% of your data.
- **What Happens:** The model learns patterns and relationships from this data.

### 2. **Validation Set**
- **Purpose:** Used to tune the model and select the best parameters or model architecture.
- **Typical Size:** 10–15% of your data.
- **What Happens:** You use this set to experiment and see if your model is improving. Helps prevent overfitting by providing feedback on how the model performs on data it hasn’t trained on.

### 3. **Test Set**
- **Purpose:** Used to evaluate the final model after all tuning is complete.
- **Typical Size:** 10–15% of your data.
- **What Happens:** The test set acts as the "final exam"—your model should never have seen this data during training or validation. This provides an unbiased estimate of how well your model will perform in the real world.

---

## Analogy: Studying for an Exam

- **Training Set:** Like your study materials during the semester.
- **Validation Set:** Like a practice exam—used to check your understanding and tweak your study strategy.
- **Test Set:** Like the real final exam—assesses how well you can apply what you’ve learned to new, unseen problems.

> If a student got access to the final exam questions beforehand, their high score wouldn’t reflect real learning—just memorization. The same applies to machine learning models!

---

## Typical Split Ratios

- **Training:** 70–80%
- **Validation:** 10–15%
- **Test:** 10–15%

_Example:_
If you have 100 patient records:
- 70 for training
- 15 for validation
- 15 for test

> **Note:** Sometimes, you’ll see only two splits (training and test), but the three-set approach is considered best practice for most projects.

---

## Key Points

- **Generalization:** The ability of a model to perform well on new, unseen data.
- **Avoiding Overfitting:** Keeping the test set completely separate ensures your model is evaluated fairly and isn’t just memorizing the answers.
- **No Cheating:** Never let your model see the validation or test set during training!

---

## Reflection

- Think about your own experience preparing for a test:
  - Did you use practice tests to improve?
  - Was the final test different from your practice materials?
  - Would you have learned as much if you had seen the final test in advance?
- This is the same reason why we keep the test set hidden from the model until the very end.

---
