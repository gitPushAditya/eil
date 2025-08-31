# Express.js - Node.js Web Framework

Express.js is a minimal and flexible Node.js web application framework that provides a robust set of features for web and mobile applications. This guide covers modern Express.js development with best practices for production environments.

## Setup and Environment

### Initial Setup

```bash
# Initialize new Node.js project
mkdir my-express-app
cd my-express-app
npm init -y

# Install Express and essential dependencies
npm install express
npm install --save-dev nodemon concurrently

# Additional common dependencies
npm install cors helmet morgan compression
npm install dotenv bcryptjs jsonwebtoken
npm install express-rate-limit express-validator
npm install cookie-parser body-parser multer

# Database packages (choose one)
npm install mongoose     # MongoDB
npm install pg          # PostgreSQL
npm install mysql2      # MySQL
npm install sqlite3     # SQLite

# Development dependencies
npm install --save-dev eslint prettier jest supertest
```

### Project Structure

```
my-express-app/
├── src/
│   ├── controllers/     # Route handlers
│   ├── middleware/      # Custom middleware
│   ├── models/         # Database models
│   ├── routes/         # Route definitions
│   ├── services/       # Business logic
│   ├── utils/          # Utility functions
│   ├── config/         # Configuration files
│   └── app.js          # Express app setup
├── tests/              # Test files
├── public/             # Static files
├── uploads/            # File uploads
├── logs/               # Log files
├── .env                # Environment variables
├── .gitignore
├── package.json
└── server.js           # Entry point
```

### Basic Server Setup

```javascript
// server.js
const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');
const compression = require('compression');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;

// Security middleware
app.use(helmet());

// CORS configuration
app.use(cors({
  origin: process.env.CLIENT_URL || 'http://localhost:3000',
  credentials: true
}));

// Body parsing middleware
app.use(express.json({ limit: '10mb' }));
app.use(express.urlencoded({ extended: true, limit: '10mb' }));

// Compression middleware
app.use(compression());

// Logging middleware
app.use(morgan('combined'));

// Static files
app.use(express.static('public'));

// Routes
app.use('/api/auth', require('./src/routes/auth'));
app.use('/api/users', require('./src/routes/users'));
app.use('/api/posts', require('./src/routes/posts'));

// Error handling middleware
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).json({
    message: 'Something went wrong!',
    error: process.env.NODE_ENV === 'production' ? {} : err.stack
  });
});

// 404 handler
app.use('*', (req, res) => {
  res.status(404).json({
    message: 'Route not found'
  });
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

module.exports = app;
```

---

## Routing

### Basic Routing

```javascript
// src/routes/users.js
const express = require('express');
const router = express.Router();
const userController = require('../controllers/userController');
const authMiddleware = require('../middleware/auth');
const { validateUser } = require('../middleware/validation');

// GET /api/users
router.get('/', authMiddleware, userController.getAllUsers);

// GET /api/users/:id
router.get('/:id', authMiddleware, userController.getUserById);

// POST /api/users
router.post('/', validateUser, userController.createUser);

// PUT /api/users/:id
router.put('/:id', authMiddleware, validateUser, userController.updateUser);

// DELETE /api/users/:id
router.delete('/:id', authMiddleware, userController.deleteUser);

// Nested routes
router.use('/:userId/posts', require('./posts'));

module.exports = router;
```

### Advanced Routing Patterns

```javascript
// src/routes/api.js
const express = require('express');
const router = express.Router();

// Route parameters with validation
router.get('/users/:id(\\d+)', (req, res) => {
  // :id must be numeric
  const userId = parseInt(req.params.id);
  res.json({ userId });
});

// Multiple route parameters
router.get('/users/:userId/posts/:postId', (req, res) => {
  const { userId, postId } = req.params;
  res.json({ userId, postId });
});

// Query parameters
router.get('/search', (req, res) => {
  const { q, page = 1, limit = 10, sort = 'created_at' } = req.query;
  
  // Convert to appropriate types
  const pageNum = parseInt(page);
  const limitNum = parseInt(limit);
  
  res.json({
    query: q,
    pagination: { page: pageNum, limit: limitNum },
    sort
  });
});

// Route with optional parameters
router.get('/posts/:year/:month?', (req, res) => {
  const { year, month } = req.params;
  res.json({ year, month: month || 'all' });
});

// Wildcard routes
router.get('/files/*', (req, res) => {
  const filePath = req.params[0];
  res.json({ filePath });
});

// Route with custom regex
router.get('/products/:sku([A-Z]{3}-\\d{4})', (req, res) => {
  // SKU format: ABC-1234
  res.json({ sku: req.params.sku });
});

module.exports = router;
```

### Router-level Middleware

```javascript
// src/routes/admin.js
const express = require('express');
const router = express.Router();

// Middleware for all admin routes
router.use((req, res, next) => {
  console.log('Admin route accessed:', req.method, req.path);
  next();
});

// Admin authentication middleware
router.use(require('../middleware/adminAuth'));

// Admin-only routes
router.get('/dashboard', (req, res) => {
  res.json({ message: 'Admin dashboard' });
});

router.get('/users', (req, res) => {
  res.json({ message: 'Admin users list' });
});

// Sub-router for admin settings
const settingsRouter = express.Router();
settingsRouter.get('/', (req, res) => {
  res.json({ message: 'Admin settings' });
});
router.use('/settings', settingsRouter);

module.exports = router;
```

---

## Controllers

### RESTful Controllers

```javascript
// src/controllers/userController.js
const User = require('../models/User');
const { validationResult } = require('express-validator');
const asyncHandler = require('../utils/asyncHandler');

class UserController {
  // GET /api/users
  getAllUsers = asyncHandler(async (req, res) => {
    const { page = 1, limit = 10, sort = 'createdAt', order = 'desc', search } = req.query;
    
    const options = {
      page: parseInt(page),
      limit: parseInt(limit),
      sort: { [sort]: order === 'desc' ? -1 : 1 }
    };
    
    let query = {};
    if (search) {
      query = {
        $or: [
          { name: { $regex: search, $options: 'i' } },
          { email: { $regex: search, $options: 'i' } }
        ]
      };
    }
    
    const users = await User.paginate(query, options);
    
    res.json({
      success: true,
      data: users.docs,
      pagination: {
        page: users.page,
        limit: users.limit,
        total: users.totalDocs,
        pages: users.totalPages,
        hasNext: users.hasNextPage,
        hasPrev: users.hasPrevPage
      }
    });
  });

  // GET /api/users/:id
  getUserById = asyncHandler(async (req, res) => {
    const { id } = req.params;
    
    const user = await User.findById(id)
      .populate('posts', 'title createdAt')
      .lean();
    
    if (!user) {
      return res.status(404).json({
        success: false,
        message: 'User not found'
      });
    }
    
    res.json({
      success: true,
      data: user
    });
  });

  // POST /api/users
  createUser = asyncHandler(async (req, res) => {
    // Check validation errors
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({
        success: false,
        message: 'Validation errors',
        errors: errors.array()
      });
    }
    
    const { name, email, password, role = 'user' } = req.body;
    
    // Check if user already exists
    const existingUser = await User.findOne({ email });
    if (existingUser) {
      return res.status(409).json({
        success: false,
        message: 'User with this email already exists'
      });
    }
    
    const user = new User({
      name,
      email,
      password,
      role
    });
    
    await user.save();
    
    // Remove password from response
    const userResponse = user.toObject();
    delete userResponse.password;
    
    res.status(201).json({
      success: true,
      message: 'User created successfully',
      data: userResponse
    });
  });

  // PUT /api/users/:id
  updateUser = asyncHandler(async (req, res) => {
    const { id } = req.params;
    const updates = req.body;
    
    // Remove sensitive fields from updates
    delete updates.password;
    delete updates.role; // Role updates should be separate endpoint
    
    const user = await User.findByIdAndUpdate(
      id,
      { ...updates, updatedAt: new Date() },
      { new: true, runValidators: true }
    ).select('-password');
    
    if (!user) {
      return res.status(404).json({
        success: false,
        message: 'User not found'
      });
    }
    
    res.json({
      success: true,
      message: 'User updated successfully',
      data: user
    });
  });

  // DELETE /api/users/:id
  deleteUser = asyncHandler(async (req, res) => {
    const { id } = req.params;
    
    const user = await User.findByIdAndDelete(id);
    
    if (!user) {
      return res.status(404).json({
        success: false,
        message: 'User not found'
      });
    }
    
    res.json({
      success: true,
      message: 'User deleted successfully'
    });
  });

  // PATCH /api/users/:id/password
  changePassword = asyncHandler(async (req, res) => {
    const { id } = req.params;
    const { currentPassword, newPassword } = req.body;
    
    const user = await User.findById(id);
    if (!user) {
      return res.status(404).json({
        success: false,
        message: 'User not found'
      });
    }
    
    // Verify current password
    const isValidPassword = await user.comparePassword(currentPassword);
    if (!isValidPassword) {
      return res.status(400).json({
        success: false,
        message: 'Current password is incorrect'
      });
    }
    
    user.password = newPassword;
    await user.save();
    
    res.json({
      success: true,
      message: 'Password changed successfully'
    });
  });
}

module.exports = new UserController();
```

### Service Layer Pattern

```javascript
// src/services/userService.js
const User = require('../models/User');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

class UserService {
  async createUser(userData) {
    const { name, email, password } = userData;
    
    // Hash password
    const saltRounds = 12;
    const hashedPassword = await bcrypt.hash(password, saltRounds);
    
    const user = new User({
      name,
      email,
      password: hashedPassword
    });
    
    return await user.save();
  }
  
  async authenticateUser(email, password) {
    const user = await User.findOne({ email }).select('+password');
    
    if (!user) {
      throw new Error('Invalid credentials');
    }
    
    const isValidPassword = await bcrypt.compare(password, user.password);
    if (!isValidPassword) {
      throw new Error('Invalid credentials');
    }
    
    return user;
  }
  
  generateToken(userId) {
    return jwt.sign(
      { userId },
      process.env.JWT_SECRET,
      { expiresIn: process.env.JWT_EXPIRES_IN || '7d' }
    );
  }
  
  async getUserProfile(userId) {
    return await User.findById(userId)
      .select('-password')
      .populate('posts', 'title createdAt');
  }
  
  async updateUserProfile(userId, updates) {
    const allowedUpdates = ['name', 'email', 'bio', 'avatar'];
    const filteredUpdates = Object.keys(updates)
      .filter(key => allowedUpdates.includes(key))
      .reduce((obj, key) => {
        obj[key] = updates[key];
        return obj;
      }, {});
    
    return await User.findByIdAndUpdate(
      userId,
      { ...filteredUpdates, updatedAt: new Date() },
      { new: true, runValidators: true }
    ).select('-password');
  }
  
  async deleteUser(userId) {
    return await User.findByIdAndDelete(userId);
  }
  
  async searchUsers(query, options = {}) {
    const { page = 1, limit = 10, sortBy = 'createdAt', sortOrder = 'desc' } = options;
    
    const searchQuery = {
      $or: [
        { name: { $regex: query, $options: 'i' } },
        { email: { $regex: query, $options: 'i' } }
      ]
    };
    
    const sort = { [sortBy]: sortOrder === 'desc' ? -1 : 1 };
    
    const users = await User.find(searchQuery)
      .select('-password')
      .sort(sort)
      .limit(limit * 1)
      .skip((page - 1) * limit)
      .lean();
    
    const total = await User.countDocuments(searchQuery);
    
    return {
      users,
      pagination: {
        page,
        limit,
        total,
        pages: Math.ceil(total / limit)
      }
    };
  }
}

module.exports = new UserService();
```

---

## Middleware

### Authentication Middleware

```javascript
// src/middleware/auth.js
const jwt = require('jsonwebtoken');
const User = require('../models/User');

const auth = async (req, res, next) => {
  try {
    const token = req.header('Authorization')?.replace('Bearer ', '') ||
                  req.cookies?.token;
    
    if (!token) {
      return res.status(401).json({
        success: false,
        message: 'Access denied. No token provided.'
      });
    }
    
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    const user = await User.findById(decoded.userId).select('-password');
    
    if (!user) {
      return res.status(401).json({
        success: false,
        message: 'Token is invalid.'
      });
    }
    
    req.user = user;
    next();
  } catch (error) {
    res.status(401).json({
      success: false,
      message: 'Token is invalid.'
    });
  }
};

// Admin authorization middleware
const adminAuth = (req, res, next) => {
  if (req.user.role !== 'admin') {
    return res.status(403).json({
      success: false,
      message: 'Access denied. Admin privileges required.'
    });
  }
  next();
};

// Role-based authorization
const authorize = (...roles) => {
  return (req, res, next) => {
    if (!roles.includes(req.user.role)) {
      return res.status(403).json({
        success: false,
        message: 'Access denied. Insufficient privileges.'
      });
    }
    next();
  };
};

module.exports = { auth, adminAuth, authorize };
```

### Validation Middleware

```javascript
// src/middleware/validation.js
const { body, param, query, validationResult } = require('express-validator');

// User validation rules
const userValidationRules = () => {
  return [
    body('name')
      .trim()
      .isLength({ min: 2, max: 50 })
      .withMessage('Name must be between 2 and 50 characters'),
    
    body('email')
      .isEmail()
      .normalizeEmail()
      .withMessage('Must be a valid email address'),
    
    body('password')
      .isLength({ min: 8 })
      .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]/)
      .withMessage('Password must contain at least 8 characters with uppercase, lowercase, number and special character'),
    
    body('role')
      .optional()
      .isIn(['user', 'admin', 'moderator'])
      .withMessage('Role must be user, admin, or moderator')
  ];
};

// Post validation rules
const postValidationRules = () => {
  return [
    body('title')
      .trim()
      .isLength({ min: 5, max: 100 })
      .withMessage('Title must be between 5 and 100 characters'),
    
    body('content')
      .trim()
      .isLength({ min: 10, max: 5000 })
      .withMessage('Content must be between 10 and 5000 characters'),
    
    body('tags')
      .optional()
      .isArray()
      .withMessage('Tags must be an array'),
    
    body('tags.*')
      .optional()
      .trim()
      .isLength({ min: 2, max: 20 })
      .withMessage('Each tag must be between 2 and 20 characters')
  ];
};

// ID parameter validation
const validateId = () => {
  return [
    param('id')
      .isMongoId()
      .withMessage('Invalid ID format')
  ];
};

// Pagination validation
const validatePagination = () => {
  return [
    query('page')
      .optional()
      .isInt({ min: 1 })
      .withMessage('Page must be a positive integer'),
    
    query('limit')
      .optional()
      .isInt({ min: 1, max: 100 })
      .withMessage('Limit must be between 1 and 100'),
    
    query('sort')
      .optional()
      .isIn(['createdAt', 'updatedAt', 'name', 'title'])
      .withMessage('Invalid sort field'),
    
    query('order')
      .optional()
      .isIn(['asc', 'desc'])
      .withMessage('Order must be asc or desc')
  ];
};

// Validation error handler
const handleValidationErrors = (req, res, next) => {
  const errors = validationResult(req);
  
  if (!errors.isEmpty()) {
    return res.status(400).json({
      success: false,
      message: 'Validation errors',
      errors: errors.array().map(error => ({
        field: error.param,
        message: error.msg,
        value: error.value
      }))
    });
  }
  
  next();
};

module.exports = {
  userValidationRules,
  postValidationRules,
  validateId,
  validatePagination,
  handleValidationErrors
};
```

### Custom Middleware

```javascript
// src/middleware/rateLimiter.js
const rateLimit = require('express-rate-limit');

// General rate limiter
const generalLimiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutes
  max: 100, // Limit each IP to 100 requests per windowMs
  message: {
    success: false,
    message: 'Too many requests from this IP, please try again later.'
  },
  standardHeaders: true,
  legacyHeaders: false
});

// Strict limiter for authentication routes
const authLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 5,
  message: {
    success: false,
    message: 'Too many authentication attempts, please try again later.'
  }
});

// API key rate limiter
const apiKeyLimiter = rateLimit({
  windowMs: 60 * 60 * 1000, // 1 hour
  max: (req) => {
    // Different limits based on API key tier
    const tier = req.apiKey?.tier || 'free';
    const limits = {
      free: 100,
      premium: 1000,
      enterprise: 10000
    };
    return limits[tier];
  },
  keyGenerator: (req) => req.apiKey?.id || req.ip
});

module.exports = {
  generalLimiter,
  authLimiter,
  apiKeyLimiter
};

// src/middleware/logger.js
const winston = require('winston');

const logger = winston.createLogger({
  level: 'info',
  format: winston.format.combine(
    winston.format.timestamp(),
    winston.format.errors({ stack: true }),
    winston.format.json()
  ),
  defaultMeta: { service: 'express-app' },
  transports: [
    new winston.transports.File({ filename: 'logs/error.log', level: 'error' }),
    new winston.transports.File({ filename: 'logs/combined.log' })
  ]
});

if (process.env.NODE_ENV !== 'production') {
  logger.add(new winston.transports.Console({
    format: winston.format.simple()
  }));
}

// Request logging middleware
const requestLogger = (req, res, next) => {
  const start = Date.now();
  
  res.on('finish', () => {
    const duration = Date.now() - start;
    logger.info({
      method: req.method,
      url: req.originalUrl,
      status: res.statusCode,
      duration: `${duration}ms`,
      ip: req.ip,
      userAgent: req.get('User-Agent')
    });
  });
  
  next();
};

module.exports = { logger, requestLogger };

// src/middleware/security.js
const helmet = require('helmet');
const mongoSanitize = require('express-mongo-sanitize');
const xss = require('xss-clean');
const hpp = require('hpp');

const securityMiddleware = [
  // Set security headers
  helmet({
    contentSecurityPolicy: {
      directives: {
        defaultSrc: ["'self'"],
        styleSrc: ["'self'", "'unsafe-inline'"],
        scriptSrc: ["'self'"],
        imgSrc: ["'self'", "data:", "https:"]
      }
    }
  }),
  
  // Data sanitization against NoSQL query injection
  mongoSanitize(),
  
  // Data sanitization against XSS
  xss(),
  
  // Prevent parameter pollution
  hpp({
    whitelist: ['sort', 'fields', 'page', 'limit']
  })
];

module.exports = securityMiddleware;
```

---

## File Uploads

### Basic File Upload

```javascript
// src/middleware/upload.js
const multer = require('multer');
const path = require('path');
const crypto = require('crypto');

// Storage configuration
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, 'uploads/');
  },
  filename: (req, file, cb) => {
    // Generate unique filename
    const uniqueName = crypto.randomBytes(16).toString('hex');
    const extension = path.extname(file.originalname);
    cb(null, `${uniqueName}${extension}`);
  }
});

// File filter
const fileFilter = (req, file, cb) => {
  // Allowed file types
  const allowedTypes = ['image/jpeg', 'image/png', 'image/gif', 'application/pdf'];
  
  if (allowedTypes.includes(file.mimetype)) {
    cb(null, true);
  } else {
    cb(new Error('Invalid file type. Only JPEG, PNG, GIF, and PDF are allowed.'), false);
  }
};

// Multer configuration
const upload = multer({
  storage,
  fileFilter,
  limits: {
    fileSize: 5 * 1024 * 1024, // 5MB limit
    files: 5 // Maximum 5 files
  }
});

// Memory storage for processing
const memoryUpload = multer({
  storage: multer.memoryStorage(),
  fileFilter,
  limits: {
    fileSize: 10 * 1024 * 1024 // 10MB limit
  }
});

module.exports = {
  upload,
  memoryUpload
};

// src/controllers/uploadController.js
const sharp = require('sharp');
const path = require('path');
const fs = require('fs').promises;

class UploadController {
  // Single file upload
  uploadSingle = async (req, res) => {
    try {
      if (!req.file) {
        return res.status(400).json({
          success: false,
          message: 'No file uploaded'
        });
      }
      
      res.json({
        success: true,
        message: 'File uploaded successfully',
        data: {
          filename: req.file.filename,
          originalName: req.file.originalname,
          size: req.file.size,
          url: `/uploads/${req.file.filename}`
        }
      });
    } catch (error) {
      res.status(500).json({
        success: false,
        message: 'Upload failed',
        error: error.message
      });
    }
  };

  // Multiple files upload
  uploadMultiple = async (req, res) => {
    try {
      if (!req.files || req.files.length === 0) {
        return res.status(400).json({
          success: false,
          message: 'No files uploaded'
        });
      }
      
      const uploadedFiles = req.files.map(file => ({
        filename: file.filename,
        originalName: file.originalname,
        size: file.size,
        url: `/uploads/${file.filename}`
      }));
      
      res.json({
        success: true,
        message: 'Files uploaded successfully',
        data: uploadedFiles
      });
    } catch (error) {
      res.status(500).json({
        success: false,
        message: 'Upload failed',
        error: error.message
      });
    }
  };

  // Image processing and upload
  uploadAndProcessImage = async (req, res) => {
    try {
      if (!req.file) {
        return res.status(400).json({
          success: false,
          message: 'No image uploaded'
        });
      }
      
      const { buffer, originalname } = req.file;
      const filename = `processed-${Date.now()}-${originalname}`;
      const outputPath = path.join('uploads', filename);
      
      // Process image with sharp
      await sharp(buffer)
        .resize(800, 600, { 
          fit: 'inside',
          withoutEnlargement: true 
        })
        .jpeg({ quality: 80 })
        .toFile(outputPath);
      
      res.json({
        success: true,
        message: 'Image processed and uploaded successfully',
        data: {
          filename,
          url: `/uploads/${filename}`
        }
      });
    } catch (error) {
      res.status(500).json({
        success: false,
        message: 'Image processing failed',
        error: error.message
      });
    }
  };

  // Delete uploaded file
  deleteFile = async (req, res) => {
    try {
      const { filename } = req.params;
      const filePath = path.join('uploads', filename);
      
      await fs.unlink(filePath);
      
      res.json({
        success: true,
        message: 'File deleted successfully'
      });
    } catch (error) {
      if (error.code === 'ENOENT') {
        return res.status(404).json({
          success: false,
          message: 'File not found'
        });
      }
      
      res.status(500).json({
        success: false,
        message: 'File deletion failed',
        error: error.message
      });
    }
  };
}

module.exports = new UploadController();

// src/routes/upload.js
const express = require('express');
const router = express.Router();
const { upload, memoryUpload } = require('../middleware/upload');
const uploadController = require('../controllers/uploadController');
const { auth } = require('../middleware/auth');

// Single file upload
router.post('/single', 
  auth,
  upload.single('file'),
  uploadController.uploadSingle
);

// Multiple files upload
router.post('/multiple',
  auth,
  upload.array('files', 5),
  uploadController.uploadMultiple
);

// Image processing upload
router.post('/image',
  auth,
  memoryUpload.single('image'),
  uploadController.uploadAndProcessImage
);

// Delete file
router.delete('/:filename',
  auth,
  uploadController.deleteFile
);

module.exports = router;
```

---

## Database Integration

### MongoDB with Mongoose

```javascript
// src/config/database.js
const mongoose = require('mongoose');

const connectDB = async () => {
  try {
    const conn = await mongoose.connect(process.env.MONGODB_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
    
    console.log(`MongoDB Connected: ${conn.connection.host}`);
  } catch (error) {
    console.error('Database connection error:', error);
    process.exit(1);
  }
};

module.exports = connectDB;

// src/models/User.js
const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const userSchema = new mongoose.Schema({
  name: {
    type: String,
    required: [true, 'Name is required'],
    trim: true,
    maxlength: [50, 'Name cannot exceed 50 characters']
  },
  email: {
    type: String,
    required: [true, 'Email is required'],
    unique: true,
    lowercase: true,
    match: [/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/, 'Please enter a valid email']
  },
  password: {
    type: String,
    required: [true, 'Password is required'],
    minlength: [8, 'Password must be at least 8 characters'],
    select: false // Don't include password in queries by default
  },
  role: {
    type: String,
    enum: ['user', 'admin', 'moderator'],
    default: 'user'
  },
  avatar: {
    type: String,
    default: null
  },
  bio: {
    type: String,
    maxlength: [500, 'Bio cannot exceed 500 characters']
  },
  isActive: {
    type: Boolean,
    default: true
  },
  lastLoginAt: {
    type: Date,
    default: null
  }
}, {
  timestamps: true,
  toJSON: { virtuals: true },
  toObject: { virtuals: true }
});

// Virtual for posts
userSchema.virtual('posts', {
  ref: 'Post',
  localField: '_id',
  foreignField: 'author',
  justOne: false
});

// Pre-save middleware to hash password
userSchema.pre('save', async function(next) {
  if (!this.isModified('password')) return next();
  
  try {
    const salt = await bcrypt.genSalt(12);
    this.password = await bcrypt.hash(this.password, salt);
    next();
  } catch (error) {
    next(error);
  }
});

// Instance method to compare password
userSchema.methods.comparePassword = async function(candidatePassword) {
  return await bcrypt.compare(candidatePassword, this.password);
};

// Static method to find active users
userSchema.statics.findActive = function() {
  return this.find({ isActive: true });
};

// Index for better query performance
userSchema.index({ email: 1 });
userSchema.index({ createdAt: -1 });

module.exports = mongoose.model('User', userSchema);
```

### PostgreSQL with Sequelize

```javascript
// src/config/database.js
const { Sequelize } = require('sequelize');

const sequelize = new Sequelize(process.env.DATABASE_URL, {
  dialect: 'postgres',
  logging: process.env.NODE_ENV === 'development' ? console.log : false,
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  }
});

const connectDB = async () => {
  try {
    await sequelize.authenticate();
    console.log('PostgreSQL connection established successfully.');
    
    // Sync models in development
    if (process.env.NODE_ENV === 'development') {
      await sequelize.sync({ alter: true });
      console.log('Database synchronized');
    }
  } catch (error) {
    console.error('Unable to connect to the database:', error);
    process.exit(1);
  }
};

module.exports = { sequelize, connectDB };

// src/models/User.js
const { DataTypes, Model } = require('sequelize');
const { sequelize } = require('../config/database');
const bcrypt = require('bcryptjs');

class User extends Model {
  async comparePassword(candidatePassword) {
    return await bcrypt.compare(candidatePassword, this.password);
  }
  
  toJSON() {
    const values = { ...this.get() };
    delete values.password;
    return values;
  }
}

User.init({
  id: {
    type: DataTypes.UUID,
    defaultValue: DataTypes.UUIDV4,
    primaryKey: true
  },
  name: {
    type: DataTypes.STRING(50),
    allowNull: false,
    validate: {
      notEmpty: { msg: 'Name is required' },
      len: {
        args: [2, 50],
        msg: 'Name must be between 2 and 50 characters'
      }
    }
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: {
      msg: 'Email already exists'
    },
    validate: {
      isEmail: { msg: 'Must be a valid email address' }
    }
  },
  password: {
    type: DataTypes.STRING,
    allowNull: false,
    validate: {
      len: {
        args: [8, 100],
        msg: 'Password must be at least 8 characters'
      }
    }
  },
  role: {
    type: DataTypes.ENUM('user', 'admin', 'moderator'),
    defaultValue: 'user'
  },
  avatar: {
    type: DataTypes.STRING,
    allowNull: true
  },
  bio: {
    type: DataTypes.TEXT,
    allowNull: true,
    validate: {
      len: {
        args: [0, 500],
        msg: 'Bio cannot exceed 500 characters'
      }
    }
  },
  isActive: {
    type: DataTypes.BOOLEAN,
    defaultValue: true
  },
  lastLoginAt: {
    type: DataTypes.DATE,
    allowNull: true
  }
}, {
  sequelize,
  modelName: 'User',
  tableName: 'users',
  timestamps: true,
  hooks: {
    beforeSave: async (user) => {
      if (user.changed('password')) {
        const salt = await bcrypt.genSalt(12);
        user.password = await bcrypt.hash(user.password, salt);
      }
    }
  },
  indexes: [
    { fields: ['email'] },
    { fields: ['createdAt'] }
  ]
});

module.exports = User;
```

---

## Testing

### Unit Testing with Jest

```javascript
// tests/unit/userService.test.js
const UserService = require('../../src/services/userService');
const User = require('../../src/models/User');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

// Mock dependencies
jest.mock('../../src/models/User');
jest.mock('bcryptjs');
jest.mock('jsonwebtoken');

describe('UserService', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  describe('createUser', () => {
    it('should create a user with hashed password', async () => {
      const userData = {
        name: 'John Doe',
        email: 'john@example.com',
        password: 'password123'
      };

      const hashedPassword = 'hashedPassword123';
      const savedUser = { ...userData, password: hashedPassword, _id: 'user123' };

      bcrypt.hash.mockResolvedValue(hashedPassword);
      User.mockImplementation(() => ({
        save: jest.fn().mockResolvedValue(savedUser)
      }));

      const result = await UserService.createUser(userData);

      expect(bcrypt.hash).toHaveBeenCalledWith(userData.password, 12);
      expect(result).toEqual(savedUser);
    });

    it('should throw error if user creation fails', async () => {
      const userData = {
        name: 'John Doe',
        email: 'john@example.com',
        password: 'password123'
      };

      User.mockImplementation(() => ({
        save: jest.fn().mockRejectedValue(new Error('Database error'))
      }));

      await expect(UserService.createUser(userData)).rejects.toThrow('Database error');
    });
  });

  describe('authenticateUser', () => {
    it('should authenticate user with valid credentials', async () => {
      const email = 'john@example.com';
      const password = 'password123';
      const user = { _id: 'user123', email, password: 'hashedPassword' };

      User.findOne.mockReturnValue({
        select: jest.fn().mockResolvedValue(user)
      });
      bcrypt.compare.mockResolvedValue(true);

      const result = await UserService.authenticateUser(email, password);

      expect(User.findOne).toHaveBeenCalledWith({ email });
      expect(bcrypt.compare).toHaveBeenCalledWith(password, user.password);
      expect(result).toEqual(user);
    });

    it('should throw error for invalid credentials', async () => {
      User.findOne.mockReturnValue({
        select: jest.fn().mockResolvedValue(null)
      });

      await expect(UserService.authenticateUser('invalid@email.com', 'wrongpassword'))
        .rejects.toThrow('Invalid credentials');
    });
  });

  describe('generateToken', () => {
    it('should generate JWT token', () => {
      const userId = 'user123';
      const token = 'jwtToken123';

      jwt.sign.mockReturnValue(token);

      const result = UserService.generateToken(userId);

      expect(jwt.sign).toHaveBeenCalledWith(
        { userId },
        process.env.JWT_SECRET,
        { expiresIn: process.env.JWT_EXPIRES_IN || '7d' }
      );
      expect(result).toBe(token);
    });
  });
});
```

### Integration Testing

```javascript
// tests/integration/auth.test.js
const request = require('supertest');
const app = require('../../src/app');
const User = require('../../src/models/User');
const connectDB = require('../../src/config/database');

describe('Authentication Routes', () => {
  beforeAll(async () => {
    await connectDB();
  });

  afterAll(async () => {
    await User.deleteMany({});
    await mongoose.connection.close();
  });

  beforeEach(async () => {
    await User.deleteMany({});
  });

  describe('POST /api/auth/register', () => {
    it('should register a new user', async () => {
      const userData = {
        name: 'John Doe',
        email: 'john@example.com',
        password: 'Password123!'
      };

      const response = await request(app)
        .post('/api/auth/register')
        .send(userData)
        .expect(201);

      expect(response.body.success).toBe(true);
      expect(response.body.data.name).toBe(userData.name);
      expect(response.body.data.email).toBe(userData.email);
      expect(response.body.data.password).toBeUndefined();

      const user = await User.findOne({ email: userData.email });
      expect(user).toBeTruthy();
    });

    it('should return validation error for invalid data', async () => {
      const invalidData = {
        name: '',
        email: 'invalid-email',
        password: '123'
      };

      const response = await request(app)
        .post('/api/auth/register')
        .send(invalidData)
        .expect(400);

      expect(response.body.success).toBe(false);
      expect(response.body.errors).toBeDefined();
    });

    it('should return error for duplicate email', async () => {
      const userData = {
        name: 'John Doe',
        email: 'john@example.com',
        password: 'Password123!'
      };

      // Create first user
      await request(app)
        .post('/api/auth/register')
        .send(userData)
        .expect(201);

      // Try to create duplicate
      const response = await request(app)
        .post('/api/auth/register')
        .send(userData)
        .expect(409);

      expect(response.body.success).toBe(false);
      expect(response.body.message).toContain('already exists');
    });
  });

  describe('POST /api/auth/login', () => {
    beforeEach(async () => {
      const user = new User({
        name: 'John Doe',
        email: 'john@example.com',
        password: 'Password123!'
      });
      await user.save();
    });

    it('should login with valid credentials', async () => {
      const loginData = {
        email: 'john@example.com',
        password: 'Password123!'
      };

      const response = await request(app)
        .post('/api/auth/login')
        .send(loginData)
        .expect(200);

      expect(response.body.success).toBe(true);
      expect(response.body.data.token).toBeDefined();
      expect(response.body.data.user.email).toBe(loginData.email);
    });

    it('should return error for invalid credentials', async () => {
      const loginData = {
        email: 'john@example.com',
        password: 'WrongPassword'
      };

      const response = await request(app)
        .post('/api/auth/login')
        .send(loginData)
        .expect(401);

      expect(response.body.success).toBe(false);
      expect(response.body.message).toContain('Invalid credentials');
    });
  });

  describe('GET /api/auth/me', () => {
    let authToken;
    let user;

    beforeEach(async () => {
      user = new User({
        name: 'John Doe',
        email: 'john@example.com',
        password: 'Password123!'
      });
      await user.save();

      const loginResponse = await request(app)
        .post('/api/auth/login')
        .send({
          email: 'john@example.com',
          password: 'Password123!'
        });

      authToken = loginResponse.body.data.token;
    });

    it('should return user profile with valid token', async () => {
      const response = await request(app)
        .get('/api/auth/me')
        .set('Authorization', `Bearer ${authToken}`)
        .expect(200);

      expect(response.body.success).toBe(true);
      expect(response.body.data.email).toBe(user.email);
      expect(response.body.data.password).toBeUndefined();
    });

    it('should return error without token', async () => {
      const response = await request(app)
        .get('/api/auth/me')
        .expect(401);

      expect(response.body.success).toBe(false);
      expect(response.body.message).toContain('Access denied');
    });

    it('should return error with invalid token', async () => {
      const response = await request(app)
        .get('/api/auth/me')
        .set('Authorization', 'Bearer invalid-token')
        .expect(401);

      expect(response.body.success).toBe(false);
      expect(response.body.message).toContain('invalid');
    });
  });
});
```

---

## Real-World Example: Blog API

```javascript
// Complete Blog API implementation

// src/models/Post.js
const mongoose = require('mongoose');
const mongoosePaginate = require('mongoose-paginate-v2');

const postSchema = new mongoose.Schema({
  title: {
    type: String,
    required: [true, 'Title is required'],
    trim: true,
    maxlength: [100, 'Title cannot exceed 100 characters']
  },
  slug: {
    type: String,
    unique: true,
    lowercase: true
  },
  content: {
    type: String,
    required: [true, 'Content is required'],
    minlength: [10, 'Content must be at least 10 characters']
  },
  excerpt: {
    type: String,
    maxlength: [200, 'Excerpt cannot exceed 200 characters']
  },
  author: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
    required: true
  },
  tags: [{
    type: String,
    trim: true,
    lowercase: true
  }],
  category: {
    type: String,
    required: true,
    enum: ['technology', 'lifestyle', 'travel', 'food', 'health']
  },
  featuredImage: {
    type: String,
    default: null
  },
  status: {
    type: String,
    enum: ['draft', 'published', 'archived'],
    default: 'draft'
  },
  publishedAt: {
    type: Date,
    default: null
  },
  views: {
    type: Number,
    default: 0
  },
  likes: [{
    user: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User'
    },
    createdAt: {
      type: Date,
      default: Date.now
    }
  }],
  comments: [{
    user: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User'
    },
    content: {
      type: String,
      required: true,
      maxlength: 500
    },
    createdAt: {
      type: Date,
      default: Date.now
    }
  }]
}, {
  timestamps: true,
  toJSON: { virtuals: true },
  toObject: { virtuals: true }
});

// Virtual for like count
postSchema.virtual('likeCount').get(function() {
  return this.likes.length;
});

// Virtual for comment count
postSchema.virtual('commentCount').get(function() {
  return this.comments.length;
});

// Generate slug from title
postSchema.pre('save', function(next) {
  if (this.isModified('title')) {
    this.slug = this.title
      .toLowerCase()
      .replace(/[^a-zA-Z0-9 ]/g, '')
      .replace(/\s+/g, '-');
  }
  
  if (this.isModified('status') && this.status === 'published' && !this.publishedAt) {
    this.publishedAt = new Date();
  }
  
  next();
});

// Generate excerpt from content if not provided
postSchema.pre('save', function(next) {
  if (!this.excerpt && this.content) {
    this.excerpt = this.content.substring(0, 150) + '...';
  }
  next();
});

// Index for better query performance
postSchema.index({ slug: 1 });
postSchema.index({ author: 1 });
postSchema.index({ status: 1, publishedAt: -1 });
postSchema.index({ tags: 1 });
postSchema.index({ category: 1 });

postSchema.plugin(mongoosePaginate);

module.exports = mongoose.model('Post', postSchema);

// src/controllers/postController.js
const Post = require('../models/Post');
const User = require('../models/User');
const asyncHandler = require('../utils/asyncHandler');

class PostController {
  // GET /api/posts
  getAllPosts = asyncHandler(async (req, res) => {
    const {
      page = 1,
      limit = 10,
      status = 'published',
      category,
      author,
      tag,
      search,
      sort = '-publishedAt'
    } = req.query;

    // Build query
    let query = {};

    if (status) {
      query.status = status;
    }

    if (category) {
      query.category = category;
    }

    if (author) {
      query.author = author;
    }

    if (tag) {
      query.tags = { $in: [tag] };
    }

    if (search) {
      query.$or = [
        { title: { $regex: search, $options: 'i' } },
        { content: { $regex: search, $options: 'i' } },
        { tags: { $in: [new RegExp(search, 'i')] } }
      ];
    }

    const options = {
      page: parseInt(page),
      limit: parseInt(limit),
      sort,
      populate: [
        { path: 'author', select: 'name avatar' },
        { path: 'comments.user', select: 'name avatar' }
      ],
      lean: false
    };

    const posts = await Post.paginate(query, options);

    res.json({
      success: true,
      data: posts.docs,
      pagination: {
        page: posts.page,
        limit: posts.limit,
        total: posts.totalDocs,
        pages: posts.totalPages,
        hasNext: posts.hasNextPage,
        hasPrev: posts.hasPrevPage
      }
    });
  });

  // GET /api/posts/:slug
  getPostBySlug = asyncHandler(async (req, res) => {
    const { slug } = req.params;

    const post = await Post.findOne({ slug, status: 'published' })
      .populate('author', 'name avatar bio')
      .populate('comments.user', 'name avatar');

    if (!post) {
      return res.status(404).json({
        success: false,
        message: 'Post not found'
      });
    }

    // Increment view count
    post.views += 1;
    await post.save();

    res.json({
      success: true,
      data: post
    });
  });

  // POST /api/posts
  createPost = asyncHandler(async (req, res) => {
    const { title, content, category, tags, status } = req.body;

    const post = new Post({
      title,
      content,
      category,
      tags: tags ? tags.split(',').map(tag => tag.trim()) : [],
      status: status || 'draft',
      author: req.user._id
    });

    await post.save();
    await post.populate('author', 'name avatar');

    res.status(201).json({
      success: true,
      message: 'Post created successfully',
      data: post
    });
  });

  // PUT /api/posts/:id
  updatePost = asyncHandler(async (req, res) => {
    const { id } = req.params;
    const updates = req.body;

    const post = await Post.findById(id);

    if (!post) {
      return res.status(404).json({
        success: false,
        message: 'Post not found'
      });
    }

    // Check if user is author or admin
    if (post.author.toString() !== req.user._id.toString() && req.user.role !== 'admin') {
      return res.status(403).json({
        success: false,
        message: 'Not authorized to update this post'
      });
    }

    // Process tags
    if (updates.tags && typeof updates.tags === 'string') {
      updates.tags = updates.tags.split(',').map(tag => tag.trim());
    }

    Object.assign(post, updates);
    await post.save();
    await post.populate('author', 'name avatar');

    res.json({
      success: true,
      message: 'Post updated successfully',
      data: post
    });
  });

  // DELETE /api/posts/:id
  deletePost = asyncHandler(async (req, res) => {
    const { id } = req.params;

    const post = await Post.findById(id);

    if (!post) {
      return res.status(404).json({
        success: false,
        message: 'Post not found'
      });
    }

    // Check if user is author or admin
    if (post.author.toString() !== req.user._id.toString() && req.user.role !== 'admin') {
      return res.status(403).json({
        success: false,
        message: 'Not authorized to delete this post'
      });
    }

    await post.deleteOne();

    res.json({
      success: true,
      message: 'Post deleted successfully'
    });
  });

  // POST /api/posts/:id/like
  likePost = asyncHandler(async (req, res) => {
    const { id } = req.params;
    const userId = req.user._id;

    const post = await Post.findById(id);

    if (!post) {
      return res.status(404).json({
        success: false,
        message: 'Post not found'
      });
    }

    // Check if user already liked the post
    const existingLike = post.likes.find(like => 
      like.user.toString() === userId.toString()
    );

    if (existingLike) {
      // Unlike the post
      post.likes = post.likes.filter(like => 
        like.user.toString() !== userId.toString()
      );
    } else {
      // Like the post
      post.likes.push({ user: userId });
    }

    await post.save();

    res.json({
      success: true,
      message: existingLike ? 'Post unliked' : 'Post liked',
      data: {
        likes: post.likeCount,
        isLiked: !existingLike
      }
    });
  });

  // POST /api/posts/:id/comments
  addComment = asyncHandler(async (req, res) => {
    const { id } = req.params;
    const { content } = req.body;

    const post = await Post.findById(id);

    if (!post) {
      return res.status(404).json({
        success: false,
        message: 'Post not found'
      });
    }

    post.comments.push({
      user: req.user._id,
      content
    });

    await post.save();
    await post.populate('comments.user', 'name avatar');

    res.status(201).json({
      success: true,
      message: 'Comment added successfully',
      data: post.comments[post.comments.length - 1]
    });
  });

  // DELETE /api/posts/:id/comments/:commentId
  deleteComment = asyncHandler(async (req, res) => {
    const { id, commentId } = req.params;

    const post = await Post.findById(id);

    if (!post) {
      return res.status(404).json({
        success: false,
        message: 'Post not found'
      });
    }

    const comment = post.comments.id(commentId);

    if (!comment) {
      return res.status(404).json({
        success: false,
        message: 'Comment not found'
      });
    }

    // Check if user is comment author or admin
    if (comment.user.toString() !== req.user._id.toString() && req.user.role !== 'admin') {
      return res.status(403).json({
        success: false,
        message: 'Not authorized to delete this comment'
      });
    }

    comment.deleteOne();
    await post.save();

    res.json({
      success: true,
      message: 'Comment deleted successfully'
    });
  });
}

module.exports = new PostController();

// src/routes/posts.js
const express = require('express');
const router = express.Router();
const postController = require('../controllers/postController');
const { auth, authorize } = require('../middleware/auth');
const { postValidationRules, handleValidationErrors } = require('../middleware/validation');

// Public routes
router.get('/', postController.getAllPosts);
router.get('/:slug', postController.getPostBySlug);

// Protected routes
router.use(auth);

router.post('/',
  postValidationRules(),
  handleValidationErrors,
  postController.createPost
);

router.put('/:id',
  postValidationRules(),
  handleValidationErrors,
  postController.updatePost
);

router.delete('/:id', postController.deletePost);

// Post interactions
router.post('/:id/like', postController.likePost);
router.post('/:id/comments', postController.addComment);
router.delete('/:id/comments/:commentId', postController.deleteComment);

module.exports = router;
```

---

## Best Practices Summary

### 1. **Project Structure**
- Use clear folder organization
- Separate concerns (controllers, services, middleware)
- Follow consistent naming conventions
- Keep configuration in environment variables

### 2. **Security**
- Use helmet for security headers
- Implement rate limiting
- Validate and sanitize input data
- Use proper authentication and authorization
- Never expose sensitive information

### 3. **Performance**
- Use compression middleware
- Implement caching strategies
- Optimize database queries
- Use pagination for large datasets
- Monitor and profile application performance

### 4. **Error Handling**
- Use centralized error handling
- Provide meaningful error messages
- Log errors appropriately
- Use async error handling patterns

### 5. **Testing**
- Write unit tests for business logic
- Create integration tests for API endpoints
- Use test databases
- Mock external dependencies
- Maintain good test coverage

This comprehensive Express.js guide covers modern backend development patterns suitable for production applications, from basic setup to advanced features like authentication, file uploads, and testing.