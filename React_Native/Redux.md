# Redux Basics

Redux is a app wide state manager for react and react native apps.

```
npm install @reduxjs/toolkit react-redux
```

## Store.js

We create a file `store.js` which stores all teh state of app in an immutable object.

```js
import { configureStore } from "@reduxjs/toolkit";

export const store = configureStore({
  reducer: {
    // Empty for now - This is a basic structure
  },
});
```

---

## Layout File

In main app file, we have to surround it with provider.

```js
import {store} ...
import {Provider} from 'react-redux';

<Provider store={store}>
--- App Data
</Provider>
```

---

## Feature Folder

This will contain slices(sub folders) for managing data app wide, for eg. One data will be managed by one slice.

Let say, we create 'features' folder and then 'counter' folder in it then 'counterSlice.js'

```js
import { createSlice, nanoid } from "@reduxjs/toolkit";

const initialState = {
  count: 0,
};

export const counterSlice = createSlice({
  name: "counter",
  initialState,
  reducers: {
    increment: (state) => {
      state.count += 1;
    },
    decrement: (state) => {
      state.count -= 1;
    },
    reset: (state) => {
      state.count = 0;
    },
    incrementByAmount: (state, action) => {
      state.count += action.payload;
    },
  },
});

export const { increment, decrement, reset, incrementByAmount } =
  counterSlice.actions;

export default counterSlice.reducer;
```

Now in the store, add this -

```js
import { configureStore } from "@reduxjs/toolkit";
import counterReducer from ...

export const store = configureStore({
  reducer: {
    counter: counterReducer,
  },
});
```

To use them -

```js
import {useSelector, useDispatch} from "react-redux";
import {useState} from "react";
import {increment, decrement, reset, incrementByAmount} ...

// Inside component

const count = useSelector((state) => state.counter.count);
const dispatch = useDispatch();

 // To use functions inside component -

... onClick={()=> dispatch(increment())}
... onClick={() => dispatch(incrementByAmount(value))}

```

---

## Using Async Operations:

```js
// index.js

import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import App from './App'

import { store } from './redux/config/store'
import { Provider } from 'react-redux'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
)

// store.js

import { configureStore } from '@reduxjs/toolkit'
import contentSlice from '../slice/contentSlice'

export const store = configureStore({
  reducer: {
    content: contentSlice,
  },
})

// contentSlice.js

import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const initialState = {
  contents: [],
  isLoading: false,
  error: null,
}

export const fetchContent = createAsyncThunk(
  'content/fetchContent',
  async () => {
    const res = await axios('https://jsonplaceholder.typicode.com/photos')
    const data = await res.data
    return data
  }
)

export const contentSlice = createSlice({
  name: 'content',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchContent.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(fetchContent.fulfilled, (state, action) => {
      state.isLoading = false
      state.contents = action.payload
    })
    builder.addCase(fetchContent.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
  },
})

export default contentSlice.reducer


// App.js

function App() {
  const dispatch = useDispatch()

  useEffect(() => {
    dispatch(fetchContent())
  }, [dispatch])

  const contents = useSelector((state) => state.content.contents)
  const isLoading = useSelector((state) => state.content.isLoading)
  const error = useSelector((state) => state.content.error)

  if (isLoading) {
    return 'loading...'
  }

  if (error) {
    return error
  }

  return (
    <div className='grid gap-4 grid-cols-2  md:grid-cols-4 lg:grid-cols-8  p-4'>
      {contents.map((content) => (
        <div key={content.id}>
          <img
            src={`${content.thumbnailUrl}`}
            alt={`${content.title}`}
            className='w-full h-full rounded'
          />
        </div>
      ))}
    </div>
  )
}

export default App

```
