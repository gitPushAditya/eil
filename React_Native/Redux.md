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
import { createSlice } from "@reduxjs/toolkit";

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
    }
  },
});

export const { increment, decrement, reset, incrementByAmount } = counterSlice.actions;

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