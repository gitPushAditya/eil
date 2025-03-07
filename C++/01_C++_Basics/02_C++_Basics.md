# C++ Basics

## Syntax and Structure 

```cpp
# include <iostream> // Header file for input and output

using namespace std; // Allows usage of standard functions without std::

int main(){ // Main Function - Execution starts here
    cout << "Hello World!" << endl; // Output statement
    return 0; // return 0, indicating successful execution
}
```

---

## Input/Output

### Output with `cout`  

Syntax - 
```cpp
cout << "Hello World!";
```


Example-
```cpp
#include <iostream>
using namespace std;

int main(){
    cout << "Hello World!" << endl;
    cout << "welcome to C++ programming." << endl;
    return 0;
}
```

### Input with `cin`

Syntax-
```cpp
cin >> variable;
```

Example-
```cpp
#include <iostream>
using namespace std;

int main(){
    int age;
    cout << "Enter your age: ";
    cin >> age;
    cout << "You are " << age << "years old." << endl;
    return 0;
}
```

### Multiple Inputs

```cpp
cin >> name >> age;
```

### Take entire line as input

By default, cin only reads the first word. Use getline() to get entire line including spaces. 

```cpp
getline(cin, fullName);
```

---

## Variables and Data Types

### Variable 

A variable is a name given to a memory location that stores a value. 

Syntax-
```cpp
data_type variable_name = value;
```

Example-
```cpp
int age = 25;
```

### Rules for variables:
- Must start with a letter or underscore
- Can contain letters, digits, and underscores.
- Cannot be a C++ keyword
- Case-Sensitive.


### Data Types in C++

```cpp

int number = 10;

float decimalNumber = 3.14;

double bigDecimal = 3.14159265;

char singleCharacter = 'A';

bool rightOrWrong = true;

string words = "Hello"

```







