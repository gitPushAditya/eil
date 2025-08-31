# JavaScript

## Introduction to JavaScript

JavaScript is a high-level, interpreted programming language that is one of the core technologies of the World Wide Web. It enables dynamic and interactive web pages and is used for both client-side and server-side development.

---

## JavaScript Syntax

### Variables

```javascript
// ES6+ (Modern JavaScript)
let name = "John";
const age = 25;
var city = "New York"; // Avoid using var

// Data Types
let string = "Hello";
let number = 42;
let boolean = true;
let array = [1, 2, 3, 4, 5];
let object = { name: "John", age: 25 };
let nullValue = null;
let undefinedValue = undefined;
```

---

## Functions

### Function Declaration

```javascript
function greet(name) {
    return `Hello, ${name}!`;
}

// Function Expression
const greet = function(name) {
    return `Hello, ${name}!`;
};

// Arrow Function (ES6)
const greet = (name) => `Hello, ${name}!`;
```

---

## Control Structures

### Conditional Statements

```javascript
if (age >= 18) {
    console.log("Adult");
} else if (age >= 13) {
    console.log("Teenager");
} else {
    console.log("Child");
}

// Ternary Operator
const status = age >= 18 ? "Adult" : "Minor";

// Switch Statement
switch (day) {
    case "Monday":
        console.log("Start of work week");
        break;
    case "Friday":
        console.log("TGIF!");
        break;
    default:
        console.log("Regular day");
}
```

### Loops

```javascript
// For Loop
for (let i = 0; i < 5; i++) {
    console.log(i);
}

// While Loop
let count = 0;
while (count < 5) {
    console.log(count);
    count++;
}

// For...of Loop (Arrays)
const fruits = ["apple", "banana", "orange"];
for (const fruit of fruits) {
    console.log(fruit);
}

// For...in Loop (Objects)
const person = { name: "John", age: 25 };
for (const key in person) {
    console.log(`${key}: ${person[key]}`);
}
```

---

## Objects and Arrays

### Arrays

```javascript
const numbers = [1, 2, 3, 4, 5];

// Array Methods
numbers.push(6);           // Add to end
numbers.pop();             // Remove from end
numbers.unshift(0);        // Add to beginning
numbers.shift();           // Remove from beginning

// Higher-order methods
const doubled = numbers.map(num => num * 2);
const evens = numbers.filter(num => num % 2 === 0);
const sum = numbers.reduce((acc, num) => acc + num, 0);
```

### Objects

```javascript
const person = {
    name: "John",
    age: 25,
    greet: function() {
        return `Hello, I'm ${this.name}`;
    }
};

// Object methods
console.log(Object.keys(person));
console.log(Object.values(person));
console.log(Object.entries(person));
```

---

## ES6+ Features

### Destructuring

```javascript
// Array Destructuring
const [first, second, ...rest] = [1, 2, 3, 4, 5];

// Object Destructuring
const { name, age } = person;
```

### Template Literals

```javascript
const greeting = `Hello, ${name}! You are ${age} years old.`;
```

### Spread Operator

```javascript
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5, 6];

const obj1 = { a: 1, b: 2 };
const obj2 = { ...obj1, c: 3 };
```

---

## Asynchronous JavaScript

### Promises

```javascript
const fetchData = () => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve("Data fetched successfully");
        }, 1000);
    });
};

fetchData()
    .then(data => console.log(data))
    .catch(error => console.error(error));
```

### Async/Await

```javascript
async function fetchUserData() {
    try {
        const response = await fetch('/api/user');
        const userData = await response.json();
        return userData;
    } catch (error) {
        console.error('Error fetching user data:', error);
    }
}
```

---

## Error Handling

```javascript
try {
    // Code that might throw an error
    const result = riskyOperation();
    console.log(result);
} catch (error) {
    console.error('An error occurred:', error.message);
} finally {
    console.log('This always runs');
}
```

---

## Best Practices

1. Use `const` and `let` instead of `var`
2. Use meaningful variable and function names
3. Follow consistent code formatting
4. Handle errors properly with try-catch
5. Use modern ES6+ features for cleaner code
6. Avoid global variables
7. Use strict mode (`'use strict';`)
8. Comment your code appropriately

---