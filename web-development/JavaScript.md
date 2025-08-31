# JavaScript - Modern ES6+ Programming

JavaScript is a versatile programming language for web development. This guide covers modern ES6+ features, async programming, functional programming, and production-ready patterns.

## Setup and Environment

### Modern JavaScript Environment

```bash
# Node.js project setup
npm init -y
npm install --save-dev @babel/core @babel/preset-env @babel/cli
npm install --save-dev eslint prettier
npm install --save-dev jest @testing-library/jest-dom

# Package.json scripts
{
  "scripts": {
    "start": "node src/index.js",
    "dev": "nodemon src/index.js",
    "build": "babel src -d dist",
    "test": "jest",
    "lint": "eslint src/**/*.js",
    "format": "prettier --write src/**/*.js"
  }
}
```

### ESLint Configuration (.eslintrc.js)

```javascript
module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true,
    jest: true
  },
  extends: [
    'eslint:recommended'
  ],
  parserOptions: {
    ecmaVersion: 2022,
    sourceType: 'module'
  },
  rules: {
    'no-console': 'warn',
    'no-unused-vars': 'error',
    'prefer-const': 'error',
    'no-var': 'error',
    'arrow-spacing': 'error',
    'object-curly-spacing': ['error', 'always'],
    'indent': ['error', 2],
    'quotes': ['error', 'single'],
    'semi': ['error', 'always']
  }
};
```

---

## Variables and Data Types

### Modern Variable Declarations

```javascript
// Avoid var - use const and let
const API_URL = 'https://api.example.com'; // Immutable reference
let currentUser = null; // Mutable variable
let userCount = 0; // Mutable primitive

// Destructuring Assignment
const user = {
  name: 'John Doe',
  email: 'john@example.com',
  address: {
    city: 'New York',
    country: 'USA'
  }
};

// Object destructuring
const { name, email } = user;
const { city, country } = user.address;

// Array destructuring
const colors = ['red', 'green', 'blue'];
const [primary, secondary, tertiary] = colors;

// Rest operator
const { name: userName, ...userDetails } = user;
const [first, ...rest] = colors;

// Default values
const { age = 25, name: fullName = 'Anonymous' } = user;

// Nested destructuring
const { address: { city: userCity } } = user;
```

### Data Types and Type Checking

```javascript
// Primitive types
const str = 'Hello World';
const num = 42;
const bool = true;
const undef = undefined;
const nul = null;
const sym = Symbol('id');
const bigInt = 123n;

// Type checking
console.log(typeof str);        // 'string'
console.log(typeof num);        // 'number'
console.log(typeof bool);       // 'boolean'
console.log(typeof undef);      // 'undefined'
console.log(typeof nul);        // 'object' (JS quirk)
console.log(typeof sym);        // 'symbol'
console.log(typeof bigInt);     // 'bigint'

// Better type checking
function getType(value) {
  return Object.prototype.toString.call(value).slice(8, -1).toLowerCase();
}

console.log(getType(null));       // 'null'
console.log(getType([]));         // 'array'
console.log(getType({}));         // 'object'
console.log(getType(new Date())); // 'date'

// Type guards
function isString(value) {
  return typeof value === 'string';
}

function isArray(value) {
  return Array.isArray(value);
}

function isObject(value) {
  return value !== null && typeof value === 'object' && !Array.isArray(value);
}
```

---

## Functions and Arrow Functions

### Modern Function Syntax

```javascript
// Traditional function declaration
function calculateArea(length, width) {
  return length * width;
}

// Function expression
const calculateVolume = function(length, width, height) {
  return length * width * height;
};

// Arrow functions
const multiply = (a, b) => a * b;
const square = x => x * x;
const greet = () => 'Hello World';

// Arrow function with block body
const processUser = (user) => {
  const processed = {
    ...user,
    fullName: `${user.firstName} ${user.lastName}`,
    isActive: true
  };
  return processed;
};

// Higher-order functions
const createMultiplier = (factor) => (value) => value * factor;
const double = createMultiplier(2);
const triple = createMultiplier(3);

console.log(double(5)); // 10
console.log(triple(4)); // 12

// Function with default parameters
const createUser = (name, email, role = 'user', active = true) => ({
  name,
  email,
  role,
  active,
  createdAt: new Date()
});

// Rest parameters
const sum = (...numbers) => numbers.reduce((acc, num) => acc + num, 0);
console.log(sum(1, 2, 3, 4, 5)); // 15

// Spread operator with functions
const numbers = [1, 2, 3, 4, 5];
console.log(Math.max(...numbers)); // 5

// Function composition
const compose = (...fns) => (value) => fns.reduceRight((acc, fn) => fn(acc), value);
const pipe = (...fns) => (value) => fns.reduce((acc, fn) => fn(acc), value);

const addOne = x => x + 1;
const multiplyByTwo = x => x * 2;
const square = x => x * x;

const composedFn = compose(square, multiplyByTwo, addOne);
const pipedFn = pipe(addOne, multiplyByTwo, square);

console.log(composedFn(3)); // ((3 + 1) * 2)² = 64
console.log(pipedFn(3));    // ((3 + 1) * 2)² = 64
```

### Advanced Function Patterns

```javascript
// Currying
const curry = (fn) => {
  return function curried(...args) {
    if (args.length >= fn.length) {
      return fn.apply(this, args);
    } else {
      return function(...nextArgs) {
        return curried.apply(this, args.concat(nextArgs));
      };
    }
  };
};

const add = (a, b, c) => a + b + c;
const curriedAdd = curry(add);

console.log(curriedAdd(1)(2)(3)); // 6
console.log(curriedAdd(1, 2)(3)); // 6
console.log(curriedAdd(1)(2, 3)); // 6

// Partial application
const partial = (fn, ...args1) => {
  return (...args2) => fn(...args1, ...args2);
};

const multiply = (a, b, c) => a * b * c;
const multiplyByTwo = partial(multiply, 2);

console.log(multiplyByTwo(3, 4)); // 24

// Memoization
const memoize = (fn) => {
  const cache = new Map();
  return (...args) => {
    const key = JSON.stringify(args);
    if (cache.has(key)) {
      return cache.get(key);
    }
    const result = fn(...args);
    cache.set(key, result);
    return result;
  };
};

const fibonacci = memoize((n) => {
  if (n <= 1) return n;
  return fibonacci(n - 1) + fibonacci(n - 2);
});

// Debounce and Throttle
const debounce = (func, delay) => {
  let timeoutId;
  return (...args) => {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
};

const throttle = (func, limit) => {
  let inThrottle;
  return (...args) => {
    if (!inThrottle) {
      func.apply(this, args);
      inThrottle = true;
      setTimeout(() => inThrottle = false, limit);
    }
  };
};

// Usage examples
const handleSearch = debounce((query) => {
  console.log('Searching for:', query);
}, 300);

const handleScroll = throttle(() => {
  console.log('Scrolling...');
}, 100);
```

---

## Arrays and Array Methods

### Modern Array Operations

```javascript
// Array creation and manipulation
const fruits = ['apple', 'banana', 'orange'];
const numbers = [1, 2, 3, 4, 5];
const mixed = [1, 'hello', true, null, { id: 1 }];

// Array.from() - Create arrays from iterables
const arrayFromString = Array.from('hello'); // ['h', 'e', 'l', 'l', 'o']
const arrayFromSet = Array.from(new Set([1, 2, 2, 3])); // [1, 2, 3]
const arrayWithLength = Array.from({ length: 5 }, (_, i) => i + 1); // [1, 2, 3, 4, 5]

// Array methods - Immutable operations
const users = [
  { id: 1, name: 'John', age: 25, active: true },
  { id: 2, name: 'Jane', age: 30, active: false },
  { id: 3, name: 'Bob', age: 35, active: true },
  { id: 4, name: 'Alice', age: 28, active: true }
];

// map() - Transform each element
const userNames = users.map(user => user.name);
const userSummaries = users.map(user => ({
  id: user.id,
  summary: `${user.name} (${user.age} years old)`
}));

// filter() - Select elements based on condition
const activeUsers = users.filter(user => user.active);
const youngUsers = users.filter(user => user.age < 30);

// reduce() - Reduce array to single value
const totalAge = users.reduce((sum, user) => sum + user.age, 0);
const usersByAge = users.reduce((acc, user) => {
  acc[user.age] = user;
  return acc;
}, {});

// find() and findIndex()
const userJohn = users.find(user => user.name === 'John');
const johnIndex = users.findIndex(user => user.name === 'John');

// some() and every()
const hasActiveUsers = users.some(user => user.active);
const allUsersActive = users.every(user => user.active);

// sort() - Create sorted copy
const sortedByAge = [...users].sort((a, b) => a.age - b.age);
const sortedByName = [...users].sort((a, b) => a.name.localeCompare(b.name));

// includes() and indexOf()
const hasApple = fruits.includes('apple');
const appleIndex = fruits.indexOf('apple');

// flatMap() and flat()
const nested = [[1, 2], [3, 4], [5, 6]];
const flattened = nested.flat(); // [1, 2, 3, 4, 5, 6]

const sentences = ['Hello world', 'JavaScript is great'];
const words = sentences.flatMap(sentence => sentence.split(' '));
// ['Hello', 'world', 'JavaScript', 'is', 'great']
```

### Advanced Array Patterns

```javascript
// Array chunking
const chunk = (array, size) => {
  const chunks = [];
  for (let i = 0; i < array.length; i += size) {
    chunks.push(array.slice(i, i + size));
  }
  return chunks;
};

const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9];
console.log(chunk(numbers, 3)); // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

// Array deduplication
const removeDuplicates = (array) => [...new Set(array)];
const removeDuplicateObjects = (array, key) => {
  const seen = new Set();
  return array.filter(item => {
    const value = item[key];
    if (seen.has(value)) return false;
    seen.add(value);
    return true;
  });
};

// Array intersection and difference
const intersection = (a, b) => a.filter(x => b.includes(x));
const difference = (a, b) => a.filter(x => !b.includes(x));
const union = (a, b) => [...new Set([...a, ...b])];

// Array grouping
const groupBy = (array, key) => {
  return array.reduce((groups, item) => {
    const group = item[key];
    groups[group] = groups[group] || [];
    groups[group].push(item);
    return groups;
  }, {});
};

const groupedUsers = groupBy(users, 'active');

// Array methods chaining
const result = users
  .filter(user => user.active)
  .map(user => ({ ...user, ageGroup: user.age >= 30 ? 'senior' : 'junior' }))
  .sort((a, b) => a.age - b.age)
  .slice(0, 10);

// Performance considerations
// Use for...of for iteration without transformation
for (const user of users) {
  console.log(user.name);
}

// Use traditional for loop for performance-critical operations
function sumArrayFast(numbers) {
  let sum = 0;
  for (let i = 0; i < numbers.length; i++) {
    sum += numbers[i];
  }
  return sum;
}
```

---

## Objects and Classes

### Object Manipulation

```javascript
// Object creation patterns
const user = {
  name: 'John Doe',
  email: 'john@example.com',
  age: 30
};

// Object.create()
const userPrototype = {
  greet() {
    return `Hello, I'm ${this.name}`;
  },
  getAge() {
    return this.age;
  }
};

const newUser = Object.create(userPrototype);
newUser.name = 'Jane';
newUser.age = 25;

// Object property methods
const keys = Object.keys(user);           // ['name', 'email', 'age']
const values = Object.values(user);       // ['John Doe', 'john@example.com', 30]
const entries = Object.entries(user);     // [['name', 'John Doe'], ...]

// Object.assign() and spread operator
const additionalInfo = { role: 'admin', active: true };
const mergedUser = Object.assign({}, user, additionalInfo);
const spreadUser = { ...user, ...additionalInfo };

// Object.freeze(), Object.seal(), Object.preventExtensions()
const immutableUser = Object.freeze({ ...user });
const sealedUser = Object.seal({ ...user }); // Can modify existing, can't add/remove
const nonExtensible = Object.preventExtensions({ ...user }); // Can modify, can't add

// Property descriptors
Object.defineProperty(user, 'id', {
  value: 123,
  writable: false,
  enumerable: false,
  configurable: false
});

Object.defineProperty(user, 'createdAt', {
  get() {
    return this._createdAt || new Date();
  },
  set(value) {
    this._createdAt = new Date(value);
  },
  enumerable: true,
  configurable: true
});

// Computed property names
const dynamicKey = 'userType';
const userWithDynamicKey = {
  name: 'John',
  [dynamicKey]: 'premium',
  [`${dynamicKey}Level`]: 'gold'
};

// Object methods shorthand
const calculator = {
  // Method shorthand
  add(a, b) {
    return a + b;
  },
  
  // Traditional method
  subtract: function(a, b) {
    return a - b;
  },
  
  // Arrow function (doesn't bind 'this')
  multiply: (a, b) => a * b
};
```

### ES6+ Classes

```javascript
// Basic class definition
class User {
  // Class fields (modern syntax)
  #privateField = 'private';
  
  constructor(name, email) {
    this.name = name;
    this.email = email;
    this.createdAt = new Date();
  }
  
  // Method definition
  greet() {
    return `Hello, I'm ${this.name}`;
  }
  
  // Getter
  get displayName() {
    return this.name.toUpperCase();
  }
  
  // Setter
  set name(value) {
    if (typeof value !== 'string') {
      throw new Error('Name must be a string');
    }
    this._name = value;
  }
  
  get name() {
    return this._name;
  }
  
  // Static method
  static createFromEmail(email) {
    const name = email.split('@')[0];
    return new User(name, email);
  }
  
  // Private method
  #validateEmail(email) {
    return email.includes('@');
  }
  
  updateEmail(email) {
    if (this.#validateEmail(email)) {
      this.email = email;
    }
  }
}

// Class inheritance
class AdminUser extends User {
  constructor(name, email, permissions = []) {
    super(name, email); // Call parent constructor
    this.permissions = permissions;
  }
  
  // Override parent method
  greet() {
    return `${super.greet()} - I'm an admin`;
  }
  
  // Additional methods
  hasPermission(permission) {
    return this.permissions.includes(permission);
  }
  
  addPermission(permission) {
    if (!this.hasPermission(permission)) {
      this.permissions.push(permission);
    }
  }
}

// Usage
const user = new User('John Doe', 'john@example.com');
const admin = new AdminUser('Jane Smith', 'jane@example.com', ['read', 'write']);

console.log(user.greet()); // "Hello, I'm John Doe"
console.log(admin.greet()); // "Hello, I'm Jane Smith - I'm an admin"

// Class as factory pattern
class ApiClient {
  constructor(baseURL, apiKey) {
    this.baseURL = baseURL;
    this.apiKey = apiKey;
  }
  
  async get(endpoint) {
    const response = await fetch(`${this.baseURL}${endpoint}`, {
      headers: {
        'Authorization': `Bearer ${this.apiKey}`,
        'Content-Type': 'application/json'
      }
    });
    return response.json();
  }
  
  async post(endpoint, data) {
    const response = await fetch(`${this.baseURL}${endpoint}`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${this.apiKey}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
    return response.json();
  }
}

// Mixins pattern
const Timestamped = {
  updateTimestamp() {
    this.updatedAt = new Date();
  }
};

const Serializable = {
  toJSON() {
    return { ...this };
  },
  
  fromJSON(data) {
    Object.assign(this, data);
    return this;
  }
};

// Applying mixins
Object.assign(User.prototype, Timestamped, Serializable);
```

---

## Asynchronous JavaScript

### Promises

```javascript
// Creating promises
const createPromise = (value, delay = 1000) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (value) {
        resolve(value);
      } else {
        reject(new Error('Value is falsy'));
      }
    }, delay);
  });
};

// Promise chaining
createPromise('Hello')
  .then(result => {
    console.log(result); // "Hello"
    return result.toUpperCase();
  })
  .then(result => {
    console.log(result); // "HELLO"
    return result + ' WORLD';
  })
  .then(result => {
    console.log(result); // "HELLO WORLD"
  })
  .catch(error => {
    console.error('Error:', error.message);
  })
  .finally(() => {
    console.log('Promise chain completed');
  });

// Promise.all() - Wait for all promises
const promises = [
  createPromise('First', 1000),
  createPromise('Second', 2000),
  createPromise('Third', 500)
];

Promise.all(promises)
  .then(results => {
    console.log(results); // ['First', 'Second', 'Third']
  })
  .catch(error => {
    console.error('One or more promises failed:', error);
  });

// Promise.allSettled() - Wait for all promises regardless of outcome
Promise.allSettled([
  createPromise('Success'),
  createPromise(null), // Will reject
  createPromise('Another success')
])
  .then(results => {
    results.forEach((result, index) => {
      if (result.status === 'fulfilled') {
        console.log(`Promise ${index} fulfilled:`, result.value);
      } else {
        console.log(`Promise ${index} rejected:`, result.reason.message);
      }
    });
  });

// Promise.race() - First to resolve or reject
Promise.race([
  createPromise('Fast', 500),
  createPromise('Slow', 2000)
])
  .then(result => {
    console.log('First to finish:', result); // 'Fast'
  });

// Promise.any() - First to fulfill (ignore rejections)
Promise.any([
  createPromise(null, 100), // Will reject
  createPromise('Success', 200),
  createPromise('Another', 300)
])
  .then(result => {
    console.log('First success:', result); // 'Success'
  })
  .catch(error => {
    console.log('All promises rejected');
  });
```

### Async/Await

```javascript
// Basic async/await
async function fetchUserData(userId) {
  try {
    const response = await fetch(`/api/users/${userId}`);
    
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    
    const user = await response.json();
    return user;
  } catch (error) {
    console.error('Error fetching user:', error);
    throw error;
  }
}

// Sequential vs Parallel execution
async function sequentialExecution() {
  console.time('Sequential');
  
  const user1 = await fetchUserData(1);
  const user2 = await fetchUserData(2);
  const user3 = await fetchUserData(3);
  
  console.timeEnd('Sequential');
  return [user1, user2, user3];
}

async function parallelExecution() {
  console.time('Parallel');
  
  const [user1, user2, user3] = await Promise.all([
    fetchUserData(1),
    fetchUserData(2),
    fetchUserData(3)
  ]);
  
  console.timeEnd('Parallel');
  return [user1, user2, user3];
}

// Async iteration
async function* asyncGenerator() {
  for (let i = 1; i <= 5; i++) {
    await new Promise(resolve => setTimeout(resolve, 1000));
    yield i;
  }
}

async function consumeAsyncGenerator() {
  for await (const value of asyncGenerator()) {
    console.log('Generated:', value);
  }
}

// Error handling patterns
async function robustApiCall(url, retries = 3) {
  for (let i = 0; i < retries; i++) {
    try {
      const response = await fetch(url);
      
      if (response.ok) {
        return await response.json();
      }
      
      throw new Error(`HTTP ${response.status}: ${response.statusText}`);
    } catch (error) {
      console.warn(`Attempt ${i + 1} failed:`, error.message);
      
      if (i === retries - 1) {
        throw error;
      }
      
      // Exponential backoff
      await new Promise(resolve => setTimeout(resolve, Math.pow(2, i) * 1000));
    }
  }
}

// Async utility functions
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

const timeout = (promise, ms) => {
  const timeoutPromise = new Promise((_, reject) => {
    setTimeout(() => reject(new Error('Operation timed out')), ms);
  });
  
  return Promise.race([promise, timeoutPromise]);
};

// Usage
async function example() {
  try {
    const result = await timeout(fetchUserData(1), 5000);
    console.log('User data:', result);
  } catch (error) {
    if (error.message === 'Operation timed out') {
      console.log('Request took too long');
    } else {
      console.log('Other error:', error.message);
    }
  }
}
```

### Fetch API and HTTP Requests

```javascript
// Modern HTTP client class
class HttpClient {
  constructor(baseURL = '', defaultHeaders = {}) {
    this.baseURL = baseURL;
    this.defaultHeaders = {
      'Content-Type': 'application/json',
      ...defaultHeaders
    };
  }
  
  async request(endpoint, options = {}) {
    const url = `${this.baseURL}${endpoint}`;
    const config = {
      headers: { ...this.defaultHeaders, ...options.headers },
      ...options
    };
    
    try {
      const response = await fetch(url, config);
      
      if (!response.ok) {
        throw new HttpError(response.status, response.statusText, await response.text());
      }
      
      const contentType = response.headers.get('content-type');
      if (contentType && contentType.includes('application/json')) {
        return await response.json();
      }
      
      return await response.text();
    } catch (error) {
      if (error instanceof HttpError) {
        throw error;
      }
      throw new HttpError(0, 'Network Error', error.message);
    }
  }
  
  get(endpoint, params = {}) {
    const searchParams = new URLSearchParams(params);
    const url = searchParams.toString() ? `${endpoint}?${searchParams}` : endpoint;
    return this.request(url, { method: 'GET' });
  }
  
  post(endpoint, data) {
    return this.request(endpoint, {
      method: 'POST',
      body: JSON.stringify(data)
    });
  }
  
  put(endpoint, data) {
    return this.request(endpoint, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  }
  
  patch(endpoint, data) {
    return this.request(endpoint, {
      method: 'PATCH',
      body: JSON.stringify(data)
    });
  }
  
  delete(endpoint) {
    return this.request(endpoint, { method: 'DELETE' });
  }
  
  // Upload file
  async upload(endpoint, file, onProgress) {
    const formData = new FormData();
    formData.append('file', file);
    
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      
      xhr.upload.addEventListener('progress', (event) => {
        if (event.lengthComputable && onProgress) {
          const progress = (event.loaded / event.total) * 100;
          onProgress(progress);
        }
      });
      
      xhr.addEventListener('load', () => {
        if (xhr.status >= 200 && xhr.status < 300) {
          resolve(JSON.parse(xhr.responseText));
        } else {
          reject(new HttpError(xhr.status, xhr.statusText));
        }
      });
      
      xhr.addEventListener('error', () => {
        reject(new Error('Upload failed'));
      });
      
      xhr.open('POST', `${this.baseURL}${endpoint}`);
      xhr.send(formData);
    });
  }
}

// Custom error class
class HttpError extends Error {
  constructor(status, statusText, body = '') {
    super(`HTTP ${status}: ${statusText}`);
    this.name = 'HttpError';
    this.status = status;
    this.statusText = statusText;
    this.body = body;
  }
}

// Usage example
const apiClient = new HttpClient('https://api.example.com', {
  'Authorization': 'Bearer your-token'
});

// API service class
class UserService {
  constructor(httpClient) {
    this.http = httpClient;
  }
  
  async getUsers(page = 1, limit = 10) {
    return this.http.get('/users', { page, limit });
  }
  
  async getUserById(id) {
    return this.http.get(`/users/${id}`);
  }
  
  async createUser(userData) {
    return this.http.post('/users', userData);
  }
  
  async updateUser(id, userData) {
    return this.http.put(`/users/${id}`, userData);
  }
  
  async deleteUser(id) {
    return this.http.delete(`/users/${id}`);
  }
  
  async uploadAvatar(userId, file, onProgress) {
    return this.http.upload(`/users/${userId}/avatar`, file, onProgress);
  }
}

// Usage
const userService = new UserService(apiClient);

async function example() {
  try {
    const users = await userService.getUsers(1, 20);
    console.log('Users:', users);
    
    const newUser = await userService.createUser({
      name: 'John Doe',
      email: 'john@example.com'
    });
    console.log('Created user:', newUser);
  } catch (error) {
    if (error instanceof HttpError) {
      console.error(`HTTP Error ${error.status}:`, error.message);
    } else {
      console.error('Unexpected error:', error);
    }
  }
}
```

---

## Modern JavaScript Features

### Modules (ES6+)

```javascript
// math.js - Named exports
export const PI = 3.14159;

export function add(a, b) {
  return a + b;
}

export function multiply(a, b) {
  return a * b;
}

export class Calculator {
  add(a, b) { return a + b; }
  subtract(a, b) { return a - b; }
}

// Default export
export default function divide(a, b) {
  if (b === 0) throw new Error('Division by zero');
  return a / b;
}

// utils.js - Re-exports
export { add, multiply } from './math.js';
export { default as divide } from './math.js';

// user.js - Class export
export default class User {
  constructor(name) {
    this.name = name;
  }
}

// main.js - Importing
import divide, { add, multiply, Calculator, PI } from './math.js';
import User from './user.js';
import * as mathUtils from './math.js';

// Dynamic imports
async function loadModule() {
  const { default: divide, add } = await import('./math.js');
  console.log(divide(10, 2)); // 5
}

// Conditional imports
async function loadUserModule(condition) {
  if (condition) {
    const UserModule = await import('./user.js');
    return new UserModule.default('John');
  }
}
```

### Destructuring and Spread

```javascript
// Advanced destructuring patterns
const user = {
  id: 1,
  name: 'John Doe',
  email: 'john@example.com',
  preferences: {
    theme: 'dark',
    language: 'en',
    notifications: {
      email: true,
      push: false
    }
  },
  tags: ['developer', 'javascript', 'react']
};

// Nested destructuring with renaming
const {
  name: userName,
  preferences: {
    theme,
    notifications: { email: emailNotifications }
  },
  tags: [primaryTag, ...otherTags]
} = user;

// Function parameter destructuring
function createUserCard({ name, email, preferences: { theme } = {} }) {
  return `
    <div class="user-card ${theme}">
      <h3>${name}</h3>
      <p>${email}</p>
    </div>
  `;
}

// Array destructuring with default values
function processCoordinates([x = 0, y = 0, z = 0] = []) {
  return { x, y, z };
}

// Swapping variables
let a = 1, b = 2;
[a, b] = [b, a]; // a = 2, b = 1

// Rest and spread with objects
const { name, ...userWithoutName } = user;
const updatedUser = { ...user, lastLogin: new Date() };

// Spread with arrays
const numbers1 = [1, 2, 3];
const numbers2 = [4, 5, 6];
const combined = [...numbers1, ...numbers2]; // [1, 2, 3, 4, 5, 6]

// Function with rest parameters
function logMessages(level, ...messages) {
  console.log(`[${level}]`, ...messages);
}

logMessages('INFO', 'Server started', 'Port 3000');
```

### Template Literals and Tagged Templates

```javascript
// Basic template literals
const name = 'John';
const age = 30;
const message = `Hello, my name is ${name} and I'm ${age} years old.`;

// Multi-line strings
const htmlTemplate = `
  <div class="user-profile">
    <h2>${name}</h2>
    <p>Age: ${age}</p>
    <p>Status: ${age >= 18 ? 'Adult' : 'Minor'}</p>
  </div>
`;

// Tagged template literals
function highlight(strings, ...values) {
  return strings.reduce((result, string, index) => {
    const value = values[index] ? `<mark>${values[index]}</mark>` : '';
    return result + string + value;
  }, '');
}

const highlighted = highlight`The user ${name} is ${age} years old`;

// SQL template tag
function sql(strings, ...values) {
  let query = '';
  for (let i = 0; i < strings.length; i++) {
    query += strings[i];
    if (i < values.length) {
      // Escape values to prevent SQL injection
      query += typeof values[i] === 'string' 
        ? `'${values[i].replace(/'/g, "''")}'`
        : values[i];
    }
  }
  return query;
}

const userId = 123;
const userName = "John O'Connor";
const query = sql`SELECT * FROM users WHERE id = ${userId} AND name = ${userName}`;

// CSS-in-JS template tag
function css(strings, ...values) {
  return strings.reduce((result, string, index) => {
    const value = values[index] || '';
    return result + string + value;
  }, '');
}

const primaryColor = '#007bff';
const styles = css`
  .button {
    background-color: ${primaryColor};
    border: 1px solid ${primaryColor};
    color: white;
  }
`;
```

### Symbols and Iterators

```javascript
// Symbols for unique property keys
const ID_SYMBOL = Symbol('id');
const NAME_SYMBOL = Symbol('name');

const user = {
  [ID_SYMBOL]: 123,
  [NAME_SYMBOL]: 'John',
  email: 'john@example.com'
};

console.log(user[ID_SYMBOL]); // 123
console.log(Object.keys(user)); // ['email'] - symbols are not enumerable

// Well-known symbols
class CustomIterable {
  constructor(data) {
    this.data = data;
  }
  
  [Symbol.iterator]() {
    let index = 0;
    const data = this.data;
    
    return {
      next() {
        if (index < data.length) {
          return { value: data[index++], done: false };
        }
        return { done: true };
      }
    };
  }
  
  [Symbol.toPrimitive](hint) {
    if (hint === 'number') {
      return this.data.length;
    }
    if (hint === 'string') {
      return this.data.join(', ');
    }
    return this.data.length;
  }
}

const iterable = new CustomIterable([1, 2, 3, 4, 5]);

// Using the iterator
for (const value of iterable) {
  console.log(value);
}

// Using Symbol.toPrimitive
console.log(+iterable); // 5 (length)
console.log(`${iterable}`); // "1, 2, 3, 4, 5"

// Custom async iterator
class AsyncRange {
  constructor(start, end, delay = 100) {
    this.start = start;
    this.end = end;
    this.delay = delay;
  }
  
  [Symbol.asyncIterator]() {
    let current = this.start;
    const end = this.end;
    const delay = this.delay;
    
    return {
      async next() {
        if (current <= end) {
          await new Promise(resolve => setTimeout(resolve, delay));
          return { value: current++, done: false };
        }
        return { done: true };
      }
    };
  }
}

// Usage
async function useAsyncIterator() {
  for await (const value of new AsyncRange(1, 5, 500)) {
    console.log(value);
  }
}
```

---

## Error Handling and Debugging

### Modern Error Handling

```javascript
// Custom error classes
class ValidationError extends Error {
  constructor(message, field) {
    super(message);
    this.name = 'ValidationError';
    this.field = field;
  }
}

class NetworkError extends Error {
  constructor(message, status, url) {
    super(message);
    this.name = 'NetworkError';
    this.status = status;
    this.url = url;
  }
}

// Error handling utility
class ErrorHandler {
  static handle(error) {
    console.error('Error occurred:', error);
    
    if (error instanceof ValidationError) {
      this.handleValidationError(error);
    } else if (error instanceof NetworkError) {
      this.handleNetworkError(error);
    } else {
      this.handleUnknownError(error);
    }
  }
  
  static handleValidationError(error) {
    console.log(`Validation failed for field: ${error.field}`);
    // Show user-friendly validation message
  }
  
  static handleNetworkError(error) {
    console.log(`Network error ${error.status} at ${error.url}`);
    // Show network error message, maybe retry button
  }
  
  static handleUnknownError(error) {
    console.log('Unknown error occurred');
    // Log to error reporting service
  }
}

// Try-catch with specific error types
async function processUserData(userData) {
  try {
    validateUserData(userData);
    const result = await saveUserData(userData);
    return result;
  } catch (error) {
    if (error instanceof ValidationError) {
      // Handle validation errors
      throw new Error(`Invalid user data: ${error.message}`);
    } else if (error instanceof NetworkError) {
      // Handle network errors
      throw new Error('Failed to save user data. Please try again.');
    } else {
      // Handle unexpected errors
      console.error('Unexpected error:', error);
      throw new Error('An unexpected error occurred.');
    }
  }
}

// Error boundaries for functions
function withErrorBoundary(fn) {
  return async function(...args) {
    try {
      return await fn.apply(this, args);
    } catch (error) {
      ErrorHandler.handle(error);
      throw error;
    }
  };
}

// Usage
const safeProcessUserData = withErrorBoundary(processUserData);

// Assertion utility
function assert(condition, message) {
  if (!condition) {
    throw new Error(`Assertion failed: ${message}`);
  }
}

// Usage in functions
function divide(a, b) {
  assert(typeof a === 'number', 'First argument must be a number');
  assert(typeof b === 'number', 'Second argument must be a number');
  assert(b !== 0, 'Cannot divide by zero');
  
  return a / b;
}

// Result pattern for error handling
class Result {
  constructor(value, error) {
    this.value = value;
    this.error = error;
  }
  
  static success(value) {
    return new Result(value, null);
  }
  
  static failure(error) {
    return new Result(null, error);
  }
  
  isSuccess() {
    return this.error === null;
  }
  
  isFailure() {
    return this.error !== null;
  }
  
  map(fn) {
    if (this.isSuccess()) {
      try {
        return Result.success(fn(this.value));
      } catch (error) {
        return Result.failure(error);
      }
    }
    return this;
  }
  
  flatMap(fn) {
    if (this.isSuccess()) {
      try {
        return fn(this.value);
      } catch (error) {
        return Result.failure(error);
      }
    }
    return this;
  }
}

// Using Result pattern
async function fetchUserResult(id) {
  try {
    const user = await fetchUser(id);
    return Result.success(user);
  } catch (error) {
    return Result.failure(error);
  }
}

const userResult = await fetchUserResult(123);

if (userResult.isSuccess()) {
  console.log('User:', userResult.value);
} else {
  console.error('Error:', userResult.error.message);
}
```

### Debugging Techniques

```javascript
// Console debugging utilities
const debug = {
  log: (...args) => {
    if (process.env.NODE_ENV === 'development') {
      console.log('[DEBUG]', ...args);
    }
  },
  
  table: (data) => {
    if (process.env.NODE_ENV === 'development') {
      console.table(data);
    }
  },
  
  time: (label) => {
    if (process.env.NODE_ENV === 'development') {
      console.time(label);
    }
  },
  
  timeEnd: (label) => {
    if (process.env.NODE_ENV === 'development') {
      console.timeEnd(label);
    }
  },
  
  trace: () => {
    if (process.env.NODE_ENV === 'development') {
      console.trace();
    }
  }
};

// Performance monitoring
function measurePerformance(fn, name) {
  return function(...args) {
    const start = performance.now();
    const result = fn.apply(this, args);
    const end = performance.now();
    
    debug.log(`${name} took ${end - start} milliseconds`);
    return result;
  };
}

// Memory usage monitoring
function logMemoryUsage() {
  if (typeof process !== 'undefined' && process.memoryUsage) {
    const usage = process.memoryUsage();
    debug.log('Memory usage:', {
      rss: `${Math.round(usage.rss / 1024 / 1024)} MB`,
      heapTotal: `${Math.round(usage.heapTotal / 1024 / 1024)} MB`,
      heapUsed: `${Math.round(usage.heapUsed / 1024 / 1024)} MB`,
      external: `${Math.round(usage.external / 1024 / 1024)} MB`
    });
  }
}

// Function call tracing
function trace(target, property, descriptor) {
  const originalMethod = descriptor.value;
  
  descriptor.value = function(...args) {
    debug.log(`Calling ${property} with args:`, args);
    const result = originalMethod.apply(this, args);
    debug.log(`${property} returned:`, result);
    return result;
  };
  
  return descriptor;
}

// Usage as decorator (if using TypeScript or Babel)
class Calculator {
  @trace
  add(a, b) {
    return a + b;
  }
}
```

---

## Real-World Example: Task Management System

```javascript
// Task Management System with modern JavaScript

// Base classes and utilities
class EventEmitter {
  constructor() {
    this.events = {};
  }
  
  on(event, callback) {
    if (!this.events[event]) {
      this.events[event] = [];
    }
    this.events[event].push(callback);
  }
  
  emit(event, data) {
    if (this.events[event]) {
      this.events[event].forEach(callback => callback(data));
    }
  }
  
  off(event, callback) {
    if (this.events[event]) {
      this.events[event] = this.events[event].filter(cb => cb !== callback);
    }
  }
}

// Task model
class Task {
  #id;
  #createdAt;
  
  constructor(title, description = '', priority = 'medium') {
    this.#id = Task.generateId();
    this.title = title;
    this.description = description;
    this.priority = priority;
    this.completed = false;
    this.#createdAt = new Date();
    this.updatedAt = new Date();
    this.tags = [];
    this.dueDate = null;
  }
  
  static generateId() {
    return Math.random().toString(36).substr(2, 9);
  }
  
  get id() {
    return this.#id;
  }
  
  get createdAt() {
    return this.#createdAt;
  }
  
  markCompleted() {
    this.completed = true;
    this.updatedAt = new Date();
  }
  
  markIncomplete() {
    this.completed = false;
    this.updatedAt = new Date();
  }
  
  updateTitle(title) {
    this.title = title;
    this.updatedAt = new Date();
  }
  
  updateDescription(description) {
    this.description = description;
    this.updatedAt = new Date();
  }
  
  addTag(tag) {
    if (!this.tags.includes(tag)) {
      this.tags.push(tag);
      this.updatedAt = new Date();
    }
  }
  
  removeTag(tag) {
    this.tags = this.tags.filter(t => t !== tag);
    this.updatedAt = new Date();
  }
  
  setDueDate(date) {
    this.dueDate = new Date(date);
    this.updatedAt = new Date();
  }
  
  isOverdue() {
    return this.dueDate && new Date() > this.dueDate && !this.completed;
  }
  
  toJSON() {
    return {
      id: this.id,
      title: this.title,
      description: this.description,
      priority: this.priority,
      completed: this.completed,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt,
      tags: this.tags,
      dueDate: this.dueDate
    };
  }
  
  static fromJSON(data) {
    const task = new Task(data.title, data.description, data.priority);
    Object.assign(task, {
      completed: data.completed,
      updatedAt: new Date(data.updatedAt),
      tags: data.tags || [],
      dueDate: data.dueDate ? new Date(data.dueDate) : null
    });
    return task;
  }
}

// Task manager
class TaskManager extends EventEmitter {
  constructor() {
    super();
    this.tasks = new Map();
    this.filters = {
      status: 'all', // 'all', 'active', 'completed'
      priority: 'all', // 'all', 'high', 'medium', 'low'
      tags: [],
      search: ''
    };
  }
  
  addTask(title, description, priority) {
    const task = new Task(title, description, priority);
    this.tasks.set(task.id, task);
    this.emit('taskAdded', task);
    return task;
  }
  
  removeTask(id) {
    const task = this.tasks.get(id);
    if (task) {
      this.tasks.delete(id);
      this.emit('taskRemoved', task);
    }
    return task;
  }
  
  getTask(id) {
    return this.tasks.get(id);
  }
  
  updateTask(id, updates) {
    const task = this.tasks.get(id);
    if (task) {
      Object.keys(updates).forEach(key => {
        if (key in task) {
          task[key] = updates[key];
        }
      });
      task.updatedAt = new Date();
      this.emit('taskUpdated', task);
    }
    return task;
  }
  
  completeTask(id) {
    const task = this.tasks.get(id);
    if (task) {
      task.markCompleted();
      this.emit('taskCompleted', task);
    }
    return task;
  }
  
  incompleteTask(id) {
    const task = this.tasks.get(id);
    if (task) {
      task.markIncomplete();
      this.emit('taskIncompleted', task);
    }
    return task;
  }
  
  getTasks(filters = {}) {
    let tasks = Array.from(this.tasks.values());
    
    // Apply filters
    const activeFilters = { ...this.filters, ...filters };
    
    if (activeFilters.status !== 'all') {
      tasks = tasks.filter(task => 
        activeFilters.status === 'completed' ? task.completed : !task.completed
      );
    }
    
    if (activeFilters.priority !== 'all') {
      tasks = tasks.filter(task => task.priority === activeFilters.priority);
    }
    
    if (activeFilters.tags.length > 0) {
      tasks = tasks.filter(task => 
        activeFilters.tags.every(tag => task.tags.includes(tag))
      );
    }
    
    if (activeFilters.search) {
      const search = activeFilters.search.toLowerCase();
      tasks = tasks.filter(task => 
        task.title.toLowerCase().includes(search) ||
        task.description.toLowerCase().includes(search)
      );
    }
    
    return tasks;
  }
  
  getTasksByTag(tag) {
    return Array.from(this.tasks.values()).filter(task => 
      task.tags.includes(tag)
    );
  }
  
  getOverdueTasks() {
    return Array.from(this.tasks.values()).filter(task => task.isOverdue());
  }
  
  getTaskStats() {
    const tasks = Array.from(this.tasks.values());
    
    return {
      total: tasks.length,
      completed: tasks.filter(task => task.completed).length,
      active: tasks.filter(task => !task.completed).length,
      overdue: tasks.filter(task => task.isOverdue()).length,
      byPriority: {
        high: tasks.filter(task => task.priority === 'high').length,
        medium: tasks.filter(task => task.priority === 'medium').length,
        low: tasks.filter(task => task.priority === 'low').length
      }
    };
  }
  
  // Bulk operations
  async bulkUpdate(taskIds, updates) {
    const results = [];
    
    for (const id of taskIds) {
      const task = this.updateTask(id, updates);
      if (task) {
        results.push(task);
      }
    }
    
    this.emit('bulkUpdate', results);
    return results;
  }
  
  async bulkDelete(taskIds) {
    const results = [];
    
    for (const id of taskIds) {
      const task = this.removeTask(id);
      if (task) {
        results.push(task);
      }
    }
    
    this.emit('bulkDelete', results);
    return results;
  }
  
  // Persistence
  exportTasks() {
    const tasks = Array.from(this.tasks.values()).map(task => task.toJSON());
    return JSON.stringify(tasks, null, 2);
  }
  
  importTasks(jsonData) {
    try {
      const tasks = JSON.parse(jsonData);
      
      tasks.forEach(taskData => {
        const task = Task.fromJSON(taskData);
        this.tasks.set(task.id, task);
      });
      
      this.emit('tasksImported', tasks.length);
      return tasks.length;
    } catch (error) {
      throw new Error(`Failed to import tasks: ${error.message}`);
    }
  }
  
  // Search and sort
  searchTasks(query) {
    const normalizedQuery = query.toLowerCase();
    
    return Array.from(this.tasks.values()).filter(task => {
      return (
        task.title.toLowerCase().includes(normalizedQuery) ||
        task.description.toLowerCase().includes(normalizedQuery) ||
        task.tags.some(tag => tag.toLowerCase().includes(normalizedQuery))
      );
    });
  }
  
  sortTasks(tasks, sortBy = 'createdAt', order = 'desc') {
    return [...tasks].sort((a, b) => {
      let aValue = a[sortBy];
      let bValue = b[sortBy];
      
      if (sortBy === 'priority') {
        const priorityOrder = { high: 3, medium: 2, low: 1 };
        aValue = priorityOrder[aValue] || 0;
        bValue = priorityOrder[bValue] || 0;
      }
      
      if (aValue instanceof Date) {
        aValue = aValue.getTime();
        bValue = bValue.getTime();
      }
      
      if (order === 'asc') {
        return aValue > bValue ? 1 : -1;
      } else {
        return aValue < bValue ? 1 : -1;
      }
    });
  }
}

// Storage service
class TaskStorageService {
  constructor(storage = localStorage) {
    this.storage = storage;
    this.key = 'taskManager_tasks';
  }
  
  async saveTasks(taskManager) {
    try {
      const data = taskManager.exportTasks();
      this.storage.setItem(this.key, data);
      return true;
    } catch (error) {
      console.error('Failed to save tasks:', error);
      return false;
    }
  }
  
  async loadTasks(taskManager) {
    try {
      const data = this.storage.getItem(this.key);
      if (data) {
        return taskManager.importTasks(data);
      }
      return 0;
    } catch (error) {
      console.error('Failed to load tasks:', error);
      return 0;
    }
  }
  
  async clearTasks() {
    try {
      this.storage.removeItem(this.key);
      return true;
    } catch (error) {
      console.error('Failed to clear tasks:', error);
      return false;
    }
  }
}

// Task app controller
class TaskApp {
  constructor() {
    this.taskManager = new TaskManager();
    this.storageService = new TaskStorageService();
    this.autoSave = true;
    
    this.setupEventListeners();
    this.loadTasks();
  }
  
  setupEventListeners() {
    // Auto-save on changes
    const autoSaveEvents = [
      'taskAdded', 'taskRemoved', 'taskUpdated', 
      'taskCompleted', 'taskIncompleted'
    ];
    
    autoSaveEvents.forEach(event => {
      this.taskManager.on(event, () => {
        if (this.autoSave) {
          this.saveTasks();
        }
      });
    });
    
    // Periodic auto-save
    setInterval(() => {
      if (this.autoSave) {
        this.saveTasks();
      }
    }, 30000); // Save every 30 seconds
  }
  
  async loadTasks() {
    try {
      const count = await this.storageService.loadTasks(this.taskManager);
      console.log(`Loaded ${count} tasks`);
    } catch (error) {
      console.error('Failed to load tasks:', error);
    }
  }
  
  async saveTasks() {
    try {
      await this.storageService.saveTasks(this.taskManager);
    } catch (error) {
      console.error('Failed to save tasks:', error);
    }
  }
  
  // Public API
  createTask(title, description, priority) {
    return this.taskManager.addTask(title, description, priority);
  }
  
  getTasks(filters) {
    return this.taskManager.getTasks(filters);
  }
  
  completeTask(id) {
    return this.taskManager.completeTask(id);
  }
  
  deleteTask(id) {
    return this.taskManager.removeTask(id);
  }
  
  getStats() {
    return this.taskManager.getTaskStats();
  }
  
  searchTasks(query) {
    return this.taskManager.searchTasks(query);
  }
}

// Usage example
const app = new TaskApp();

// Create some tasks
app.createTask('Learn JavaScript', 'Study modern ES6+ features', 'high');
app.createTask('Build project', 'Create a task management app', 'medium');
app.createTask('Review code', 'Review pull requests', 'low');

// Get tasks with filters
const highPriorityTasks = app.getTasks({ priority: 'high' });
console.log('High priority tasks:', highPriorityTasks);

// Search tasks
const searchResults = app.searchTasks('JavaScript');
console.log('Search results:', searchResults);

// Get stats
const stats = app.getStats();
console.log('Task statistics:', stats);

export { TaskApp, TaskManager, Task };
```

---

## Best Practices Summary

### 1. **Modern Syntax**
- Use `const` and `let` instead of `var`
- Prefer arrow functions for short operations
- Use template literals for string interpolation
- Implement destructuring for cleaner code

### 2. **Asynchronous Programming**
- Prefer `async/await` over Promise chains
- Handle errors with try-catch blocks
- Use Promise.all() for parallel operations
- Implement proper error handling patterns

### 3. **Performance**
- Use efficient array methods
- Implement memoization for expensive operations
- Use debouncing and throttling for events
- Minimize object creation in loops

### 4. **Code Organization**
- Use ES6 modules for code organization
- Implement class-based architecture when appropriate
- Follow consistent naming conventions
- Write self-documenting code

### 5. **Error Handling**
- Create custom error classes
- Implement comprehensive error handling
- Use assertions for debugging
- Log errors appropriately

This comprehensive JavaScript guide covers modern ES6+ features and patterns essential for professional web development, from basic syntax to advanced architectural patterns.