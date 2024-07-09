# Anaconda Setup

Working with data science and AI requires many packages, softwares and libraries. To manage all of that in one place we install a managing software like Anaconda or Mini-conda.

Anaconda comes with many advanced softwares and packages and is quite large in size(around 3 GB) while mini-conda only comes with basic setup and in around 200 MB in size.

The both use a package manager called conda which is similar to pyp, or npm, or pub.

We are also going to use conda to create virtual environments.

## Windows Setup

Download Your Anaconda Setup from here - https://www.anaconda.com/download

Download Your Mini-conda from here - https://docs.anaconda.com/miniconda/miniconda-install/

_For this, I am using Anaconda._

Once the installation is complete, you will have a software installed in your pc with name 'Anaconda Powershell Prompt'.

Open it and type - `conda list`, if this shows some sort of list and not error that means Anaconda is successfully installed in your computer.

# Creating First Project File

From Anaconda Prompt, you need to navigate to your preferred folder. Here are some basic command lines that can help you with that -

## Basic Command Lines

- Go to a subdirectory - `cd directory_name`
- Change drive - `cd E:\`
- Go a directory back - `cd..`
- Create a new directory - `mkdir directory_name`

You can use these commands to navigate to a directory(or create one) where you want to store all your projects.

Inside that, let's create our very first sample_project directory -

## Setting Up Project File

In Anaconda Prompt, write the following command to create a project directory.

`mkdir sample_project_1` then to get into it `cd sample_project_1`

Right now, this only created an empty directory. To create our environment and add all the packages that we need, we are gonna have run this command -

`conda create --prefix ./env pandas numpy matplotlib scikit-learn `

This will ask you for permission to download multiple files and packages - just press y and then enter.

Entire process will take a while based on your internet connection. After it's finished, if you navigate to your project folder, you will see another subfolder with name 'env'. This folder contains all the packages that you need to start working with data science.

But before we close our prompt, we also need to activate our environment. If you see at the end of the downloads in prompt window, conda gives you suggestions for what to run.

For me, it's -

`conda activate E:\Machine_Learning\sample_project_1\env`

And when we are done with the project, we will use `conda deactivate` to deactivate our virtual environment.

_Every time you create a new project, you are gonna have to repeat this process._

## Install a New Package

When we created our environment, we installed multiple packages like pandas, numpy etc. but what if we forgot to install something. In that case we can install it later using conda.

For eg. we didn't install jupyter notebook, to install it, we are just going to run -

`conda install jupyter`

It will ask for yes and no, just press y and enter. And now we know how to install a new package in our environment.

To check the installation, just type `jupyter notebook`. This will load a new browser tab with your jupyter folder open in it.

## Create a New Jupyter Notebook File

In your jupyter home page, click on 'New' and then on 'Notebook'. This will create an new python 3 jupyter notebook.

Now, to finally check our environment and all the packages in it, let's try importing them.

In a shell of jupyter notebook, type the following command -

```python
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import sklearn
```

And then click the run button(a small play button on the top or Shift+Enter), if this doesn't show any errors, it means we are successful(fingers crossed).

Now, we are finally ready to dive into more details.

Press Ctrl + C if you want to close the kernel and then use `conda deactivate` to deactivate the environment.

## Share Your Project

There are two ways to share your project - 

1. Share the entire project folder which contains all the packages that we need to work.

2. Entire package can be quite big sometimes, in that case, there is second way, export it as .yml file that will contain all the information that is needed to install all the packages.

To export, in powershell write - `conda env export --prefix E:\Machine_Learning\sample_project_1\env > environment.yml`

Now to create environment from yml file, run - `conda env create --file environment.yml --name env_from_file`


---
