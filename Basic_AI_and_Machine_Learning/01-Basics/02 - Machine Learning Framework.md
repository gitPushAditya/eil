
# The Three Parts of Machine Learning

1. **Data Collection:**  

   - Gathering and preparing the data you'll use for modeling.

2. **Data Modeling:**  

   - Applying ML algorithms to extract insights and build predictive models.

3. **Deployment:**  

   - Making your trained models available to real users, either through applications or APIs.


## Six Steps of Data Modeling:

1. **Problem Definition**

2. **Data**

3. **Evaluation**

4. **Features**

5. **Modeling**

6. **Experimentation**

---

## 1. Problem Definition 

- **Key Question:** What exactly is the problem we're trying to solve?

- **Considerations:**

  - Is it a supervised or unsupervised problem?

  - Is it a classification or regression task?

- **Why it matters:**  

  A clear problem definition guides every subsequent step and ensures you’re solving the right problem with the right tools.

  ---

## 2. Data

- **Key Question:** What kind of data do we have?

- **Types of Data:**

  - **Structured Data:** Rows and columns (think spreadsheets, databases).

  - **Unstructured Data:** Images, audio, text, etc.

- **Why it matters:**  

  The nature of the data influences which ML methods and tools you should use.

  
---
## 3. Evaluation

  

- **Key Question:** How will we measure success?

- **Setting Metrics:**

  - Define what a "good" model looks like (e.g., 95% accuracy for a house price prediction model).

  - Metrics may shift over time, but an initial target keeps you focused.

- **Why it matters:**  

  Without clear metrics, you might endlessly tweak your model without knowing if you’re making real progress.

  
---
## 4. Features

  

- **Key Question:** What do we already know about the data?

- **Types of Features:**

  - **Numerical:** e.g., body weight, age.

  - **Categorical:** e.g., gender, blood type.

  - **Derived:** Created from other data, e.g., BMI from height and weight.

- **Why it matters:**  

  Features are the information the model uses to learn patterns and make predictions.

  
---
## 5. Modeling

  

- **Key Question:** Which machine learning model should we use for this problem and data?

- **Process:**

  - Explore different algorithms—most are already implemented in libraries (no need to code from scratch!).

  - Different models excel at different tasks; your job is to select and apply the best candidate(s).

- **Why it matters:**  

  The right model can make a big difference in performance and results.

  
---
## 6. Experimentation

  

- **Key Idea:** ML is an iterative process, not a straight line.

- **Cycle:**

  - You might re-define the problem after seeing your data.

  - You might choose a new model if the first doesn’t meet your evaluation metric.

  - You keep experimenting—adjusting data, features, models, and evaluation criteria—until you reach a satisfactory solution.

- **Why it matters:**  

  Flexibility is crucial. The framework is a guide, not a rigid checklist.

  
---
## Summary Table

  

| Step             | Key Question                                | Example (House Price Project)      |

|------------------|---------------------------------------------|------------------------------------|

| Problem          | What are we solving?                        | Predict house prices               |

| Data             | What data do we have?                       | Historical sales, features         |

| Evaluation       | How will we measure success?                | ≥95% accuracy                      |

| Features         | What do we know about the data?             | Rooms, area, location, bathrooms   |

| Modeling         | What model/algorithm should we use?         | Linear regression, decision trees  |

| Experimentation  | How do we improve results?                  | Try new features, tune models      |


---