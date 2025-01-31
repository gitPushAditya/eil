# Folder Structure and Hilt

## Initialize Hilt

We already have our entry file called  - MainActivity.kt 
For Hilt we need to create another file usually with the name of App - For this example we will call it DrinkUp.kt

### DrinkUpApp.kt

```kotlin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DrinkUpApp: Application() {
    override fun onCreate() {
        super.onCreate()

        //TODO: Create Notification Channels Here
    }
}
```

### MainActivity.kt

```kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.visionforgestudio.drinkup.ui.theme.DrinkUpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkUpTheme {
                // TODO: Add Your App Content Here(Usually NavController)
            }
        }
    }
}
```

---

## Folder Structure

In our package we usually define 7 folders, each specified for a specific purpose.

```kotlin
com.yourcompany.appname
│
├── core
├── domain
├── data
├── presentation
├── di
├── utils
└── navigation
```

### core 

```kotlin
com.yourcompany.appname.core
├── constants     // App-wide constants (e.g., API keys, default values)
├── defaults      // Default values or configurations (e.g., themes, default states)
├── ui
│   ├── theme     // Composable theming (e.g., colors, typography, shapes)
│   ├── components // Reusable UI components (e.g., buttons, cards, dialogs)
│   └── animation // Custom animations or transitions
├── utils         // Shared utility functions (e.g., date formatters, extensions)
└── network       // Common networking logic (e.g., API clients, interceptors)

```

### domain

```kotlin
com.yourcompany.appname.domain
├── model         // Data models/entities (e.g., User, Product)
├── enums         // Enumerations (e.g., Gender)
├── interface    // Repository interfaces
└── usecase       // Use cases (e.g., FetchUserData, CalculateDiscount)

```

### data

```data
com.yourcompany.appname.data
├── repository    // Repository implementations
├── datasource
│   ├── api       // Remote data source (Retrofit services, network calls)
│   ├── db        // Local database (Room DAOs, entities)
│   └── datastore // Preferences or key-value stores
└── mapper        // Mappers to transform data between layers

```

### presentation

```kotlin
com.yourcompany.appname.presentation
├── screens
│   ├── home
│   │   ├── HomeScreen.kt      // Home screen composable
│   │   ├── HomeViewModel.kt   // ViewModel for HomeScreen
│   │   └── components         // UI components specific to HomeScreen
│   ├── details
│   │   ├── DetailsScreen.kt
│   │   ├── DetailsViewModel.kt
│   │   └── components
│   └── shared                 // Shared components across multiple screens
├── navigation                 // Centralized navigation management
└── state                      // State management (e.g., ViewModels, UI states)

```

### di

```kotlin
com.yourcompany.appname.di
├── AppModule.kt               // Application-wide dependencies
├── NetworkModule.kt           // Networking dependencies
└── DatabaseModule.kt          // Database dependencies

```

### utils

```kotlin
com.yourcompany.appname.utils
├── extensions    // Kotlin extension functions
├── validators    // Input validation logic
└── formatters    // Formatting utilities (e.g., currency, dates)

```

### navigation

```kotlin
com.yourcompany.appname.navigation
├── AppNavGraph.kt              // Centralized navigation graph
├── Destinations.kt             // Navigation destinations
└── NavigationExtensions.kt     // Helper functions for navigation

```

---

## Manifest File

In manifest file, we have to add android app file name and also default trial id for google ads.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
<!--            Add this line-->
        android:name=".DrinkUpApp" 
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.DrinkUp"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.DrinkUp">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
<!--    Add this block -->
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-3940256099942544~3347511713" />
    </application>

</manifest>
```
