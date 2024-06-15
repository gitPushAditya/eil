# Create a new flutter app

In any folder of your choice, open command prompt and run this command

```
flutter create app_name
```

---

## Working in main.dart flie

_Note: You will find main.dart in lib folder inside your app_name folder._

Delete everything that's there by default, it's sample code for default counter app by flutter.

Then do the following to start -

- Import material package.
- Create main function.
- Create a stateless widget that returns MaterialApp(MaterialApp.router if working with multiple pages using routers).

_All of these points are explained in detail in upcoming topics._

---

## Importing material package

```
import 'package:flutter/material.dart';
```

---

## Creating main function

```dart
void main(){
  runApp(const MyApp());
}
```

_Note: This code will show error initially because we haven't created 'MyApp' class yet._

---

## Creating an empty Stateless Widget class

```dart
class MyApp extends StatelessWidget {
const MyApp({ super.key });

  @override
  Widget build(BuildContext context){
    return Container();
  }
}
```

*Note: This is just an empty Stateless Widget class, this won't show anything, for our main.dart file, we need to make some changes into it. But first, we need to install go_router package for routing through different pages.*

---

