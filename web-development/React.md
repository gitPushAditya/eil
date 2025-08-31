# React - Modern React Development

React is a JavaScript library for building user interfaces. This guide covers modern React with hooks, context, performance optimization, and production-ready patterns for professional development.

## Setup and Environment

### Create React App

```bash
# Create new React app
npx create-react-app my-app
cd my-app

# With TypeScript
npx create-react-app my-app --template typescript

# With additional tools
npm install --save-dev eslint prettier husky lint-staged
npm install axios react-router-dom @testing-library/react @testing-library/jest-dom

# Modern alternative: Vite
npm create vite@latest my-react-app -- --template react
cd my-react-app
npm install
```

### Project Structure

```
src/
├── components/          # Reusable UI components
│   ├── common/         # Common components (Button, Input, etc.)
│   ├── layout/         # Layout components (Header, Footer, etc.)
│   └── ui/             # UI-specific components
├── hooks/              # Custom hooks
├── contexts/           # React contexts
├── pages/              # Page components
├── services/           # API services
├── utils/              # Utility functions
├── types/              # TypeScript types (if using TS)
├── styles/             # CSS/SCSS files
├── assets/             # Images, fonts, etc.
├── __tests__/          # Test files
├── App.js
└── index.js
```

### ESLint Configuration

```json
// .eslintrc.json
{
  "extends": [
    "react-app",
    "react-app/jest"
  ],
  "rules": {
    "react/prop-types": "error",
    "react/jsx-key": "error",
    "react/jsx-no-duplicate-props": "error",
    "react/jsx-no-undef": "error",
    "react/jsx-uses-react": "error",
    "react/jsx-uses-vars": "error",
    "react/no-danger": "warn",
    "react/no-deprecated": "error",
    "react/no-direct-mutation-state": "error",
    "react/no-unknown-property": "error",
    "react/require-render-return": "error",
    "react/self-closing-comp": "warn"
  }
}
```

---

## Components and JSX

### Functional Components

```jsx
import { useState, useCallback } from 'react';

// Basic functional component
function Welcome(props) {
  return <h1>Hello, {props.name}!</h1>;
}

// Arrow function component
const Welcome = (props) => {
  return <h1>Hello, {props.name}!</h1>;
};

// Component with destructured props
const UserCard = ({ name, email, avatar, isOnline }) => {
  return (
    <div className={`user-card ${isOnline ? 'online' : 'offline'}`}>
      <img src={avatar} alt={`${name}'s avatar`} />
      <div className="user-info">
        <h3>{name}</h3>
        <p>{email}</p>
        <span className="status">{isOnline ? 'Online' : 'Offline'}</span>
      </div>
    </div>
  );
};

// Component with default props
const Button = ({ 
  children, 
  variant = 'primary', 
  size = 'medium', 
  disabled = false,
  onClick = () => {},
  ...props 
}) => {
  return (
    <button
      className={`btn btn-${variant} btn-${size}`}
      disabled={disabled}
      onClick={onClick}
      {...props}
    >
      {children}
    </button>
  );
};

// Higher-order component pattern
const withLoading = (WrappedComponent) => {
  return function WithLoadingComponent({ isLoading, ...props }) {
    if (isLoading) {
      return <div className="loading">Loading...</div>;
    }
    return <WrappedComponent {...props} />;
  };
};

const EnhancedUserCard = withLoading(UserCard);
```

### JSX Best Practices

```jsx
import { useState } from 'react';

// Conditional rendering
const PostList = ({ posts, isLoading, error }) => {
  // Early return for error state
  if (error) {
    return <div className="error">Error: {error.message}</div>;
  }

  return (
    <div className="post-list">
      {/* Conditional rendering with logical AND */}
      {isLoading && <div className="loading">Loading posts...</div>}
      
      {/* Conditional rendering with ternary */}
      {posts.length > 0 ? (
        <ul>
          {posts.map(post => (
            <PostItem key={post.id} post={post} />
          ))}
        </ul>
      ) : (
        <div className="empty-state">No posts found</div>
      )}
    </div>
  );
};

// Fragments to avoid extra DOM nodes
const UserProfile = () => {
  return (
    <>
      <UserInfo />
      <UserStats />
      <UserPosts />
    </>
  );
};

// React.Fragment with key (useful in lists)
const DefinitionList = ({ terms }) => {
  return (
    <dl>
      {terms.map(term => (
        <React.Fragment key={term.id}>
          <dt>{term.term}</dt>
          <dd>{term.definition}</dd>
        </React.Fragment>
      ))}
    </dl>
  );
};

// Event handling
const ContactForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    message: ''
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Form submitted:', formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        name="name"
        value={formData.name}
        onChange={handleInputChange}
        placeholder="Your name"
        required
      />
      <input
        type="email"
        name="email"
        value={formData.email}
        onChange={handleInputChange}
        placeholder="Your email"
        required
      />
      <textarea
        name="message"
        value={formData.message}
        onChange={handleInputChange}
        placeholder="Your message"
        required
      />
      <button type="submit">Send Message</button>
    </form>
  );
};

// Dynamic class names
const Alert = ({ type, message, isVisible }) => {
  const alertClasses = [
    'alert',
    `alert-${type}`,
    isVisible && 'alert-visible',
    message.length > 100 && 'alert-long'
  ].filter(Boolean).join(' ');

  return (
    <div className={alertClasses}>
      {message}
    </div>
  );
};
```

---

## Hooks

### useState Hook

```jsx
import { useState } from 'react';

// Basic state
const Counter = () => {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <button onClick={() => setCount(count - 1)}>Decrement</button>
      <button onClick={() => setCount(0)}>Reset</button>
    </div>
  );
};

// Object state
const UserForm = () => {
  const [user, setUser] = useState({
    name: '',
    email: '',
    age: ''
  });

  const updateUser = (field, value) => {
    setUser(prevUser => ({
      ...prevUser,
      [field]: value
    }));
  };

  return (
    <form>
      <input
        value={user.name}
        onChange={(e) => updateUser('name', e.target.value)}
        placeholder="Name"
      />
      <input
        value={user.email}
        onChange={(e) => updateUser('email', e.target.value)}
        placeholder="Email"
      />
      <input
        value={user.age}
        onChange={(e) => updateUser('age', e.target.value)}
        placeholder="Age"
        type="number"
      />
    </form>
  );
};

// Array state
const TodoList = () => {
  const [todos, setTodos] = useState([]);
  const [inputValue, setInputValue] = useState('');

  const addTodo = () => {
    if (inputValue.trim()) {
      setTodos(prevTodos => [
        ...prevTodos,
        {
          id: Date.now(),
          text: inputValue,
          completed: false
        }
      ]);
      setInputValue('');
    }
  };

  const toggleTodo = (id) => {
    setTodos(prevTodos =>
      prevTodos.map(todo =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  const deleteTodo = (id) => {
    setTodos(prevTodos => prevTodos.filter(todo => todo.id !== id));
  };

  return (
    <div>
      <div>
        <input
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
          onKeyPress={(e) => e.key === 'Enter' && addTodo()}
          placeholder="Add todo"
        />
        <button onClick={addTodo}>Add</button>
      </div>
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            <span
              style={{
                textDecoration: todo.completed ? 'line-through' : 'none'
              }}
              onClick={() => toggleTodo(todo.id)}
            >
              {todo.text}
            </span>
            <button onClick={() => deleteTodo(todo.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

// Lazy initial state
const ExpensiveComponent = () => {
  const [data, setData] = useState(() => {
    // This function only runs once on initial render
    console.log('Computing initial state...');
    return { value: Math.random() * 1000 };
  });

  return <div>Data: {data.value}</div>;
};
```

### useEffect Hook

```jsx
import { useState, useEffect } from 'react';

// Basic effect
const WindowWidth = () => {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener('resize', handleResize);

    // Cleanup function
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []); // Empty dependency array - runs once

  return <div>Window width: {windowWidth}px</div>;
};

// Effect with dependencies
const UserProfile = ({ userId }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await fetch(`/api/users/${userId}`);
        
        if (!response.ok) {
          throw new Error('Failed to fetch user');
        }
        
        const userData = await response.json();
        setUser(userData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, [userId]); // Runs when userId changes

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!user) return <div>User not found</div>;

  return (
    <div>
      <h2>{user.name}</h2>
      <p>{user.email}</p>
    </div>
  );
};

// Multiple effects for separation of concerns
const ChatRoom = ({ roomId }) => {
  const [messages, setMessages] = useState([]);
  const [onlineUsers, setOnlineUsers] = useState([]);

  // Effect for messages
  useEffect(() => {
    const socket = new WebSocket(`ws://localhost:8080/rooms/${roomId}`);
    
    socket.onmessage = (event) => {
      const message = JSON.parse(event.data);
      setMessages(prev => [...prev, message]);
    };

    return () => {
      socket.close();
    };
  }, [roomId]);

  // Effect for online users
  useEffect(() => {
    const interval = setInterval(async () => {
      const response = await fetch(`/api/rooms/${roomId}/users`);
      const users = await response.json();
      setOnlineUsers(users);
    }, 5000);

    return () => {
      clearInterval(interval);
    };
  }, [roomId]);

  // Effect for document title
  useEffect(() => {
    document.title = `Chat Room ${roomId}`;
    
    return () => {
      document.title = 'My App';
    };
  }, [roomId]);

  return (
    <div>
      <div>Online: {onlineUsers.length}</div>
      <div>
        {messages.map(message => (
          <div key={message.id}>{message.text}</div>
        ))}
      </div>
    </div>
  );
};

// Conditional effects
const Timer = ({ isActive }) => {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    if (!isActive) return;

    const interval = setInterval(() => {
      setSeconds(prev => prev + 1);
    }, 1000);

    return () => clearInterval(interval);
  }, [isActive]);

  return <div>Timer: {seconds}s</div>;
};
```

### Custom Hooks

```jsx
import { useState, useEffect, useCallback } from 'react';

// useLocalStorage hook
function useLocalStorage(key, initialValue) {
  const [storedValue, setStoredValue] = useState(() => {
    try {
      const item = window.localStorage.getItem(key);
      return item ? JSON.parse(item) : initialValue;
    } catch (error) {
      console.error(`Error reading localStorage key "${key}":`, error);
      return initialValue;
    }
  });

  const setValue = (value) => {
    try {
      const valueToStore = value instanceof Function ? value(storedValue) : value;
      setStoredValue(valueToStore);
      window.localStorage.setItem(key, JSON.stringify(valueToStore));
    } catch (error) {
      console.error(`Error setting localStorage key "${key}":`, error);
    }
  };

  return [storedValue, setValue];
}

// useFetch hook
function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!url) return;
    
    const abortController = new AbortController();

    const fetchData = async () => {
      try {
        setLoading(true);
        setError(null);
        
        const response = await fetch(url, {
          signal: abortController.signal
        });
        
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const result = await response.json();
        setData(result);
      } catch (err) {
        if (err.name !== 'AbortError') {
          setError(err);
        }
      } finally {
        setLoading(false);
      }
    };

    fetchData();

    return () => {
      abortController.abort();
    };
  }, [url]);

  return { data, loading, error };
}

// useDebounce hook
function useDebounce(value, delay) {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedValue(value);
    }, delay);

    return () => {
      clearTimeout(handler);
    };
  }, [value, delay]);

  return debouncedValue;
}

// useToggle hook
function useToggle(initialValue = false) {
  const [value, setValue] = useState(initialValue);

  const toggle = useCallback(() => setValue(prev => !prev), []);
  const setTrue = useCallback(() => setValue(true), []);
  const setFalse = useCallback(() => setValue(false), []);

  return [value, { toggle, setTrue, setFalse }];
}

// useCounter hook
function useCounter(initialValue = 0, step = 1) {
  const [count, setCount] = useState(initialValue);
  
  const increment = useCallback(() => {
    setCount(prev => prev + step);
  }, [step]);
  
  const decrement = useCallback(() => {
    setCount(prev => prev - step);
  }, [step]);
  
  const reset = useCallback(() => {
    setCount(initialValue);
  }, [initialValue]);
  
  return {
    count,
    increment,
    decrement,
    reset,
    setCount
  };
}

// Usage examples
const Settings = () => {
  const [theme, setTheme] = useLocalStorage('theme', 'light');
  const [isMenuOpen, menuActions] = useToggle(false);
  const counter = useCounter(0, 5);

  return (
    <div className={`app ${theme}`}>
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
      
      <button onClick={menuActions.toggle}>
        {isMenuOpen ? 'Close' : 'Open'} Menu
      </button>
      
      <div>
        <p>Count: {counter.count}</p>
        <button onClick={counter.increment}>+5</button>
        <button onClick={counter.decrement}>-5</button>
        <button onClick={counter.reset}>Reset</button>
      </div>
      
      {isMenuOpen && <div>Menu content</div>}
    </div>
  );
};

const SearchResults = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const debouncedSearchTerm = useDebounce(searchTerm, 500);
  const { data: results, loading } = useFetch(
    debouncedSearchTerm ? `/api/search?q=${debouncedSearchTerm}` : null
  );

  return (
    <div>
      <input
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        placeholder="Search..."
      />
      
      {loading && <div>Searching...</div>}
      
      {results && (
        <ul>
          {results.map(result => (
            <li key={result.id}>{result.title}</li>
          ))}
        </ul>
      )}
    </div>
  );
};
```

---

## Context API

### Creating and Using Context

```jsx
import { createContext, useContext, useReducer, useState, useEffect } from 'react';

// Theme Context
const ThemeContext = createContext();

const ThemeProvider = ({ children }) => {
  const [theme, setTheme] = useState('light');
  
  const toggleTheme = () => {
    setTheme(prevTheme => prevTheme === 'light' ? 'dark' : 'light');
  };
  
  const value = {
    theme,
    toggleTheme
  };
  
  return (
    <ThemeContext.Provider value={value}>
      {children}
    </ThemeContext.Provider>
  );
};

// Custom hook for using theme context
const useTheme = () => {
  const context = useContext(ThemeContext);
  
  if (!context) {
    throw new Error('useTheme must be used within a ThemeProvider');
  }
  
  return context;
};

// User Context with useReducer
const UserContext = createContext();

const userReducer = (state, action) => {
  switch (action.type) {
    case 'LOGIN':
      return {
        ...state,
        user: action.payload,
        isAuthenticated: true,
        loading: false
      };
    
    case 'LOGOUT':
      return {
        ...state,
        user: null,
        isAuthenticated: false,
        loading: false
      };
    
    case 'SET_LOADING':
      return {
        ...state,
        loading: action.payload
      };
    
    case 'SET_ERROR':
      return {
        ...state,
        error: action.payload,
        loading: false
      };
    
    default:
      return state;
  }
};

const UserProvider = ({ children }) => {
  const [state, dispatch] = useReducer(userReducer, {
    user: null,
    isAuthenticated: false,
    loading: true,
    error: null
  });
  
  const login = async (credentials) => {
    try {
      dispatch({ type: 'SET_LOADING', payload: true });
      
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials)
      });
      
      if (!response.ok) {
        throw new Error('Login failed');
      }
      
      const user = await response.json();
      dispatch({ type: 'LOGIN', payload: user });
      
      // Store token in localStorage
      localStorage.setItem('token', user.token);
    } catch (error) {
      dispatch({ type: 'SET_ERROR', payload: error.message });
    }
  };
  
  const logout = () => {
    dispatch({ type: 'LOGOUT' });
    localStorage.removeItem('token');
  };
  
  const checkAuthStatus = async () => {
    const token = localStorage.getItem('token');
    
    if (!token) {
      dispatch({ type: 'SET_LOADING', payload: false });
      return;
    }
    
    try {
      const response = await fetch('/api/me', {
        headers: { Authorization: `Bearer ${token}` }
      });
      
      if (response.ok) {
        const user = await response.json();
        dispatch({ type: 'LOGIN', payload: user });
      } else {
        localStorage.removeItem('token');
        dispatch({ type: 'SET_LOADING', payload: false });
      }
    } catch (error) {
      dispatch({ type: 'SET_ERROR', payload: error.message });
    }
  };
  
  useEffect(() => {
    checkAuthStatus();
  }, []);
  
  const value = {
    ...state,
    login,
    logout
  };
  
  return (
    <UserContext.Provider value={value}>
      {children}
    </UserContext.Provider>
  );
};

const useUser = () => {
  const context = useContext(UserContext);
  
  if (!context) {
    throw new Error('useUser must be used within a UserProvider');
  }
  
  return context;
};

// Shopping Cart Context
const CartContext = createContext();

const cartReducer = (state, action) => {
  switch (action.type) {
    case 'ADD_ITEM':
      const existingItem = state.items.find(item => item.id === action.payload.id);
      
      if (existingItem) {
        return {
          ...state,
          items: state.items.map(item =>
            item.id === action.payload.id
              ? { ...item, quantity: item.quantity + 1 }
              : item
          )
        };
      }
      
      return {
        ...state,
        items: [...state.items, { ...action.payload, quantity: 1 }]
      };
    
    case 'REMOVE_ITEM':
      return {
        ...state,
        items: state.items.filter(item => item.id !== action.payload)
      };
    
    case 'UPDATE_QUANTITY':
      return {
        ...state,
        items: state.items.map(item =>
          item.id === action.payload.id
            ? { ...item, quantity: action.payload.quantity }
            : item
        )
      };
    
    case 'CLEAR_CART':
      return {
        ...state,
        items: []
      };
    
    default:
      return state;
  }
};

const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, {
    items: []
  });
  
  const addItem = (product) => {
    dispatch({ type: 'ADD_ITEM', payload: product });
  };
  
  const removeItem = (productId) => {
    dispatch({ type: 'REMOVE_ITEM', payload: productId });
  };
  
  const updateQuantity = (productId, quantity) => {
    if (quantity <= 0) {
      removeItem(productId);
    } else {
      dispatch({ 
        type: 'UPDATE_QUANTITY', 
        payload: { id: productId, quantity } 
      });
    }
  };
  
  const clearCart = () => {
    dispatch({ type: 'CLEAR_CART' });
  };
  
  const getItemCount = () => {
    return state.items.reduce((total, item) => total + item.quantity, 0);
  };
  
  const getTotalPrice = () => {
    return state.items.reduce((total, item) => total + item.price * item.quantity, 0);
  };
  
  const value = {
    items: state.items,
    addItem,
    removeItem,
    updateQuantity,
    clearCart,
    getItemCount,
    getTotalPrice
  };
  
  return (
    <CartContext.Provider value={value}>
      {children}
    </CartContext.Provider>
  );
};

const useCart = () => {
  const context = useContext(CartContext);
  
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  
  return context;
};

// Combined Provider Component
const AppProviders = ({ children }) => {
  return (
    <ThemeProvider>
      <UserProvider>
        <CartProvider>
          {children}
        </CartProvider>
      </UserProvider>
    </ThemeProvider>
  );
};

// Using contexts in components
const Header = () => {
  const { theme, toggleTheme } = useTheme();
  const { user, logout } = useUser();
  const { getItemCount } = useCart();
  
  return (
    <header className={`header ${theme}`}>
      <h1>My App</h1>
      
      <div className="header-controls">
        <button onClick={toggleTheme}>
          {theme === 'light' ? '🌙' : '☀️'}
        </button>
        
        <div className="cart-indicator">
          Cart ({getItemCount()})
        </div>
        
        {user ? (
          <div className="user-menu">
            <span>Welcome, {user.name}</span>
            <button onClick={logout}>Logout</button>
          </div>
        ) : (
          <button>Login</button>
        )}
      </div>
    </header>
  );
};

const ProductCard = ({ product }) => {
  const { addItem } = useCart();
  
  return (
    <div className="product-card">
      <img src={product.image} alt={product.name} />
      <h3>{product.name}</h3>
      <p>${product.price}</p>
      <button onClick={() => addItem(product)}>
        Add to Cart
      </button>
    </div>
  );
};
```

---

## Performance Optimization

### React.memo and Memoization

```jsx
import { useMemo, useCallback, memo } from 'react';

// React.memo for component memoization
const ExpensiveComponent = memo(({ data, onUpdate }) => {
  console.log('ExpensiveComponent render');
  
  const processedData = useMemo(() => {
    return data.map(item => ({
      ...item,
      processed: true,
      value: item.value * 2
    }));
  }, [data]);
  
  return (
    <div>
      {processedData.map(item => (
        <div key={item.id}>{item.name}: {item.value}</div>
      ))}
    </div>
  );
});

// Custom comparison function for React.memo
const UserCard = memo(({ user, onEdit }) => {
  return (
    <div className="user-card">
      <h3>{user.name}</h3>
      <p>{user.email}</p>
      <button onClick={() => onEdit(user.id)}>Edit</button>
    </div>
  );
}, (prevProps, nextProps) => {
  // Custom comparison - only re-render if user data changed
  return (
    prevProps.user.id === nextProps.user.id &&
    prevProps.user.name === nextProps.user.name &&
    prevProps.user.email === nextProps.user.email
  );
});

// useMemo for expensive calculations
const DataTable = ({ items, searchTerm, sortBy }) => {
  const filteredAndSortedItems = useMemo(() => {
    console.log('Processing data...');
    
    let filtered = items.filter(item =>
      item.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    
    return filtered.sort((a, b) => {
      if (sortBy === 'name') {
        return a.name.localeCompare(b.name);
      }
      if (sortBy === 'date') {
        return new Date(b.date) - new Date(a.date);
      }
      return 0;
    });
  }, [items, searchTerm, sortBy]);
  
  return (
    <table>
      <tbody>
        {filteredAndSortedItems.map(item => (
          <tr key={item.id}>
            <td>{item.name}</td>
            <td>{item.date}</td>
            <td>{item.value}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

// useCallback for event handlers
const TodoList = ({ todos, onToggle, onDelete }) => {
  const [filter, setFilter] = useState('all');
  
  // Memoize filter function
  const handleFilterChange = useCallback((newFilter) => {
    setFilter(newFilter);
  }, []);
  
  // Memoize filtered todos
  const filteredTodos = useMemo(() => {
    switch (filter) {
      case 'active':
        return todos.filter(todo => !todo.completed);
      case 'completed':
        return todos.filter(todo => todo.completed);
      default:
        return todos;
    }
  }, [todos, filter]);
  
  return (
    <div>
      <FilterButtons filter={filter} onFilterChange={handleFilterChange} />
      <div>
        {filteredTodos.map(todo => (
          <TodoItem
            key={todo.id}
            todo={todo}
            onToggle={onToggle}
            onDelete={onDelete}
          />
        ))}
      </div>
    </div>
  );
};

const TodoItem = memo(({ todo, onToggle, onDelete }) => {
  const handleToggle = useCallback(() => {
    onToggle(todo.id);
  }, [todo.id, onToggle]);
  
  const handleDelete = useCallback(() => {
    onDelete(todo.id);
  }, [todo.id, onDelete]);
  
  return (
    <div className={`todo-item ${todo.completed ? 'completed' : ''}`}>
      <span onClick={handleToggle}>{todo.text}</span>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
});
```

### Lazy Loading and Code Splitting

```jsx
import { lazy, Suspense } from 'react';

// Lazy load components
const LazyDashboard = lazy(() => import('./components/Dashboard'));
const LazyProfile = lazy(() => import('./components/Profile'));
const LazySettings = lazy(() => import('./components/Settings'));

// Component with dynamic import
const LazyChart = lazy(() => 
  import('./components/Chart').then(module => ({
    default: module.Chart
  }))
);

// Loading component
const LoadingSpinner = () => (
  <div className="loading-spinner">
    <div className="spinner"></div>
    <p>Loading...</p>
  </div>
);

// Error boundary for lazy components
class LazyLoadErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }
  
  static getDerivedStateFromError(error) {
    return { hasError: true };
  }
  
  componentDidCatch(error, errorInfo) {
    console.error('Lazy load error:', error, errorInfo);
  }
  
  render() {
    if (this.state.hasError) {
      return (
        <div className="error-boundary">
          <h2>Something went wrong loading this component.</h2>
          <button onClick={() => this.setState({ hasError: false })}>
            Try again
          </button>
        </div>
      );
    }
    
    return this.props.children;
  }
}

// App with lazy loading
const App = () => {
  const [currentPage, setCurrentPage] = useState('dashboard');
  
  const renderPage = () => {
    switch (currentPage) {
      case 'dashboard':
        return <LazyDashboard />;
      case 'profile':
        return <LazyProfile />;
      case 'settings':
        return <LazySettings />;
      default:
        return <LazyDashboard />;
    }
  };
  
  return (
    <div className="app">
      <nav>
        <button onClick={() => setCurrentPage('dashboard')}>Dashboard</button>
        <button onClick={() => setCurrentPage('profile')}>Profile</button>
        <button onClick={() => setCurrentPage('settings')}>Settings</button>
      </nav>
      
      <main>
        <LazyLoadErrorBoundary>
          <Suspense fallback={<LoadingSpinner />}>
            {renderPage()}
          </Suspense>
        </LazyLoadErrorBoundary>
      </main>
    </div>
  );
};

// Preloading components
const ComponentPreloader = () => {
  useEffect(() => {
    // Preload components on user interaction
    const preloadDashboard = () => import('./components/Dashboard');
    const preloadProfile = () => import('./components/Profile');
    
    // Preload on hover
    const dashboardLink = document.querySelector('[data-preload="dashboard"]');
    if (dashboardLink) {
      dashboardLink.addEventListener('mouseenter', preloadDashboard, { once: true });
    }
    
    // Preload after initial render
    setTimeout(() => {
      preloadProfile();
    }, 2000);
  }, []);
  
  return null;
};
```

---

## Real-World Example: Task Management App

```jsx
import React, { useState, useEffect, useContext, createContext, useReducer, useCallback, useMemo } from 'react';

// Task context and reducer
const TaskContext = createContext();

const taskReducer = (state, action) => {
  switch (action.type) {
    case 'ADD_TASK':
      return {
        ...state,
        tasks: [...state.tasks, {
          id: Date.now(),
          text: action.payload.text,
          completed: false,
          priority: action.payload.priority || 'medium',
          category: action.payload.category || 'general',
          createdAt: new Date(),
          dueDate: action.payload.dueDate
        }]
      };
    
    case 'TOGGLE_TASK':
      return {
        ...state,
        tasks: state.tasks.map(task =>
          task.id === action.payload
            ? { ...task, completed: !task.completed }
            : task
        )
      };
    
    case 'DELETE_TASK':
      return {
        ...state,
        tasks: state.tasks.filter(task => task.id !== action.payload)
      };
    
    case 'UPDATE_TASK':
      return {
        ...state,
        tasks: state.tasks.map(task =>
          task.id === action.payload.id
            ? { ...task, ...action.payload.updates }
            : task
        )
      };
    
    case 'LOAD_TASKS':
      return {
        ...state,
        tasks: action.payload,
        loading: false
      };
    
    case 'SET_LOADING':
      return {
        ...state,
        loading: action.payload
      };
    
    case 'SET_FILTER':
      return {
        ...state,
        filter: action.payload
      };
    
    default:
      return state;
  }
};

// Task Provider
const TaskProvider = ({ children }) => {
  const [state, dispatch] = useReducer(taskReducer, {
    tasks: [],
    filter: 'all',
    loading: true
  });
  
  // Load tasks from localStorage on mount
  useEffect(() => {
    const savedTasks = localStorage.getItem('tasks');
    if (savedTasks) {
      dispatch({ type: 'LOAD_TASKS', payload: JSON.parse(savedTasks) });
    } else {
      dispatch({ type: 'SET_LOADING', payload: false });
    }
  }, []);
  
  // Save tasks to localStorage when tasks change
  useEffect(() => {
    if (!state.loading) {
      localStorage.setItem('tasks', JSON.stringify(state.tasks));
    }
  }, [state.tasks, state.loading]);
  
  const addTask = useCallback((taskData) => {
    dispatch({ type: 'ADD_TASK', payload: taskData });
  }, []);
  
  const toggleTask = useCallback((id) => {
    dispatch({ type: 'TOGGLE_TASK', payload: id });
  }, []);
  
  const deleteTask = useCallback((id) => {
    dispatch({ type: 'DELETE_TASK', payload: id });
  }, []);
  
  const updateTask = useCallback((id, updates) => {
    dispatch({ type: 'UPDATE_TASK', payload: { id, updates } });
  }, []);
  
  const setFilter = useCallback((filter) => {
    dispatch({ type: 'SET_FILTER', payload: filter });
  }, []);
  
  const value = {
    ...state,
    addTask,
    toggleTask,
    deleteTask,
    updateTask,
    setFilter
  };
  
  return (
    <TaskContext.Provider value={value}>
      {children}
    </TaskContext.Provider>
  );
};

const useTask = () => {
  const context = useContext(TaskContext);
  if (!context) {
    throw new Error('useTask must be used within TaskProvider');
  }
  return context;
};

// Main Task App
const TaskApp = () => {
  const { tasks, filter, loading } = useTask();
  const [searchTerm, setSearchTerm] = useState('');
  const debouncedSearchTerm = useDebounce(searchTerm, 300);
  
  const filteredTasks = useMemo(() => {
    let filtered = tasks;
    
    // Filter by status
    if (filter === 'active') {
      filtered = filtered.filter(task => !task.completed);
    } else if (filter === 'completed') {
      filtered = filtered.filter(task => task.completed);
    }
    
    // Filter by search term
    if (debouncedSearchTerm) {
      filtered = filtered.filter(task =>
        task.text.toLowerCase().includes(debouncedSearchTerm.toLowerCase()) ||
        task.category.toLowerCase().includes(debouncedSearchTerm.toLowerCase())
      );
    }
    
    // Sort by priority and due date
    return filtered.sort((a, b) => {
      const priorityOrder = { high: 3, medium: 2, low: 1 };
      const aPriority = priorityOrder[a.priority] || 0;
      const bPriority = priorityOrder[b.priority] || 0;
      
      if (aPriority !== bPriority) {
        return bPriority - aPriority;
      }
      
      if (a.dueDate && b.dueDate) {
        return new Date(a.dueDate) - new Date(b.dueDate);
      }
      
      return new Date(b.createdAt) - new Date(a.createdAt);
    });
  }, [tasks, filter, debouncedSearchTerm]);
  
  const taskStats = useMemo(() => {
    const total = tasks.length;
    const completed = tasks.filter(task => task.completed).length;
    const active = total - completed;
    const overdue = tasks.filter(task => 
      task.dueDate && 
      new Date(task.dueDate) < new Date() && 
      !task.completed
    ).length;
    
    return { total, completed, active, overdue };
  }, [tasks]);
  
  if (loading) {
    return <div className="loading">Loading tasks...</div>;
  }
  
  return (
    <div className="task-app">
      <header className="app-header">
        <h1>Task Manager</h1>
        <TaskStats stats={taskStats} />
      </header>
      
      <div className="app-content">
        <div className="task-controls">
          <SearchInput 
            value={searchTerm}
            onChange={setSearchTerm}
            placeholder="Search tasks..."
          />
          <TaskFilter />
          <AddTaskForm />
        </div>
        
        <TaskList tasks={filteredTasks} />
      </div>
    </div>
  );
};

// Task Stats Component
const TaskStats = memo(({ stats }) => (
  <div className="task-stats">
    <div className="stat">
      <span className="stat-label">Total</span>
      <span className="stat-value">{stats.total}</span>
    </div>
    <div className="stat">
      <span className="stat-label">Active</span>
      <span className="stat-value">{stats.active}</span>
    </div>
    <div className="stat">
      <span className="stat-label">Completed</span>
      <span className="stat-value">{stats.completed}</span>
    </div>
    {stats.overdue > 0 && (
      <div className="stat overdue">
        <span className="stat-label">Overdue</span>
        <span className="stat-value">{stats.overdue}</span>
      </div>
    )}
  </div>
));

// Search Input Component
const SearchInput = memo(({ value, onChange, placeholder }) => (
  <div className="search-input">
    <input
      type="text"
      value={value}
      onChange={(e) => onChange(e.target.value)}
      placeholder={placeholder}
      className="search-field"
    />
  </div>
));

// Task Filter Component
const TaskFilter = () => {
  const { filter, setFilter } = useTask();
  
  const filters = [
    { key: 'all', label: 'All' },
    { key: 'active', label: 'Active' },
    { key: 'completed', label: 'Completed' }
  ];
  
  return (
    <div className="task-filter">
      {filters.map(f => (
        <button
          key={f.key}
          className={`filter-btn ${filter === f.key ? 'active' : ''}`}
          onClick={() => setFilter(f.key)}
        >
          {f.label}
        </button>
      ))}
    </div>
  );
};

// Add Task Form Component
const AddTaskForm = () => {
  const { addTask } = useTask();
  const [formData, setFormData] = useState({
    text: '',
    priority: 'medium',
    category: 'general',
    dueDate: ''
  });
  const [isExpanded, setIsExpanded] = useState(false);
  
  const handleSubmit = (e) => {
    e.preventDefault();
    if (formData.text.trim()) {
      addTask(formData);
      setFormData({
        text: '',
        priority: 'medium',
        category: 'general',
        dueDate: ''
      });
      setIsExpanded(false);
    }
  };
  
  const handleChange = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }));
  };
  
  return (
    <form className="add-task-form" onSubmit={handleSubmit}>
      <div className="form-row">
        <input
          type="text"
          value={formData.text}
          onChange={(e) => handleChange('text', e.target.value)}
          placeholder="Add a new task..."
          className="task-input"
          onFocus={() => setIsExpanded(true)}
        />
        <button type="submit" className="add-btn">
          Add
        </button>
      </div>
      
      {isExpanded && (
        <div className="form-details">
          <select
            value={formData.priority}
            onChange={(e) => handleChange('priority', e.target.value)}
            className="priority-select"
          >
            <option value="low">Low Priority</option>
            <option value="medium">Medium Priority</option>
            <option value="high">High Priority</option>
          </select>
          
          <input
            type="text"
            value={formData.category}
            onChange={(e) => handleChange('category', e.target.value)}
            placeholder="Category"
            className="category-input"
          />
          
          <input
            type="date"
            value={formData.dueDate}
            onChange={(e) => handleChange('dueDate', e.target.value)}
            className="date-input"
          />
          
          <button
            type="button"
            onClick={() => setIsExpanded(false)}
            className="cancel-btn"
          >
            Cancel
          </button>
        </div>
      )}
    </form>
  );
};

// Task List Component
const TaskList = memo(({ tasks }) => {
  if (tasks.length === 0) {
    return (
      <div className="empty-state">
        <p>No tasks found</p>
      </div>
    );
  }
  
  return (
    <div className="task-list">
      {tasks.map(task => (
        <TaskItem key={task.id} task={task} />
      ))}
    </div>
  );
});

// Task Item Component
const TaskItem = memo(({ task }) => {
  const { toggleTask, deleteTask, updateTask } = useTask();
  const [isEditing, setIsEditing] = useState(false);
  const [editText, setEditText] = useState(task.text);
  
  const handleToggle = useCallback(() => {
    toggleTask(task.id);
  }, [task.id, toggleTask]);
  
  const handleDelete = useCallback(() => {
    deleteTask(task.id);
  }, [task.id, deleteTask]);
  
  const handleEdit = useCallback(() => {
    if (isEditing) {
      updateTask(task.id, { text: editText });
      setIsEditing(false);
    } else {
      setIsEditing(true);
    }
  }, [task.id, editText, isEditing, updateTask]);
  
  const handleCancel = useCallback(() => {
    setEditText(task.text);
    setIsEditing(false);
  }, [task.text]);
  
  const isOverdue = task.dueDate && new Date(task.dueDate) < new Date() && !task.completed;
  
  return (
    <div className={`task-item ${task.completed ? 'completed' : ''} ${isOverdue ? 'overdue' : ''}`}>
      <div className="task-content">
        <input
          type="checkbox"
          checked={task.completed}
          onChange={handleToggle}
          className="task-checkbox"
        />
        
        {isEditing ? (
          <input
            type="text"
            value={editText}
            onChange={(e) => setEditText(e.target.value)}
            className="task-edit-input"
            autoFocus
            onKeyDown={(e) => {
              if (e.key === 'Enter') handleEdit();
              if (e.key === 'Escape') handleCancel();
            }}
          />
        ) : (
          <span className="task-text">{task.text}</span>
        )}
        
        <div className="task-meta">
          <span className={`priority priority-${task.priority}`}>
            {task.priority}
          </span>
          <span className="category">{task.category}</span>
          {task.dueDate && (
            <span className="due-date">
              Due: {new Date(task.dueDate).toLocaleDateString()}
            </span>
          )}
        </div>
      </div>
      
      <div className="task-actions">
        {isEditing ? (
          <>
            <button onClick={handleEdit} className="save-btn">
              Save
            </button>
            <button onClick={handleCancel} className="cancel-btn">
              Cancel
            </button>
          </>
        ) : (
          <>
            <button onClick={handleEdit} className="edit-btn">
              Edit
            </button>
            <button onClick={handleDelete} className="delete-btn">
              Delete
            </button>
          </>
        )}
      </div>
    </div>
  );
});

// Main App with Providers
const App = () => {
  return (
    <TaskProvider>
      <div className="app">
        <TaskApp />
      </div>
    </TaskProvider>
  );
};

export default App;
```

---

## Best Practices Summary

### 1. **Component Design**
- Keep components small and focused
- Use functional components with hooks
- Implement proper prop validation
- Follow consistent naming conventions

### 2. **State Management**
- Use useState for local state
- Use useReducer for complex state logic
- Lift state up when needed
- Use Context for global state

### 3. **Performance**
- Memoize expensive calculations with useMemo
- Memoize callbacks with useCallback
- Use React.memo for component memoization
- Implement lazy loading for large components

### 4. **Hooks**
- Follow the rules of hooks
- Create custom hooks for reusable logic
- Use useEffect properly with dependencies
- Clean up effects to prevent memory leaks

### 5. **Error Handling**
- Implement error boundaries
- Handle async errors properly
- Provide fallback UI for failed states
- Log errors for debugging

This comprehensive React guide covers modern patterns and best practices for building production-ready applications with functional components, hooks, context, and performance optimization techniques.