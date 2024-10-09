# **Keywords and Identifiers**

In this section, we will cover two fundamental concepts of C programming: **Keywords** and **Identifiers**. Understanding these will help you write syntactically correct programs.

---

## **1. Keywords**

### **What are Keywords?**

- Keywords are reserved words in the C language that have predefined meanings.
- These words cannot be used as identifiers (e.g., variable names, function names).
- They tell the compiler to perform specific tasks and thus hold special significance.

### **List of Common Keywords in C:**

| Keyword    | Description                                                                              |
| ---------- | ---------------------------------------------------------------------------------------- |
| `auto`     | Declares automatic (local) variables, default for variables.                             |
| `break`    | Exits from a loop or switch statement.                                                   |
| `case`     | Defines a case in a switch statement.                                                    |
| `char`     | Declares a character data type.                                                          |
| `const`    | Declares a variable as constant (its value cannot change).                               |
| `continue` | Skips the rest of the current loop iteration.                                            |
| `default`  | Defines the default block in a switch statement.                                         |
| `do`       | Used in the `do-while` loop.                                                             |
| `double`   | Declares a double-precision floating-point variable.                                     |
| `else`     | Specifies the block to execute if the `if` condition is false.                           |
| `enum`     | Defines an enumeration (a set of named integer constants).                               |
| `extern`   | Declares a variable that is defined elsewhere.                                           |
| `float`    | Declares a floating-point variable.                                                      |
| `for`      | Defines a `for` loop.                                                                    |
| `goto`     | Transfers control to a labeled statement.                                                |
| `if`       | Used for conditional execution of statements.                                            |
| `int`      | Declares an integer variable.                                                            |
| `long`     | Declares a long integer variable.                                                        |
| `return`   | Exits a function and optionally returns a value.                                         |
| `short`    | Declares a short integer variable.                                                       |
| `signed`   | Declares a signed data type.                                                             |
| `sizeof`   | Returns the size of a data type or variable.                                             |
| `static`   | Declares a variable with local persistence across function calls.                        |
| `struct`   | Defines a structure (a collection of variables).                                         |
| `switch`   | Implements a switch-case block for multiple conditions.                                  |
| `typedef`  | Defines a new data type name.                                                            |
| `union`    | Defines a union (a data type that can hold different types in the same memory location). |
| `unsigned` | Declares an unsigned data type (only positive values).                                   |
| `void`     | Specifies that a function returns no value.                                              |
| `volatile` | Declares a variable that can be changed unexpectedly.                                    |
| `while`    | Defines a while loop.                                                                    |

---

## **2. Identifiers**

### **What are Identifiers?**

- Identifiers are the names given to various programming elements like variables, functions, arrays, structures, etc.
- These names are created by the programmer and should follow certain rules.
-

### **Rules for Naming Identifiers**:

1. **Valid Characters**: Identifiers can contain letters (both uppercase and lowercase), digits (0-9), and underscores (`_`).
2. **Cannot Start with a Digit**: Identifiers must begin with a letter or an underscore, but they **cannot** start with a digit.

   - Valid: `myVariable`, `_count`, `score2`
   - Invalid: `2variable`, `99_total`

3. **No Spaces Allowed**: Spaces are not allowed in identifiers. Use underscores (`_`) or camel case to separate words in an identifier.

   - Valid: `total_score`, `myVarName`
   - Invalid: `total score`, `my Var Name`

4. **Cannot Use Keywords**: Keywords such as `int`, `float`, `if`, and `else` cannot be used as identifiers.

   - Invalid: `int = 5;` (cannot use `int` as a variable name)

5. **Case-Sensitive**: Identifiers in C are case-sensitive, meaning `myVar` and `MyVar` are considered different.

### **Examples of Valid and Invalid Identifiers**:

| Valid Identifiers | Invalid Identifiers |
| ----------------- | ------------------- |
| `age`             | `2ndPlace`          |
| `my_function`     | `my function`       |
| `_count`          | `float` (keyword)   |
| `var123`          | `var 123`           |

---

## **Example Program Using Keywords and Identifiers**

```c
#include <stdio.h>   // Keyword: Includes the standard input-output library

int main() {         // Keyword: 'int' defines the return type of main function
    int age = 21;    // 'age' is a valid identifier (variable)
    float height = 5.9; // 'height' is another identifier, type 'float' is a keyword

    printf("Age: %d\n", age);     // Using printf to output age
    printf("Height: %.1f\n", height);  // Using printf to output height

    return 0;        // Keyword: 'return' indicates the function ended successfully
}
```

## **Explanation**:

- **Keywords**: `int`, `float`, `return`, `main`
- **Identifiers**: `age`, `height`, `main`, `printf`

---

## **3. Best Practices for Naming Identifiers**

1. **Be Descriptive**: Use meaningful names that clearly describe the purpose of the identifier.
   - Instead of `x`, use `totalAmount`.
2. **Follow a Consistent Naming Convention**:

   - Use **camelCase**: `totalAmount`, `userAge`
   - Use **snake_case**: `total_amount`, `user_age`

3. **Avoid Starting with an Underscore**: While valid, starting with an underscore is generally discouraged unless writing low-level or system programming code.

4. **Keep It Simple and Concise**: Names should be descriptive but not overly long.
   - Bad: `count_of_total_users_who_registered_last_year`
   - Good: `totalUsers`

---
