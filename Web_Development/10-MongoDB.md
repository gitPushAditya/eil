# MongoDB

## Introduction to MongoDB

MongoDB is a NoSQL, document-oriented database that stores data in flexible, JSON-like documents. It's designed for scalability, performance, and high availability, making it popular for modern web applications.

---

## Installation and Setup

### Installation

```bash
# macOS with Homebrew
brew tap mongodb/brew
brew install mongodb-community

# Ubuntu/Debian
sudo apt-get install -y mongodb

# Start MongoDB service
sudo systemctl start mongod
sudo systemctl enable mongod
```

### MongoDB Compass (GUI)
Download from: https://www.mongodb.com/products/compass

### MongoDB Atlas (Cloud)
Sign up at: https://www.mongodb.com/cloud/atlas

---

## Basic Concepts

### Document Structure

```javascript
// Example document in a users collection
{
  "_id": ObjectId("64a7b8c9e1234567890abcde"),
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30,
  "address": {
    "street": "123 Main St",
    "city": "New York",
    "zipCode": "10001"
  },
  "hobbies": ["reading", "swimming", "coding"],
  "createdAt": ISODate("2023-07-07T10:00:00Z"),
  "isActive": true
}
```

### Data Types

```javascript
{
  "string": "Hello World",
  "number": 42,
  "double": 3.14159,
  "boolean": true,
  "date": new Date(),
  "objectId": ObjectId("64a7b8c9e1234567890abcde"),
  "array": [1, 2, 3, "four"],
  "object": { "nested": "value" },
  "null": null,
  "binary": BinData(0, "..."),
  "regex": /pattern/i
}
```

---

## MongoDB Shell (mongosh)

### Basic Commands

```javascript
// Show databases
show dbs

// Use/create database
use myapp

// Show collections
show collections

// Show current database
db

// Database stats
db.stats()

// Drop database
db.dropDatabase()
```

### Collection Operations

```javascript
// Create collection
db.createCollection("users")

// Drop collection
db.users.drop()

// Collection stats
db.users.stats()

// Rename collection
db.users.renameCollection("customers")
```

---

## CRUD Operations

### Create (Insert)

```javascript
// Insert one document
db.users.insertOne({
  name: "John Doe",
  email: "john@example.com",
  age: 30
})

// Insert multiple documents
db.users.insertMany([
  {
    name: "Alice Smith",
    email: "alice@example.com",
    age: 25
  },
  {
    name: "Bob Johnson",
    email: "bob@example.com",
    age: 35
  }
])

// Insert with custom _id
db.users.insertOne({
  _id: "custom-id-001",
  name: "Custom User",
  email: "custom@example.com"
})
```

### Read (Find)

```javascript
// Find all documents
db.users.find()

// Find with pretty formatting
db.users.find().pretty()

// Find one document
db.users.findOne()

// Find by condition
db.users.find({ age: 30 })

// Find with multiple conditions
db.users.find({
  age: { $gte: 25 },
  name: "John Doe"
})

// Find with projection (select specific fields)
db.users.find(
  { age: { $gte: 25 } },
  { name: 1, email: 1, _id: 0 }
)

// Find with limit and skip
db.users.find().limit(5).skip(10)

// Find with sorting
db.users.find().sort({ age: 1 })  // 1 for ascending, -1 for descending

// Count documents
db.users.countDocuments({ age: { $gte: 25 } })
```

### Update

```javascript
// Update one document
db.users.updateOne(
  { name: "John Doe" },
  { $set: { age: 31, lastModified: new Date() } }
)

// Update multiple documents
db.users.updateMany(
  { age: { $lt: 30 } },
  { $set: { category: "young" } }
)

// Replace entire document
db.users.replaceOne(
  { name: "John Doe" },
  {
    name: "John Doe",
    email: "john.doe.new@example.com",
    age: 31,
    status: "updated"
  }
)

// Upsert (update or insert)
db.users.updateOne(
  { email: "new@example.com" },
  { $set: { name: "New User", age: 25 } },
  { upsert: true }
)

// Increment a field
db.users.updateOne(
  { name: "John Doe" },
  { $inc: { age: 1 } }
)

// Add to array
db.users.updateOne(
  { name: "John Doe" },
  { $push: { hobbies: "cooking" } }
)

// Remove from array
db.users.updateOne(
  { name: "John Doe" },
  { $pull: { hobbies: "cooking" } }
)
```

### Delete

```javascript
// Delete one document
db.users.deleteOne({ name: "John Doe" })

// Delete multiple documents
db.users.deleteMany({ age: { $lt: 25 } })

// Delete all documents in collection
db.users.deleteMany({})
```

---

## Query Operators

### Comparison Operators

```javascript
// Equal
db.users.find({ age: 30 })

// Not equal
db.users.find({ age: { $ne: 30 } })

// Greater than
db.users.find({ age: { $gt: 25 } })

// Greater than or equal
db.users.find({ age: { $gte: 25 } })

// Less than
db.users.find({ age: { $lt: 35 } })

// Less than or equal
db.users.find({ age: { $lte: 35 } })

// In array
db.users.find({ age: { $in: [25, 30, 35] } })

// Not in array
db.users.find({ age: { $nin: [25, 30, 35] } })
```

### Logical Operators

```javascript
// AND
db.users.find({
  $and: [
    { age: { $gte: 25 } },
    { age: { $lte: 35 } }
  ]
})

// OR
db.users.find({
  $or: [
    { age: { $lt: 25 } },
    { age: { $gt: 35 } }
  ]
})

// NOT
db.users.find({
  age: { $not: { $gte: 30 } }
})

// NOR
db.users.find({
  $nor: [
    { age: { $lt: 25 } },
    { name: "John Doe" }
  ]
})
```

### Element Operators

```javascript
// Field exists
db.users.find({ email: { $exists: true } })

// Field type
db.users.find({ age: { $type: "number" } })

// Array size
db.users.find({ hobbies: { $size: 3 } })
```

### Array Operators

```javascript
// All elements match
db.users.find({ hobbies: { $all: ["reading", "swimming"] } })

// At least one element matches
db.users.find({ hobbies: { $elemMatch: { $eq: "reading" } } })

// Array element at index
db.users.find({ "hobbies.0": "reading" })
```

---

## Aggregation Pipeline

### Basic Aggregation

```javascript
// Match and group
db.orders.aggregate([
  { $match: { status: "completed" } },
  { $group: {
    _id: "$customerId",
    totalAmount: { $sum: "$amount" },
    orderCount: { $sum: 1 }
  }}
])

// Sort and limit
db.users.aggregate([
  { $sort: { age: -1 } },
  { $limit: 5 }
])

// Project (select fields)
db.users.aggregate([
  { $project: {
    name: 1,
    email: 1,
    ageGroup: {
      $cond: {
        if: { $gte: ["$age", 30] },
        then: "senior",
        else: "junior"
      }
    }
  }}
])
```

### Advanced Aggregation

```javascript
// Lookup (join)
db.orders.aggregate([
  {
    $lookup: {
      from: "users",
      localField: "userId",
      foreignField: "_id",
      as: "user"
    }
  },
  { $unwind: "$user" },
  {
    $project: {
      orderId: "$_id",
      amount: 1,
      userName: "$user.name",
      userEmail: "$user.email"
    }
  }
])

// Group with complex operations
db.sales.aggregate([
  {
    $group: {
      _id: {
        year: { $year: "$date" },
        month: { $month: "$date" }
      },
      totalSales: { $sum: "$amount" },
      averageSale: { $avg: "$amount" },
      maxSale: { $max: "$amount" },
      minSale: { $min: "$amount" },
      salesCount: { $sum: 1 }
    }
  },
  { $sort: { "_id.year": 1, "_id.month": 1 } }
])

// Facet (multiple pipelines)
db.products.aggregate([
  {
    $facet: {
      "categoryCounts": [
        { $group: { _id: "$category", count: { $sum: 1 } } }
      ],
      "priceRanges": [
        {
          $bucket: {
            groupBy: "$price",
            boundaries: [0, 50, 100, 200, 500],
            default: "Other",
            output: { count: { $sum: 1 } }
          }
        }
      ]
    }
  }
])
```

---

## Indexing

### Create Indexes

```javascript
// Single field index
db.users.createIndex({ email: 1 })

// Compound index
db.users.createIndex({ name: 1, age: -1 })

// Text index for search
db.posts.createIndex({ title: "text", content: "text" })

// Unique index
db.users.createIndex({ email: 1 }, { unique: true })

// Partial index
db.users.createIndex(
  { age: 1 },
  { partialFilterExpression: { age: { $gt: 18 } } }
)

// TTL index (Time To Live)
db.sessions.createIndex(
  { createdAt: 1 },
  { expireAfterSeconds: 3600 }
)
```

### Index Management

```javascript
// List indexes
db.users.getIndexes()

// Drop index
db.users.dropIndex({ email: 1 })

// Drop all indexes except _id
db.users.dropIndexes()

// Reindex collection
db.users.reIndex()

// Index statistics
db.users.getIndexStats()
```

---

## Using MongoDB with Node.js

### MongoDB Driver

```javascript
const { MongoClient } = require('mongodb');

const uri = 'mongodb://localhost:27017';
const client = new MongoClient(uri);

async function run() {
  try {
    await client.connect();
    const database = client.db('myapp');
    const users = database.collection('users');

    // Insert
    const insertResult = await users.insertOne({
      name: 'John Doe',
      email: 'john@example.com',
      age: 30
    });
    console.log('Inserted document with _id:', insertResult.insertedId);

    // Find
    const findResult = await users.findOne({ name: 'John Doe' });
    console.log('Found user:', findResult);

    // Update
    const updateResult = await users.updateOne(
      { name: 'John Doe' },
      { $set: { age: 31 } }
    );
    console.log('Modified count:', updateResult.modifiedCount);

    // Delete
    const deleteResult = await users.deleteOne({ name: 'John Doe' });
    console.log('Deleted count:', deleteResult.deletedCount);

  } finally {
    await client.close();
  }
}

run().catch(console.dir);
```

### Mongoose ODM

```javascript
const mongoose = require('mongoose');

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/myapp');

// Define schema
const userSchema = new mongoose.Schema({
  name: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  age: { type: Number, min: 0, max: 150 },
  hobbies: [String],
  address: {
    street: String,
    city: String,
    zipCode: String
  },
  createdAt: { type: Date, default: Date.now },
  isActive: { type: Boolean, default: true }
});

// Add methods
userSchema.methods.getFullAddress = function() {
  return `${this.address.street}, ${this.address.city} ${this.address.zipCode}`;
};

// Add static methods
userSchema.statics.findByEmail = function(email) {
  return this.findOne({ email: email });
};

// Create model
const User = mongoose.model('User', userSchema);

// Usage examples
async function examples() {
  // Create user
  const user = new User({
    name: 'John Doe',
    email: 'john@example.com',
    age: 30,
    hobbies: ['reading', 'swimming'],
    address: {
      street: '123 Main St',
      city: 'New York',
      zipCode: '10001'
    }
  });
  
  await user.save();

  // Find users
  const users = await User.find({ age: { $gte: 25 } });
  const userByEmail = await User.findByEmail('john@example.com');

  // Update user
  await User.updateOne(
    { email: 'john@example.com' },
    { $set: { age: 31 } }
  );

  // Delete user
  await User.deleteOne({ email: 'john@example.com' });
}
```

---

## Schema Design Patterns

### Embedding Documents

```javascript
// Good for one-to-few relationships
{
  "_id": ObjectId("..."),
  "name": "John Doe",
  "addresses": [
    {
      "type": "home",
      "street": "123 Main St",
      "city": "New York",
      "zipCode": "10001"
    },
    {
      "type": "work",
      "street": "456 Office Ave",
      "city": "New York",
      "zipCode": "10002"
    }
  ]
}
```

### Referencing Documents

```javascript
// User document
{
  "_id": ObjectId("64a7b8c9e1234567890abcde"),
  "name": "John Doe",
  "email": "john@example.com"
}

// Order documents
{
  "_id": ObjectId("64a7b8c9e1234567890abcdf"),
  "userId": ObjectId("64a7b8c9e1234567890abcde"),
  "amount": 99.99,
  "date": ISODate("2023-07-07T10:00:00Z")
}
```

### Hybrid Approach

```javascript
// Blog post with embedded comments and referenced author
{
  "_id": ObjectId("..."),
  "title": "My Blog Post",
  "content": "...",
  "authorId": ObjectId("..."),
  "author": {
    "name": "John Doe",
    "avatar": "avatar.jpg"
  },
  "comments": [
    {
      "authorId": ObjectId("..."),
      "author": {
        "name": "Jane Smith",
        "avatar": "jane.jpg"
      },
      "text": "Great post!",
      "date": ISODate("...")
    }
  ]
}
```

---

## Performance and Optimization

### Query Optimization

```javascript
// Use explain() to analyze query performance
db.users.find({ age: { $gte: 25 } }).explain("executionStats")

// Create appropriate indexes
db.users.createIndex({ age: 1 })

// Use projection to limit returned data
db.users.find(
  { age: { $gte: 25 } },
  { name: 1, email: 1, _id: 0 }
)

// Use limit() for pagination
db.users.find().sort({ createdAt: -1 }).limit(20)
```

### Connection Pooling

```javascript
const { MongoClient } = require('mongodb');

const client = new MongoClient(uri, {
  maxPoolSize: 10, // Maximum number of connections
  serverSelectionTimeoutMS: 5000, // Keep trying to send operations for 5 seconds
  socketTimeoutMS: 45000, // Close sockets after 45 seconds of inactivity
  bufferMaxEntries: 0
});
```

---

## Best Practices

1. **Design for your queries** - Structure documents based on how you'll query them
2. **Use appropriate data types** - Choose the right type for each field
3. **Create effective indexes** - Index fields you query frequently
4. **Avoid large documents** - Keep documents under 16MB limit
5. **Use projections** - Only fetch the fields you need
6. **Implement proper error handling** - Handle connection and query errors
7. **Use aggregation pipeline** - For complex data transformations
8. **Monitor performance** - Use MongoDB profiler and explain()
9. **Plan for scaling** - Consider sharding for large datasets
10. **Secure your database** - Enable authentication and use proper access controls
11. **Backup regularly** - Implement backup and disaster recovery strategies
12. **Keep MongoDB updated** - Use the latest stable version for security and performance

---