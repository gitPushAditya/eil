# React Native Intermediate

## State

There are two type of data that control the components -

- State: mutable
- props: immutable

### useState

```jsx
import React, {useState} from 'react';

 // In the beginning of function
const [name, setName] = useState("Adi")

 // To use it -
<Text> My Name is {name}</Text>

 // To change it -

onClick= {() => setName(newName)}
```

This can be used for any data type, arrays or even objects.

All the changes will only be saved for the life-cycle of page, as soon as the page is reloaded, the value will be set to default.

---

## Using StyleSheet

Create a StyleSheet -

```jsx
// Using Style -

<View style={styles.body}>
  <Text style={styles.text}>Hello World</Text>
</View>;

// Creating StyleSheet
const styles = StyleSheet.create({
  body: {
    flex: 1,
    alignItems: "center",
  },
  text: {
    color: "#ffffff",
    margin: 10,
  },
});
```

---

## Installing custom Fonts

```
expo install @expo-google-fonts/font-name

expo install expo-app-loading

```

Importing Fonts -

```jsx
import {
    DancingScript_400Regular,
    DancingScript_500Medium
} from '@expo-google-fonts/dancing-script';
import {useFonts} from 'expo-font';

 // Inside App Function

let [fontsLoaded, error] = useFonts({
    DancingScript_400Regular,
    DancingScript_500Medium
    // For custom fonts in file -

    'Indie-Flower' : require('./assets/fonts/IndieFlower-Regular.ttf');

});

if (!fontsLoaded) {
    return <AppLoading />
}

// Now you can use it in styles
```

---

## Data Persist

```jsx
npx expo install @react-native-async-storage/async-storage
```

To save data.

```jsx
import AsyncStorage from '@react-native-async-storage/async-storage';

const setData = async () => {
  if(name.length == 0){
    Alert.alert('Warning!, Name is empty')
  }else{
    try{
      await AsyncStorage.setItem('UserName' name);
    }catch(error){
      console.log(error);
    }
  }
}
```

To get data.

```jsx
const [name, setName] = useState('');

useEffect(() => {
  getData()
}, [])

const getData = () => {
  try {
    AsyncStorage.getItem('UserName')
    .then(value => {
      if(value != null ){
        setName(value);
      }
    })
  }catch(error){
    console.log(error);
  }
}
```

To replace, just use set function, it will replace old value with new one. 

To remove item(key with value) -

```js
await AsyncStorage.removeItem('UserName' name);
```

To remove everything

```js
await AsyncStorage.clear();
```

---  

## Promises 

To return a promise: 

```js
axiosRequest.get('link---').then(response => {
  // use result
})
.catch(error => {
  console.error()
})
```

To await until things are finished.

```js
async function getActivity(){
  try{
    let response = await axiosRequest.get('link---')
    console.log(response)
  }catch(error){
    console.error(error)
  }
}
```

---

## HOC - High order component

```tsx
const createStyledText = (fontSize: number, fontFamily: string) => {
  return ({
    children,
    style,
  }: {
    children: ReactNode;
    style?: StyleProp<ViewStyle>;
  }) => {
    const theme = useAppTheme();

    return (
      <Text
        style={[
          {
            fontSize: getFontSize(fontSize),
            fontFamily,
            color: theme.foregroundPrimary,
          },
          style,
        ]}
      >
        {children}
      </Text>
    );
  };
};
```

## Eject Expo App

To turn expo app into native react-native app, you can eject it using -

```
npm eject
```

Then answer a few questions and it will be converted.


