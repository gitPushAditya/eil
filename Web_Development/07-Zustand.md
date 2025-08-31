# Zustand

## Introduction to Zustand

Zustand is a small, fast, and scalable state management library for React applications. It provides a simple API for managing global state without the complexity of Redux or Context API boilerplate.

---

## Installation

```bash
npm install zustand
# or
yarn add zustand
```

---

## Basic Store Creation

### Simple Store

```javascript
import { create } from 'zustand';

const useStore = create((set) => ({
  // State
  count: 0,
  name: 'John',
  
  // Actions
  increment: () => set((state) => ({ count: state.count + 1 })),
  decrement: () => set((state) => ({ count: state.count - 1 })),
  setName: (name) => set({ name }),
  reset: () => set({ count: 0, name: 'John' }),
}));

export default useStore;
```

### Using the Store in Components

```jsx
import React from 'react';
import useStore from './store';

const Counter = () => {
  const { count, increment, decrement, reset } = useStore();

  return (
    <div>
      <h2>Count: {count}</h2>
      <button onClick={increment}>+</button>
      <button onClick={decrement}>-</button>
      <button onClick={reset}>Reset</button>
    </div>
  );
};

const NameDisplay = () => {
  const { name, setName } = useStore();

  return (
    <div>
      <h3>Name: {name}</h3>
      <input 
        value={name} 
        onChange={(e) => setName(e.target.value)} 
        placeholder="Enter name"
      />
    </div>
  );
};
```

---

## Advanced Store Patterns

### Slicing Stores

```javascript
import { create } from 'zustand';

// User slice
const createUserSlice = (set, get) => ({
  user: null,
  isAuthenticated: false,
  login: async (credentials) => {
    try {
      const user = await api.login(credentials);
      set({ user, isAuthenticated: true });
    } catch (error) {
      console.error('Login failed:', error);
    }
  },
  logout: () => set({ user: null, isAuthenticated: false }),
});

// Cart slice
const createCartSlice = (set, get) => ({
  items: [],
  total: 0,
  addItem: (item) => set((state) => {
    const newItems = [...state.items, item];
    return {
      items: newItems,
      total: newItems.reduce((sum, item) => sum + item.price, 0)
    };
  }),
  removeItem: (id) => set((state) => {
    const newItems = state.items.filter(item => item.id !== id);
    return {
      items: newItems,
      total: newItems.reduce((sum, item) => sum + item.price, 0)
    };
  }),
  clearCart: () => set({ items: [], total: 0 }),
});

// Combined store
const useStore = create((set, get) => ({
  ...createUserSlice(set, get),
  ...createCartSlice(set, get),
}));
```

---

## Async Actions

```javascript
import { create } from 'zustand';

const useStore = create((set, get) => ({
  // State
  todos: [],
  loading: false,
  error: null,

  // Async actions
  fetchTodos: async () => {
    set({ loading: true, error: null });
    try {
      const response = await fetch('/api/todos');
      const todos = await response.json();
      set({ todos, loading: false });
    } catch (error) {
      set({ error: error.message, loading: false });
    }
  },

  addTodo: async (todoText) => {
    set({ loading: true });
    try {
      const response = await fetch('/api/todos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: todoText, completed: false }),
      });
      const newTodo = await response.json();
      set((state) => ({ 
        todos: [...state.todos, newTodo],
        loading: false 
      }));
    } catch (error) {
      set({ error: error.message, loading: false });
    }
  },

  updateTodo: async (id, updates) => {
    try {
      const response = await fetch(`/api/todos/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updates),
      });
      const updatedTodo = await response.json();
      
      set((state) => ({
        todos: state.todos.map(todo => 
          todo.id === id ? updatedTodo : todo
        )
      }));
    } catch (error) {
      set({ error: error.message });
    }
  },

  deleteTodo: async (id) => {
    try {
      await fetch(`/api/todos/${id}`, { method: 'DELETE' });
      set((state) => ({
        todos: state.todos.filter(todo => todo.id !== id)
      }));
    } catch (error) {
      set({ error: error.message });
    }
  },
}));
```

---

## Middleware

### Persist Middleware

```javascript
import { create } from 'zustand';
import { persist } from 'zustand/middleware';

const useStore = create(
  persist(
    (set) => ({
      // State
      preferences: {
        theme: 'light',
        language: 'en',
        notifications: true,
      },
      
      // Actions
      setTheme: (theme) => set((state) => ({
        preferences: { ...state.preferences, theme }
      })),
      
      setLanguage: (language) => set((state) => ({
        preferences: { ...state.preferences, language }
      })),
      
      toggleNotifications: () => set((state) => ({
        preferences: { 
          ...state.preferences, 
          notifications: !state.preferences.notifications 
        }
      })),
    }),
    {
      name: 'user-preferences', // Storage key
      // Optional: custom storage
      // storage: createJSONStorage(() => sessionStorage),
    }
  )
);
```

### Immer Middleware

```javascript
import { create } from 'zustand';
import { immer } from 'zustand/middleware/immer';

const useStore = create(
  immer((set) => ({
    // Nested state
    user: {
      profile: {
        name: '',
        email: '',
        preferences: {
          theme: 'light',
          notifications: true,
        },
      },
      posts: [],
    },

    // Immer allows direct mutations
    updateUserName: (name) => set((state) => {
      state.user.profile.name = name;
    }),

    updateUserEmail: (email) => set((state) => {
      state.user.profile.email = email;
    }),

    toggleTheme: () => set((state) => {
      state.user.profile.preferences.theme = 
        state.user.profile.preferences.theme === 'light' ? 'dark' : 'light';
    }),

    addPost: (post) => set((state) => {
      state.user.posts.push(post);
    }),

    removePost: (postId) => set((state) => {
      const index = state.user.posts.findIndex(post => post.id === postId);
      if (index !== -1) {
        state.user.posts.splice(index, 1);
      }
    }),
  }))
);
```

---

## Selectors and Performance

### Using Selectors

```javascript
import { create } from 'zustand';
import { shallow } from 'zustand/shallow';

const useStore = create((set) => ({
  users: [],
  currentUserId: null,
  filter: '',
  
  setUsers: (users) => set({ users }),
  setCurrentUser: (id) => set({ currentUserId: id }),
  setFilter: (filter) => set({ filter }),
}));

// Efficient selectors
const useCurrentUser = () => useStore(
  (state) => state.users.find(user => user.id === state.currentUserId)
);

const useFilteredUsers = () => useStore(
  (state) => state.users.filter(user => 
    user.name.toLowerCase().includes(state.filter.toLowerCase())
  )
);

// Multiple values with shallow comparison
const useUserActions = () => useStore(
  (state) => ({
    setUsers: state.setUsers,
    setCurrentUser: state.setCurrentUser,
    setFilter: state.setFilter,
  }),
  shallow
);

// Component usage
const UserList = () => {
  const filteredUsers = useFilteredUsers();
  const { setCurrentUser, setFilter } = useUserActions();
  
  return (
    <div>
      <input 
        onChange={(e) => setFilter(e.target.value)}
        placeholder="Filter users..."
      />
      {filteredUsers.map(user => (
        <div key={user.id} onClick={() => setCurrentUser(user.id)}>
          {user.name}
        </div>
      ))}
    </div>
  );
};
```

---

## Testing Zustand Stores

```javascript
import { create } from 'zustand';
import { act, renderHook } from '@testing-library/react';

// Store for testing
const createStore = () => create((set) => ({
  count: 0,
  increment: () => set((state) => ({ count: state.count + 1 })),
  decrement: () => set((state) => ({ count: state.count - 1 })),
  reset: () => set({ count: 0 }),
}));

describe('Counter Store', () => {
  let useStore;

  beforeEach(() => {
    useStore = createStore();
  });

  test('initial state', () => {
    const { result } = renderHook(() => useStore());
    expect(result.current.count).toBe(0);
  });

  test('increment', () => {
    const { result } = renderHook(() => useStore());
    
    act(() => {
      result.current.increment();
    });
    
    expect(result.current.count).toBe(1);
  });

  test('decrement', () => {
    const { result } = renderHook(() => useStore());
    
    act(() => {
      result.current.increment();
      result.current.decrement();
    });
    
    expect(result.current.count).toBe(0);
  });

  test('reset', () => {
    const { result } = renderHook(() => useStore());
    
    act(() => {
      result.current.increment();
      result.current.increment();
      result.current.reset();
    });
    
    expect(result.current.count).toBe(0);
  });
});
```

---

## Store Organization

### Multiple Stores

```javascript
// stores/authStore.js
export const useAuthStore = create((set) => ({
  user: null,
  isAuthenticated: false,
  login: async (credentials) => { /* ... */ },
  logout: () => set({ user: null, isAuthenticated: false }),
}));

// stores/cartStore.js
export const useCartStore = create((set) => ({
  items: [],
  total: 0,
  addItem: (item) => { /* ... */ },
  removeItem: (id) => { /* ... */ },
}));

// stores/index.js
export { useAuthStore } from './authStore';
export { useCartStore } from './cartStore';
```

### Store Composition

```javascript
import { create } from 'zustand';
import { subscribeWithSelector } from 'zustand/middleware';

const useStore = create(
  subscribeWithSelector((set, get) => ({
    // State
    items: [],
    filter: 'all',
    
    // Actions
    addItem: (item) => set((state) => ({ 
      items: [...state.items, item] 
    })),
    
    setFilter: (filter) => set({ filter }),
    
    // Computed values
    get filteredItems() {
      const { items, filter } = get();
      if (filter === 'completed') {
        return items.filter(item => item.completed);
      }
      if (filter === 'active') {
        return items.filter(item => !item.completed);
      }
      return items;
    },
  }))
);

// Subscribe to changes
useStore.subscribe(
  (state) => state.items,
  (items) => {
    console.log('Items changed:', items);
  }
);
```

---

## Best Practices

1. **Keep stores focused** - One store per domain/feature
2. **Use TypeScript** - For better type safety and IntelliSense
3. **Avoid nested state** - Keep state structure flat when possible
4. **Use selectors** - For performance optimization
5. **Separate actions from state** - Keep actions pure and testable
6. **Use middleware wisely** - Only when necessary (persist, immer)
7. **Test your stores** - Unit test store logic independently
8. **Avoid deep object mutations** - Use spread operator or Immer
9. **Use shallow comparison** - When selecting multiple values
10. **Document your store** - Add comments for complex logic

---