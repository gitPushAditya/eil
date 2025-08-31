# Express.js

## Introduction to Express.js

Express.js is a fast, unopinionated, minimalist web framework for Node.js. It provides a robust set of features for building web applications and APIs, making it easier to handle HTTP requests, routing, middleware, and more.

---

## Getting Started

### Installation

```bash
npm init -y
npm install express
```

### Basic Express Server

```javascript
const express = require('express');
const app = express();
const PORT = process.env.PORT || 3000;

app.get('/', (req, res) => {
    res.send('Hello World!');
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
```

---

## Routing

### Basic Routes

```javascript
const express = require('express');
const app = express();

// GET route
app.get('/', (req, res) => {
    res.send('GET request to homepage');
});

// POST route
app.post('/users', (req, res) => {
    res.send('POST request to create user');
});

// PUT route
app.put('/users/:id', (req, res) => {
    res.send(`PUT request to update user ${req.params.id}`);
});

// DELETE route
app.delete('/users/:id', (req, res) => {
    res.send(`DELETE request to remove user ${req.params.id}`);
});
```

### Route Parameters

```javascript
// Route parameters
app.get('/users/:id', (req, res) => {
    const userId = req.params.id;
    res.json({ userId: userId });
});

// Multiple parameters
app.get('/users/:userId/posts/:postId', (req, res) => {
    const { userId, postId } = req.params;
    res.json({ userId, postId });
});

// Optional parameters
app.get('/posts/:year/:month?', (req, res) => {
    const { year, month } = req.params;
    res.json({ year, month: month || 'all' });
});
```

### Query Parameters

```javascript
// Query parameters
app.get('/search', (req, res) => {
    const { q, page, limit } = req.query;
    res.json({
        query: q,
        page: page || 1,
        limit: limit || 10
    });
});
// Example: GET /search?q=nodejs&page=2&limit=5
```

---

## Middleware

### Built-in Middleware

```javascript
const express = require('express');
const app = express();

// Parse JSON bodies
app.use(express.json());

// Parse URL-encoded bodies
app.use(express.urlencoded({ extended: true }));

// Serve static files
app.use(express.static('public'));
```

### Custom Middleware

```javascript
// Logger middleware
const logger = (req, res, next) => {
    console.log(`${new Date().toISOString()} - ${req.method} ${req.url}`);
    next(); // Call next middleware
};

app.use(logger);

// Authentication middleware
const authenticate = (req, res, next) => {
    const token = req.headers['authorization'];
    
    if (!token) {
        return res.status(401).json({ error: 'No token provided' });
    }
    
    // Verify token logic here
    req.user = { id: 1, username: 'john' }; // Mock user
    next();
};

// Apply middleware to specific routes
app.get('/protected', authenticate, (req, res) => {
    res.json({ message: 'Access granted', user: req.user });
});
```

### Error Handling Middleware

```javascript
// Error handling middleware (must be last)
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).json({ error: 'Something went wrong!' });
});

// 404 handler
app.use((req, res) => {
    res.status(404).json({ error: 'Route not found' });
});
```

---

## Router

```javascript
const express = require('express');
const userRouter = express.Router();

// User routes
userRouter.get('/', (req, res) => {
    res.json({ message: 'Get all users' });
});

userRouter.get('/:id', (req, res) => {
    res.json({ message: `Get user ${req.params.id}` });
});

userRouter.post('/', (req, res) => {
    res.json({ message: 'Create user', data: req.body });
});

userRouter.put('/:id', (req, res) => {
    res.json({ message: `Update user ${req.params.id}`, data: req.body });
});

userRouter.delete('/:id', (req, res) => {
    res.json({ message: `Delete user ${req.params.id}` });
});

// Mount router
app.use('/api/users', userRouter);
```

---

## Request and Response Objects

### Request Object

```javascript
app.post('/example', (req, res) => {
    // Request properties
    console.log('Method:', req.method);
    console.log('URL:', req.url);
    console.log('Headers:', req.headers);
    console.log('Body:', req.body);
    console.log('Params:', req.params);
    console.log('Query:', req.query);
    console.log('Cookies:', req.cookies);
    console.log('IP:', req.ip);
    
    res.send('Request logged');
});
```

### Response Object

```javascript
app.get('/response-examples', (req, res) => {
    // Different response methods
    
    // Send plain text
    res.send('Hello World');
    
    // Send JSON
    res.json({ message: 'Success', data: [] });
    
    // Set status code
    res.status(201).json({ message: 'Created' });
    
    // Set headers
    res.set('Content-Type', 'text/plain');
    res.send('Plain text response');
    
    // Redirect
    res.redirect('/login');
    
    // Send file
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
    
    // Set cookie
    res.cookie('sessionId', '12345', { httpOnly: true });
    
    // End response
    res.end();
});
```

---

## Working with Databases

### MongoDB with Mongoose

```javascript
const mongoose = require('mongoose');
const express = require('express');
const app = express();

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/myapp');

// User schema
const userSchema = new mongoose.Schema({
    name: { type: String, required: true },
    email: { type: String, required: true, unique: true },
    age: { type: Number, min: 0 }
});

const User = mongoose.model('User', userSchema);

app.use(express.json());

// Create user
app.post('/users', async (req, res) => {
    try {
        const user = new User(req.body);
        await user.save();
        res.status(201).json(user);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

// Get all users
app.get('/users', async (req, res) => {
    try {
        const users = await User.find();
        res.json(users);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

// Get user by ID
app.get('/users/:id', async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (!user) {
            return res.status(404).json({ error: 'User not found' });
        }
        res.json(user);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});
```

---

## File Upload

```javascript
const multer = require('multer');
const path = require('path');

// Configure multer for file uploads
const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, 'uploads/');
    },
    filename: (req, file, cb) => {
        cb(null, Date.now() + path.extname(file.originalname));
    }
});

const upload = multer({ 
    storage: storage,
    limits: { fileSize: 1024 * 1024 * 5 }, // 5MB limit
    fileFilter: (req, file, cb) => {
        const allowedTypes = /jpeg|jpg|png|gif/;
        const extname = allowedTypes.test(path.extname(file.originalname).toLowerCase());
        const mimetype = allowedTypes.test(file.mimetype);
        
        if (mimetype && extname) {
            return cb(null, true);
        } else {
            cb(new Error('Invalid file type'));
        }
    }
});

// Single file upload
app.post('/upload', upload.single('image'), (req, res) => {
    if (!req.file) {
        return res.status(400).json({ error: 'No file uploaded' });
    }
    
    res.json({
        message: 'File uploaded successfully',
        filename: req.file.filename,
        path: req.file.path
    });
});

// Multiple file upload
app.post('/upload-multiple', upload.array('images', 5), (req, res) => {
    res.json({
        message: 'Files uploaded successfully',
        files: req.files.map(file => ({
            filename: file.filename,
            path: file.path
        }))
    });
});
```

---

## Environment Variables and Configuration

```javascript
// Install: npm install dotenv
require('dotenv').config();

const express = require('express');
const app = express();

const config = {
    port: process.env.PORT || 3000,
    dbUrl: process.env.DATABASE_URL || 'mongodb://localhost:27017/myapp',
    jwtSecret: process.env.JWT_SECRET || 'fallback-secret',
    nodeEnv: process.env.NODE_ENV || 'development'
};

// Use configuration
app.listen(config.port, () => {
    console.log(`Server running on port ${config.port}`);
    console.log(`Environment: ${config.nodeEnv}`);
});
```

---

## Best Practices

1. **Use environment variables** - For sensitive configuration data
2. **Implement proper error handling** - Use try-catch and error middleware
3. **Validate input data** - Use libraries like Joi or express-validator
4. **Implement security middleware** - Use helmet, cors, rate limiting
5. **Structure your application** - Separate routes, controllers, and models
6. **Use async/await** - For better error handling with promises
7. **Log requests and errors** - Use libraries like winston or morgan
8. **Implement authentication and authorization** - Use JWT or sessions
9. **Handle CORS properly** - Configure allowed origins and methods
10. **Use compression** - Compress responses for better performance
11. **Implement rate limiting** - Prevent abuse and DoS attacks
12. **Use HTTPS in production** - Encrypt data in transit

---