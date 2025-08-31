# React

## Introduction to React

React is a popular JavaScript library for building user interfaces, particularly single-page applications. It was developed by Facebook and allows developers to create reusable UI components and manage application state effectively.

---

## Getting Started with React

### Create React App

```bash
npx create-react-app my-app
cd my-app
npm start
```

### Basic React Component

```jsx
import React from 'react';

function Welcome(props) {
    return <h1>Hello, {props.name}!</h1>;
}

// Arrow function component
const Welcome = (props) => {
    return <h1>Hello, {props.name}!</h1>;
};

export default Welcome;
```

---

## JSX (JavaScript XML)

JSX allows you to write HTML-like syntax in JavaScript:

```jsx
const element = <h1>Hello, World!</h1>;

// JSX with expressions
const name = 'John';
const element = <h1>Hello, {name}!</h1>;

// JSX with attributes
const element = <img src={user.avatarUrl} alt={user.name} />;

// JSX with children
const element = (
    <div>
        <h1>Welcome!</h1>
        <p>Good to see you here.</p>
    </div>
);
```

---

## Components

### Functional Components

```jsx
import React from 'react';

const Greeting = ({ name, age }) => {
    return (
        <div>
            <h2>Hello, {name}!</h2>
            <p>You are {age} years old.</p>
        </div>
    );
};

export default Greeting;
```

### Class Components (Legacy)

```jsx
import React, { Component } from 'react';

class Greeting extends Component {
    render() {
        const { name, age } = this.props;
        return (
            <div>
                <h2>Hello, {name}!</h2>
                <p>You are {age} years old.</p>
            </div>
        );
    }
}

export default Greeting;
```

---

## State Management

### useState Hook

```jsx
import React, { useState } from 'react';

const Counter = () => {
    const [count, setCount] = useState(0);

    const increment = () => setCount(count + 1);
    const decrement = () => setCount(count - 1);

    return (
        <div>
            <h2>Count: {count}</h2>
            <button onClick={increment}>+</button>
            <button onClick={decrement}>-</button>
        </div>
    );
};

export default Counter;
```

### Multiple State Variables

```jsx
const UserProfile = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [age, setAge] = useState(0);

    return (
        <form>
            <input 
                type="text" 
                value={name} 
                onChange={(e) => setName(e.target.value)}
                placeholder="Name"
            />
            <input 
                type="email" 
                value={email} 
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
            />
            <input 
                type="number" 
                value={age} 
                onChange={(e) => setAge(parseInt(e.target.value))}
                placeholder="Age"
            />
        </form>
    );
};
```

---

## Effects and Lifecycle

### useEffect Hook

```jsx
import React, { useState, useEffect } from 'react';

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);

    // Effect runs after component mounts and updates
    useEffect(() => {
        fetchUsers();
    }, []); // Empty dependency array means it runs once

    // Effect with dependencies
    useEffect(() => {
        document.title = `${users.length} users`;
    }, [users]); // Runs when users array changes

    // Effect with cleanup
    useEffect(() => {
        const timer = setInterval(() => {
            console.log('Timer tick');
        }, 1000);

        return () => {
            clearInterval(timer); // Cleanup function
        };
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await fetch('/api/users');
            const userData = await response.json();
            setUsers(userData);
        } catch (error) {
            console.error('Error fetching users:', error);
        } finally {
            setLoading(false);
        }
    };

    if (loading) return <div>Loading...</div>;

    return (
        <ul>
            {users.map(user => (
                <li key={user.id}>{user.name}</li>
            ))}
        </ul>
    );
};
```

---

## Props and Component Communication

### Passing Props

```jsx
const App = () => {
    const user = { name: 'John', age: 25 };
    
    return (
        <div>
            <UserCard user={user} />
            <Greeting name="Alice" age={30} />
        </div>
    );
};

const UserCard = ({ user }) => {
    return (
        <div className="user-card">
            <h3>{user.name}</h3>
            <p>Age: {user.age}</p>
        </div>
    );
};
```

### Prop Types (Optional)

```jsx
import PropTypes from 'prop-types';

const UserCard = ({ user }) => {
    return (
        <div className="user-card">
            <h3>{user.name}</h3>
            <p>Age: {user.age}</p>
        </div>
    );
};

UserCard.propTypes = {
    user: PropTypes.shape({
        name: PropTypes.string.isRequired,
        age: PropTypes.number.isRequired
    }).isRequired
};
```

---

## Event Handling

```jsx
const Form = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        message: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form submitted:', formData);
        // Handle form submission
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                placeholder="Name"
            />
            <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                placeholder="Email"
            />
            <textarea
                name="message"
                value={formData.message}
                onChange={handleInputChange}
                placeholder="Message"
            />
            <button type="submit">Submit</button>
        </form>
    );
};
```

---

## Conditional Rendering

```jsx
const UserDashboard = ({ user, isLoggedIn }) => {
    // Conditional rendering with &&
    return (
        <div>
            {isLoggedIn && <h1>Welcome back, {user.name}!</h1>}
            
            {/* Conditional rendering with ternary operator */}
            {isLoggedIn ? (
                <UserProfile user={user} />
            ) : (
                <LoginForm />
            )}
            
            {/* Conditional rendering with if statement */}
            {(() => {
                if (user.role === 'admin') {
                    return <AdminPanel />;
                } else if (user.role === 'moderator') {
                    return <ModeratorPanel />;
                } else {
                    return <UserPanel />;
                }
            })()}
        </div>
    );
};
```

---

## Lists and Keys

```jsx
const TodoList = ({ todos }) => {
    return (
        <ul>
            {todos.map(todo => (
                <li key={todo.id} className={todo.completed ? 'completed' : ''}>
                    <input 
                        type="checkbox" 
                        checked={todo.completed}
                        onChange={() => toggleTodo(todo.id)}
                    />
                    <span>{todo.text}</span>
                    <button onClick={() => deleteTodo(todo.id)}>Delete</button>
                </li>
            ))}
        </ul>
    );
};
```

---

## Best Practices

1. **Use functional components with hooks** - Preferred over class components
2. **Keep components small and focused** - Single responsibility principle
3. **Use meaningful component and variable names**
4. **Extract custom hooks** - For reusable stateful logic
5. **Avoid inline function definitions** - Can cause unnecessary re-renders
6. **Use keys properly** - Stable, unique identifiers for list items
7. **Handle loading and error states** - Provide good user experience
8. **Use React Developer Tools** - For debugging and performance optimization
9. **Follow the principle of immutability** - Don't mutate state directly
10. **Use TypeScript** - For better type safety and development experience

---