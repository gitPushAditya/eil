# Machine Learning Framework

## 6 Steps

![img4.png](img4.png)

These are the set of steps that we can follow to create almost any Machine Learning Model.

### 1. Problem Definition

The very first step is to figre out what kind of problem we are dealing with. Is it supervised or unsupervised? Is it regression or classification?

### 2. Data

Second step is to find out what kind of data we have. Based on the problem, there are different kinds of data - structured like exel and csv files, unstructured like images, audio and videos, etc.

### 3. Evaluation

This step is to decide - "What defines success for us?" Since Machine Learning Models can be improved again and again, we need to define a threshold for success.

### 4. Features

What do we already know about the data? For e.g. If wew are dealing with data of patient with heart disease, then body weight, age, blood pressure, etc. are features that we already know.

### 5. Modelling

Once we have our problem and data, it's time to start building our ML model. Based on the type of problem we have, we can choose from different types of inbuilt models.

### 6. Experimentation

This step is about asking "How can we improve our model?" or if it's not working as desired then "What can we try next?"

---

## 1. Problem Definition

There are different categories foof Ml, and based on those categories, we are are going to define our problem as one of them.

### a. Supervised Learning

In supervised learning we have data and label(result) and model tries to predict the label based on the data. If it fails, the algorithm is corrected and tries again.

Main types of supervised learning are:

- Regression
- Classification

#### i. Regression

In regression, we predict a continuous value. For example, predicting the price of a house based on its features like size, location, etc. These numbers can only go up or down based on data.

#### ii. Classification

In classification, we predict a categorical value(one thing or another). For example, predicting the gender of a person based on their features.

There are two types of classification:

- Binary Classification - Two classes or options
- Multi-Class Classification - More than two classes or options

### b. Unsupervised Learning

In unsupervised learning we have data and no label(result). Model tries to find patterns and relationships in the data.

E.g. Clustering different kind of items bought together at amazon and suggesting customers based on that. Suggesting music based on user's listening history.

### c. Transfer Learning

In transfer learning we use a pre-trained model and fine-tune it on a new task. This can save a lot of time and resources.

For example, using a pre-trained model to classify images of cats and dogs and then fine-tuning it on a new task of classifying images of birds.

### d. Reinforcement Learning

In reinforcement learning we have data and no label(result). Model tries to learn from experience(by doing something and getting a reward for success). The goal of reinforcement learning is to maximize rewards over time.

E.g. Training a robot to play a game and getting a reward for success and punishing the robot for failure.

---

## 2. Data

There are basically two types of data:

- Structured
- Unstructured

### a. Structured

Structured data is data that is structured in a way that it can be easily understood and interpreted. These are mostly tabular data, like Excel or CSV files.

E.g. Data of patients with heart disease and their features like body weight, age, blood pressure, etc.

### b. Unstructured 

Unstructured data is data that is not structured in a way that it can be easily understood and interpreted. These data are mostly images, videos, audio, etc.

E.g. Data of images of cats and dogs and then doing some classification on it.


Within these two data types, there are two different kinds of data:

#### i. Static
Data that does not change over time. 

E.g. Data of patients with heart disease and their features like body weight, age, blood pressure, etc.

#### ii. Streaming
Data that changes over time. 

E.g. Change of stock prices based on news events. 

---

## 3. Evaluation

This step is to decide - "What defines success for us?"

There can be several ways to define success based on what we are trying to achieve.

### a. Accuracy

Accuracy is the percentage of correct predictions. Let's say if we are creating a model to determine if a patient has heart disease or not, then high accuracy is our top priority. 

### b. Precision 

Precision is about how close we are from the actual result. Let's say if we are creating a model to predict the pricing of a house, then high precision is our top priority. 

### c. Recall 

Recall is used when missing a positive case has big consequence in comparison to false positive. Let's say we are creating a model to diagnose cancer patients, then getting a false positive is okay but missing a cancer patient can have big consequence.

---

## 4. Features

This step is to ask - "What do we already know about the data?" 

Features are the different type of data that is available(variables) to us. E.g. If wew are dealing with data of patient with heart disease, then body weight, age, blood pressure, etc. are called features or feature variables and final output(if the patient has heart disease or not) is called target variable.  


Features can be of different types:

### a. Numerical

When data is numerical, for example, body weight, age, blood pressure, etc. are called numerical features.

### b. Categorical

When data is categorical, meaning it's either one or another, for example, gender of a person, disease type, etc. are called categorical features.

### c. Derived

When data is derived from other features, for example, if a person has a heart disease, then he/she has a high blood pressure, etc. are called derived features.

---

## 5. Modelling

Modelling is the process of creating a model based on the data and features that we have.

It can be divided into three steps:

1. Choosing and training a model
2. Tuning a model
3. Model comparison

But before training any model, there is an important thing that we need to do - Data Splitting.

We split our data into three groups: 
- Training data - to train out model
- Validation data - to fine tune our model
- Testing data - to test our model

### a. Choosing and training a model




