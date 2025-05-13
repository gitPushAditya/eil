# Dependencies 

## Versions 

```toml
# libraries
accompanistSystemuicontroller = "0.29.2-rc"
accompanistPermissions = "0.31.2-alpha"
coreSplashscreen = "1.0.1"
datastorePreferences = "1.1.3"
hiltAndroid = "2.51.1"
hiltNavigationCompose = "1.2.0"
materialIconsExtended = "1.7.8"
navigationCompose = "2.8.9"
playServicesAds = "24.2.0"
roomRuntime = "2.6.1"
kotlinxSerializationJson = "1.6.3"
startupRuntime = "1.2.0"
firebaseBom = "33.11.0"
billingClient = "7.1.1"
glance = "1.1.1"
gson = "2.10.1"

# plugins
ksp = "2.0.0-1.0.24"
hilt = "2.51.1"
serializer = "1.8.0"
gmsServices = "4.4.2"
firebaseCrashlytics = "3.0.3"


accompanist-systemuicontroller = { group = "com.google.accompanist", name = "accompanist-systemuicontroller", version.ref = "accompanistSystemuicontroller" }
accompanist-permissions = { group = "com.google.accompanist", name = "accompanist-permissions", version.ref = "accompanistPermissions" }
androidx-core-splashscreen = { group = "androidx.core", name = "core-splashscreen", version.ref = "coreSplashscreen" }
androidx-datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastorePreferences" }
androidx-hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
com-google-dagger-hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hiltAndroid" }
com-google-dagger-hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hiltAndroid" }
androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended", version.ref = "materialIconsExtended" }
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
com-google-play-services-ads = { group = "com.google.android.gms", name = "play-services-ads", version.ref = "playServicesAds" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "roomRuntime" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomRuntime" }
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }
androidx-startup-runtime = { group = "androidx.startup", name = "startup-runtime", version.ref = "startupRuntime" }
com-google-firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
com-google-firebase-analytics = { group = "com.google.firebase", name = "firebase-analytics" }
com-google-firebase-crashlytics = { group = "com.google.firebase", name = "firebase-crashlytics", version.ref = "firebaseCrashlytics" }
billingClient = { group = "com.android.billingclient", name = "billing", version.ref = "billingClient" }
androidx-glance = { group = "androidx.glance", name = "glance", version.ref = "glance" }
androidx-glance-appwidget = { group = "androidx.glance", name = "glance-appwidget" }
com-google-gson = { group = "com.google.code.gson", name = "gson", version.ref = "gson" }

ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
hilt-android = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
kotlinx-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "serializer" }
gms-services = { id = "com.google.gms.google-services", version.ref = "gmsServices" }
firebase-crashlytics = { id = "com.google.firebase.crashlytics", version.ref = "firebaseCrashlytics" }
```

---

## App Level Gradle

```kts
// In Plugins

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.gms.services)
    alias(libs.plugins.firebase.crashlytics)

// In Android

packaging{
        resources {
            excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }

// In Dependencies

configurations.all{
        resolutionStrategy{
            force("org.jetbrains:annotations:23.0.0")
            exclude(group = "com.intellij", module = "annotations")
        }
    }

implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.com.google.dagger.hilt.android)
    implementation(libs.com.google.dagger.hilt.compiler)
    ksp(libs.com.google.dagger.hilt.compiler)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.com.google.play.services.ads)
    implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.com.google.firebase.analytics)
    implementation(libs.com.google.firebase.crashlytics)
    implementation(platform(libs.com.google.firebase.bom))
    implementation(libs.com.google.gson)
    implementation(libs.billingClient)
    implementation(libs.androidx.glance)

```

---

## Project Level Gradle

```kts
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.gms.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
```

---

## Manifest

In Application - 

```xml
<meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-3940256099942544~3347511713"/>

```