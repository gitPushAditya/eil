
# Change Gradle Files

## In android/app/build.gradle.kts make these changes - 

- Note: These versions may update in future.

NDK new version is required by notification package
```
ndkVersion = "27.0.12077973"
```

---

New version of Java for advance features
```
compileOptions {  
    sourceCompatibility = JavaVersion.VERSION_17  
    targetCompatibility = JavaVersion.VERSION_17  
}
```

```
kotlinOptions {  
    jvmTarget = JavaVersion.VERSION_17.toString()  
}
```

---

Minimum SDK required is 23(for now)m can't be below that
```
minSdk = 23  
targetSdk = 35
```

---

Need to add this dependency for firebase
```
dependencies {  
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")  
}
```

---

Final build file looks like this - 

```
plugins {  
    id("com.android.application")  
    // START: FlutterFire Configuration  
    id("com.google.gms.google-services")  
    id("com.google.firebase.firebase-perf")  
    id("com.google.firebase.crashlytics")  
    // END: FlutterFire Configuration  
    id("kotlin-android")  
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.  
    id("dev.flutter.flutter-gradle-plugin")  
}  
  
android {  
    namespace = "com.visionforgestudio.routine_flow"  
    compileSdk = flutter.compileSdkVersion  
    ndkVersion = "27.0.12077973"  
  
    compileOptions {  
        sourceCompatibility = JavaVersion.VERSION_17  
        targetCompatibility = JavaVersion.VERSION_17  
        isCoreLibraryDesugaringEnabled = true  
    }  
  
    kotlinOptions {  
        jvmTarget = JavaVersion.VERSION_17.toString()  
    }  
    defaultConfig {  
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).  
        applicationId = "com.visionforgestudio.routine_flow"  
        // You can update the following values to match your application needs.  
        // For more information, see: https://flutter.dev/to/review-gradle-config.        minSdk = 23  
        targetSdk = 35  
        versionCode = flutter.versionCode  
        versionName = flutter.versionName  
    }  
  
    buildTypes {  
        release {  
            // TODO: Add your own signing config for the release build.  
            // Signing with the debug keys for now, so `flutter run --release` works.            signingConfig = signingConfigs.getByName("debug")  
        }    }}  
  
flutter {  
    source = "../.."  
}  
  
dependencies {  
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")  
}
```

---
