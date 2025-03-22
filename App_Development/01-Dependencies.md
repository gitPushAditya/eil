# Dependencies 

## libs.toml file for versions

### Libraries Versions

```toml
# accompanist
accompanistSystemuicontroller = "0.29.2-rc"
accompanistPermissions = "0.31.2-alpha"

# splash screen
coreSplashscreen = "1.0.1"

# datastore
datastorePreferences = "1.1.3"

# hilt
hiltAndroid = "2.51.1"
hiltNavigationCompose = "1.2.0"

# material
materialIconsExtended = "1.7.8"

# navigation
navigationCompose = "2.8.8"

# ads
playServicesAds = "24.0.0"

# room
roomRuntime = "2.6.1"

# serialization
kotlinxSerializationJson = "1.6.3"

# startup
startupRuntime = "1.2.0"

# firebase
firebaseBom = "33.10.0"

# billing
billingClient = "7.1.1"

# glance
glance = "1.1.1"

# exoplayer
exoplayer = "1.5.1"
```
### Plugins Versions

```toml
# plugins
ksp = "2.0.0-1.0.24"
hilt = "2.51.1"
serializer = "1.8.0"
gmsServices = "4.4.2"
firebaseCrashlytics = "3.0.3"
```

### Links

```toml
# accompanist
accompanist-systemuicontroller = { group = "com.google.accompanist", name = "accompanist-systemuicontroller", version.ref = "accompanistSystemuicontroller" }
accompanist-permissions = { group = "com.google.accompanist", name = "accompanist-permissions", version.ref = "accompanistPermissions" }

# splash screen
androidx-core-splashscreen = { group = "androidx.core", name = "core-splashscreen", version.ref = "coreSplashscreen" }

# datastore
androidx-datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastorePreferences" }

# hilt
androidx-hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
com-google-dagger-hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hiltAndroid" }
com-google-dagger-hilt-android-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hiltAndroid" }

# material
androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended", version.ref = "materialIconsExtended" }

# navigation
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }

# ads
play-services-ads = { group = "com.google.android.gms", name = "play-services-ads", version.ref = "playServicesAds" }

# room
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomRuntime" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "roomRuntime" }

# serialization
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }

# startup
androidx-startup-runtime = { group = "androidx.startup", name = "startup-runtime", version.ref = "startupRuntime" }

# firebase
firebase-analytics = { group = "com.google.firebase", name = "firebase-analytics" }
firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
firebase-crashlytics = { group = "com.google.firebase", name = "firebase-crashlytics" }

# billing
billingClient = { group = "com.android.billingclient", name = "billing", version.ref = "billingClient" }

# glance
androidx-glance = { group = "androidx.glance", name = "glance", version.ref = "glance" }

#exoplayer
androidx-media3-ui = { group = "androidx.media3", name = "media3-ui", version.ref = "exoplayer" }
androidx-media3-exoplayer-dash = { group = "androidx.media3", name = "media3-exoplayer-dash", version.ref = "exoplayer" }
androidx-media3-exoplayer-hls = { group = "androidx.media3", name = "media3-exoplayer-hls", version.ref = "exoplayer" }
androidx-media3-exoplayer = { group = "androidx.media3", name = "media3-exoplayer", version.ref = "exoplayer" }

```

```toml
# plugins

ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
serializer = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "serializer" }
google-gms-services = { id = "com.google.gms.google-services", version.ref = "gmsServices" }
firebase-crashlytics = { id = "com.google.firebase.crashlytics", version.ref = "firebaseCrashlytics" }
```

--- 

## Gradle - Project

```groovy
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.serializer) apply false
//    alias(libs.plugins.google.gms.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
```

---

## Gradle - App Module

### Plugins
```groovy
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // plugins

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serializer)
//    alias(libs.plugins.google.gms.services)
    alias(libs.plugins.firebase.crashlytics)
}
```

### Include this in android block

```groovy
packaging{
        resources {
            excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
```

### Include this in dependencies block

```groovy
configurations.all{
        resolutionStrategy{
            force("org.jetbrains:annotations:23.0.0")
            exclude(group = "com.intellij", module = "annotations")
        }
    }
```

### Dependencies

```groovy
implementation(libs.accompanist.systemuicontroller)
implementation(libs.accompanist.permissions)
implementation(libs.androidx.core.splashscreen)
implementation(libs.androidx.datastore.preferences)
implementation(libs.androidx.hilt.navigation.compose)
implementation(libs.com.google.dagger.hilt.android)
implementation(libs.com.google.dagger.hilt.android.compiler)
ksp(libs.com.google.dagger.hilt.android.compiler)
implementation(libs.androidx.material.icons.extended)
implementation(libs.androidx.navigation.compose)
implementation(libs.play.services.ads)
implementation(libs.androidx.room.compiler)
implementation(libs.androidx.room.ktx)
implementation(libs.androidx.room.runtime)
ksp(libs.androidx.room.compiler)
implementation(libs.kotlinx.serialization.json)
implementation(libs.androidx.startup.runtime)
implementation(libs.firebase.analytics)
implementation(libs.firebase.crashlytics)
implementation(platform(libs.firebase.bom))
//    implementation(libs.billingClient)
implementation(libs.androidx.glance)
implementation(libs.androidx.media3.exoplayer)
implementation(libs.androidx.media3.ui)
implementation(libs.androidx.media3.exoplayer.dash)
implementation(libs.androidx.media3.exoplayer.hls)
```
---

### Google Ads Trial Id 

```xml

<meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-3940256099942544~3347511713"/>
```

---