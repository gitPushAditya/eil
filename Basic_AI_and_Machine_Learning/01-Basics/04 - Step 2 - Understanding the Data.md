# Step 2 - Understanding the Data

Now that we've defined our problem and matched it to an appropriate type of machine learning problem, the next step is to thoroughly understand the data we have available. Data is the backbone of any machine learning project, and knowing its form and source is crucial for choosing the right approach and tools.

---
## Types of Data

### 1. Structured Data

- **Definition:**  
  Data organized into rows and columns, like you’d see in a spreadsheet or a database table.
- **Examples:**  
  - Patient medical records (each row = patient; columns = blood pressure, age, diagnosis, etc.)
  - Customer purchase transactions
  - Financial records
- **Characteristics:**  
  - Consistent format across samples
  - Each column typically contains the same type of value (e.g., all numbers, all strings)
  - Easy to manipulate and analyze using tools like Excel, SQL, or Python libraries

**Sample (CSV format):**
```
ID,Weight,Sex,ChestPain
1,70,M,No
2,85,F,Yes
...
```
> CSV (Comma Separated Values) is one of the most common formats for structured data.

---
### 2. Unstructured Data

- **Definition:**  
  Data that doesn’t have a predefined data model or isn’t organized in a pre-defined manner.
- **Examples:**  
  - Images (e.g., photos of dogs, X-rays)
  - Text (e.g., emails, transcribed phone calls)
  - Videos
  - Audio files (e.g., voice recordings)
- **Characteristics:**  
  - Highly variable representation and format
  - Requires additional processing to extract useful information (e.g., converting images to numbers)
  - Often converted into a structured form for analysis

> An image of a dog, an audio recording of a conversation, and a paragraph of text are all unstructured data—they look very different and need special handling.

---
### Streaming Data

- **Definition:**  
  Data that is continuously generated and updated in real-time.
- **Examples:**  
  - Stock prices updating every second
  - Social media feeds
  - Live sensor data
  - News headlines
- **Use in ML:**  
  - Typically applied in advanced or production-level ML systems.
  - Requires specialized tools to handle data as it arrives.
- **Workflow:**  
  - Often, you start with static data for model development, then transition to streaming data when deploying your solution to handle real-time predictions.

---
## A Typical Data Science Workflow

1. **Open Static Data:**  
   Start by loading a CSV file (or another static dataset) into a tool such as a Jupyter Notebook.
2. **Explore and Analyze:**  
   Use Python libraries like **pandas** (for data manipulation) and **matplotlib** (for visualization) to understand your data.
3. **Build Models:**  
   Apply machine learning algorithms, often using libraries like **scikit-learn** (sometimes abbreviated as sklearn).
4. **Iterate:**  
   Adjust your analysis, features, and models as you learn more about your data.

---
## Reflection: Your Data

- **Think about the data you encounter daily.**
  - Is it structured (organized into tables)?
  - Or unstructured (text, images, videos)?
  - How much data do you have access to?
- **Exercise:**  
  Try to categorize a few types of data you use or see regularly. This is a great way to start thinking like a data scientist!

---
