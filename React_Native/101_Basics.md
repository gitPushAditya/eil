# React Native Basics

## Create a New App

```
npx create-expo-app@latest AppName -t
```

Choose the desired template, usually blank

---

## Install Dependencies

```
npx expo install expo-router react-native-safe-area-context react-native-screens expo-linking expo-constants expo-status-bar expo-font axios react-native-dotenv
```

---

## Run the App

```
npm start
```

---

## Core Components

```js
import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
```

```
- <View>
- <Text>
- <Image>
- <ScrollView>
- <TextInput>
```

---

## App.js

This is the entry point of the app which gets rendered when the app starts.

--- 

## `<View>` component

- It is similar to div in web development, and can be nested under other components and cn have one or more children.
- By default, it only takes space filled by it's children but you can set `flex: 1` to make it take whole space.

```js
<View style={{ flex: 1 }}>-------//other components</View>
```
 
---

## `<Text>` component

- Every text must be displayed withing Text view.
- Text views can be nested.

```js
<Text>Text Here</Text>
```

---
  
## `<Image>` component

Enables to display various images -

- Static Images
- Network Images
- Images from local disc

```js
const logoImg = require("./assets/adaptive-icon.png");
<Image source={logoImg} style={{width: 300, height: 300}}/>
<Image source={{uri: 'https://picsum.photos/300}'}} style={{width: 300, height: 300}}/>
```
---
  
## `<ImageBackground>` component

A special component to display image in the background.

```js
<ImageBackground source={{ flex: 1 }}>---content</ImageBackground>
```

---
  
## `<ScrollView>`

Normal View component is not scrollable. For that we have <ScrollView>

_Note:- ScrollView requires a bounded height._

```js
<View style={{ flex: 1 }}>
  <ScrollView>// content here</ScrollView>
</View>
```

---
  
## `<Button>` component

Used to trigger actions

_Note: Button component has platform specific rendering for iOS and Android._

```js
<Button title= "Press" onPress = {() => console.log("Button Pressed")} color = "midnightblue" disabled = true/>
```

---
  
## `<Pressable>` component

Use to handle press event.

_It can have nested components_

```js
<Pressable onPress={() => console.log("Button Pressed")}></Pressable>
```

It has other event handlers too like -

- onPressIn
- onLongPress
- onPressOut

--- 

## `<Modal>` component

It's like a dialog box that overlays over other components.

We create a modal component with other nested component which will be visible by default but we can use useState to manipulate the visible parameter of it.

```js
import { useState } from "react";

const [isModalVisible, setModalVisible] = useState(false);

<Modal visible={isModalVisible}>
  <View>-- Modal Content ---</View>
</Modal>;
```

In a button, pass a value to set it to true - `setModalVisible(true)`

On back button press - `onRequestClose = {() => setModalVisible(false)}`

- Modal has a property - `animationType` which is "none" by default but can be set to "slide" or "fade".
- Property `presentationStyle` is "fullscreen" by default can be changed to "formSheet" or "pageSheet" _Note:- This only affects iOS_

---
  
## StatusBar component

By default, status bar background will be transparent but this component can make is used to modify it.

```js
<StatusBar backgroundColor = "lightgreen" barStyle = "default" hidden>
```

_StatusBar.currentHeight will return height of status bar in android_

_Note: backgroundColor is only used for android._

_default barStyle is black for ios and white for android, but can be changed into "dark-content" or "light-content"_

_hidden can toggled using useState_

---

## ActivityIndicator component

Used to display circular loading indicator.

```js
<ActivityIndicator size="large" color="blue" animating={false} />
```

- size can be small or large
- state variable should be used for animating value.

--- 
  
## Alert

Launches an alert dialog with title and message and also can have buttons.

```js
<Button onPress{() => Alert.alert("Title here!", "Message here..." [
    {
        text: 'Cancel'
        onPress: () =>
    },
    {
        text: 'Cancel'
        onPress: () =>
    }
])}/>
```

_On iOS, you can have as many buttons you want but on android, limit is 3_

---

## Custom Component

```js
export default function Greet({ name }) {
  return (
    <View>
      <Text>Hello, {name}</Text>
    </View>
  );
}
```

---
  
## StyleSheet

Two approaches -

- Inline using style prop
- Using StyleSheet

```js
<View style={styles.container}></View>;

const styles = StyleSheet.create({
  container: { flex: 1, padding: 60 },
});
```

For multiple styles -

`style = {[styles.box, styles.boxTwo]}`

_Note: When merging different styles, last style is dominant in case of override._

Few styles attribute:-

- width
- height
- padding
- paddingHorizontal
- paddingVertical
- margin
- marginHorizontal
- marginVertical
- borderWidth
- borderColor
- shadowColor
- shadowOffset: {width: , height: }
- shadowOpacity
- shadowRadius
- androidShadow: {elevation: }

_Note: Styles are not inherited from parent component but there are some exceptions_

---
  
## Flex Layout

View is default flexbox in react native. The default main axis is top to bottom, and cross axis is left to right.
Some attributes for flexbox -

- flex: 1
- flexDirection: "column" or column-reverse row and row-reverse
- justifyContent: "flex-start" or flex-end or center or space-between or space-around or space-evenly
- alignItems: "flex-start" and all for justify
- flexWrap: "no-wrap" or wrap or wrap reversed

For items

- alignSelf: same as align items just for flex items
- flexBasis: initial height or width of the item
- flexShrink: The items shrink to contain inside container, default is 0 which means no shrink.
- flexGrow: The items will grow if there are extra space, default is 0 means none.
- top: value for relative layout, could be bottom, right and left.
- position: "absolute" default is relative

For multiple items

- alignContent: similar values, must have more that 1 row ar column

_Note: Justify content works on main axis while alignItems and alignSelf works on cross axis_

For adding gaps-

- gap
- rowGap
- columnGap

--- 
  
## Dynamic UI

Using Dimension API -

```js
import { Dimensions } from "react-native";

const windowWidth = Dimensions.get("window").width;
const windowHeight = Dimensions.get("window").height;
```

_Note: "screen" can be used in place of window, screen represents entire screen while window represents the part of screen taken by app_

_This API has one drawback, it doesn't dynamically updates when window's size changes._

The better solution -

```js
import { useWindowDimensions } from "react-native";

const windowWidth = useWindowDimension().width;
const windowHeight = useWindowDimension().height;
```

---  

## SafeArea

```js
import { SafeArea } from "react-native";

<SafeAreaView style={styles.safeContainer}></SafeAreaView>;

const style = StyleSheet.create({
  safeContainer: {
    flex: 1,
    backgroundColor: "plum",
  },
});
```

---
  

## Platform Specific Code

Approach 1:

```js
import { Platform } from "react-native";

Platform.OS === "android"; // ios
```

Approach 2:

```js
const style = StyleSheet.create(
    text:{
        ...Platform.select({
            ios: {
                color: "purple",

            },
            android: {
                color: "blue"
            }
        })
    }
)
```

Approach 3:

Create custom components with specific extensions -

Component.android.js
Component.ios.js

_Approach 1 is suitable for single change while approach 2 is suitable for small changes and approach 3 is for vast changes in styles._

---

## List

```js
{
  myList.map((item) => {
    return <Text>{item.name}</Text>;
  });
}
```

_Rendering list has a flaw, it will render all the items even if they are not in the view. This is precess consuming._

---

## FlatList

This only renders the items in the view.

```js
<FlatList data={myList} renderItem={({item}) = > {
    return(<Text>{item.name}</Text>);
}}
/>
```

Props:

- horizontal = For rendering horizontally, you can add horizontal prop.
- keyExtractor = {item.key} item.key is default value, if not present then item.id, {(item, index) => index.toString()}
- ItemSeparatorComponent = {<View style={{ height: 16}}/ >}
- ListEmptyComponent={<Text>No items found</Text>}
- ListHeaderComponent={<Text style= {style.headerText}>My List </Text>}
- ListFooterComponent={<Text style= {style.footerText}>The End</Text>}
- refreshing = bool variable
- onRefresh = {handleRefresh function}

---

## SectionList

```js
<SectionList>
sections= {groupedList}
renderItem={({item})=> {
    return (
        <View style= {styles.card}>
        <Text style={styles.cardText}>{item}</Text>
        </View>
    );

}}
renderSectionHeader={({section}) => (
    <Text>{section.type}</Text>
);}
</SectionList>
```

Data for SectionList should strictly follow this format -

```json
[
  {
    "type": "Type 1",
    "data": ["item 1- 1", "item 1- 2"]
  },
  {
    "type": "Type 2",
    "data": ["item 2- 1", "item 2- 2"]
  }
]
```

This part represents a section:

{
"type": "Type 1",
"data": ["item 1- 1", "item 1- 2"]
}

_"data" key is important._

Prop:

ItemSeparatorComponent = {() => component}
SectionSeparatorComponent = {() => component}

---

## Form

### TextInput

```js
const [name, setName] = useState("")

<TextInput value={name} onChangedText={setName} placeholder="hint text"/>

```

Props:

- placeholder="hint text"
- secureTextEntry
- keyboardType = "numeric" or many options
- autoCorrect = {false} by default is true
- autoCapitalize = "none" by default is sentence
- multiline

### Switch Component

```js
const [isDarkMode, isLightMode] = useState(false)

<Switch value={isDarkMode} onValueChange={() => setIsDarkMode((previousState) => !previousState)}
trackColor = {{false: "hex code", true: "hex code"}}
thumbColor = "hex code"/ >
```

### KeyboardAvoidingView

```js
<KeyboardAvoidingView behavior = "padding" keyboardVerticalOffset = {Platform.OS === "ios" ? 100: 0}>
```

---

## Networking

### Get Data:

```js
const fetchData = async () => {
  const response = await fetch("url");
  const data = await response.json();
};

useEffect(() => {
  fetchData();
}, []);
```  

---

## React Navigation

```
npm install @react-navigation/native
```

```
npm expo install react-native-screens react-native-safe-area-context
```

Wrap entire app under -

```js
<NavigationContainer>{app}</NavigationContainer>
```

Provides Stack, Drawer, and Tab navigators

### Stack Navigator

Will place one screen above another like stack.

Two Navigators: Stack Navigator and Native Stack Navigator

Stack Navigator is a js library with many custom functions but requires high performance.

Native Stack Navigator leverages native navigation construct, might not have many customization but feels more native and low performance consuming.

```
npm install @react-navigation/native-stack
```

```js
import { createNativeStackNavigator } from "@react-navigation/native-stack";

const Stack = createNativeStackNavigator();

// Inside NavigationContainer -

<Stack.Navigator>
  <Stack.Screen name="Home" component={HomeScreen} />
  <Stack.Screen name="About" component={AboutScreen} />
</Stack.Navigator>;
```

_By default, name of the screen is name parameter, but can be changed using options parameter._

```js
<Stack.Screen name="Home" component={HomeScreen} options={{title: "Welcome Home", headerStyle: {
    backgroundColor: "hex code",
},
headerTintColor: "hex code",
headerTitleStyle: {
    fontWeight: "bold"
},
headerRight: () => (
    //component
),

}}/>
```

_These styling are more screen specific and for more consistent styling, use screenOptions parameter and put all of this into it._

To use it -

```js
import {useNavigation} from "@react-navigation/native"

// screen function

const navigation = useNavigation();

<Button onPress={() => navigation.navigate("About")}>
```

_You can also use navigation prop in function instead of useNavigation, but it can only be used in screen function._

Passing Data:

```js
<Button onPress={() => navigation.navigate("About", {
    name: "Aditya"
})}>
```

To receive it:

Every screen function has a prop called route

```js
const { name } = route.params;
```  

### Drawer Navigator

Will place a drawer in the side.

```
npm install @react-navigator/drawer
```

```
npm install react-native-gesture-handler react-native-reanimated
```

In babel.config.js

```js
plugins: [
    'react-native-reanimated/plugin`,
]
```

In package.json 

"start": "expo start -c",

```js
import "react-native-gesture-handler"; // at the top
import { NavigationContainer } from "@react-navigation/native";
import { createDrawerNavigator } from "@react-navigator/drawer";

const Drawer = createDrawerNavigator();

// Inside NavigationContainer

<Drawer.Navigator>
    <Drawer.Screen name="Home" component={HomeScreen}/>
    <Drawer.Screen name="Settings" component={SettingsScreen}/>
</Drawer.Navigator>
```

To toggle it using program - 

`navigation.toggleDrawer()`

To navigate without using drawer - 

`navigation.jumpTo("Settings")`

To add other options - 

```
options= {{
    title: "My Home",
    drawerLabel: "Home"
    drawerActiveTintColor: "",
    drawerActiveBackgroundColor: "",
    drawerContentStyle: {
        backgroundColor: "",
    }
}}
```

### Tab Navigator

Will place tab in the bottom of the screen.

```
npm install @react-navigation/bottom-tabs
```

```js
import {NavigationContainer} from '@react-navigation/native`;
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import Ionicons from "@expo/vector-icons/Ionicons"

// inside NavigationContainer

<Tab.Navigator>
<Tab.Screen name="Home" component={HomeScreen}/ >
<Tab.Screen name="Settings" component={SettingsScreen}/ >
<Tab.Screen name="Profile" component={ProfileScreen}/ >
</Tab.Navigator>
```

Parameters - 

```js
screenOptions={{
    topBarLabelPosition: "below-icon",
    topBarShowLabel: false,
    tabBarActiveTintColor: "color",
    tabBarInactiveTintColor: "color"
}}

// for tab screens 

options = {{
    tabBarLabel: "My Profile"
    tabBarIcon: ({color}) => (<Ionicons name = "person" size = {20} color = {color}/>),
    tabBarBadge: integer
}}
```

*To hide header in any navigation, set headerShown to false*

---

