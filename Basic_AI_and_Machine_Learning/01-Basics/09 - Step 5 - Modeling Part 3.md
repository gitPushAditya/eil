# Step 5 - Modeling Part 3 Model Tuning (Hyperparameter Tuning)

Once you have trained your initial model and are satisfied with its basic performance, the next step is **tuning**—just like adjusting the dials on an oven to get the perfect meal. Tuning is about improving your model’s performance by adjusting its **hyperparameters**.

---

## What is Model Tuning?

- **Model tuning** refers to the process of adjusting the model’s hyperparameters to optimize its performance for your specific data.
- **Hyperparameters** are configuration settings external to the model that cannot be learned from the data (unlike model parameters). You set these before training.

### Analogy

- Think of hyperparameters like the temperature and time settings when cooking a chicken dish. 
  - 180°C for 1 hour might leave it undercooked.
  - 200°C for 1 hour could be perfect.
- Similarly, choosing different hyperparameter values can influence your model’s performance.

---

## Where Does Tuning Happen?

- **Best practice:** Tune your hyperparameters using the **validation set**.
- **Alternative:** If you don’t have a validation set, tuning can also occur on a portion of the training data.

---

## Examples of Hyperparameters

- **Random Forest:**
  - Number of trees in the forest
  - Maximum depth of each tree
- **Neural Network:**
  - Number of layers
  - Number of units per layer
  - Learning rate
- **General ML Models:**
  - Regularization strength
  - Batch size
  - Number of training epochs

Each model comes with its own set of hyperparameters. The names and effects will vary.

---

## How to Tune Hyperparameters

1. **Start with Defaults:**
   - Use the default settings provided by the ML library (just like using the oven’s preset).
2. **Evaluate Performance:**
   - Check results on the validation set.
3. **Adjust and Iterate:**
   - Change one or more hyperparameters and retrain the model.
   - Compare results to see if performance improves.
   - Repeat as needed.

> **Tip:** There are automated ways to search through many combinations (like Grid Search or Random Search), which you’ll explore in future lessons.

---

## Key Points

- **Tuning is about finding the “sweet spot” for your model’s settings.**
- **Do your tuning using the validation set to avoid overfitting.**
- **Not all hyperparameters will matter for every model—focus on those that do.**
- **Be patient and systematic—it’s normal to try many combinations before finding the best!**

---
