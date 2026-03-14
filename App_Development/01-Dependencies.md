# Dependencies 

## Versions 

```toml
[versions]
agp = "8.11.2"
android-compileSdk = "36"
android-minSdk = "24"
android-targetSdk = "36"
androidx-activity = "1.11.0"
androidx-appcompat = "1.7.1"
androidx-core = "1.17.0"
androidx-espresso = "3.7.0"
androidx-lifecycle = "2.9.5"
androidx-testExt = "1.3.0"
composeMultiplatform = "1.9.1"
junit = "4.13.2"
kotlin = "2.2.20"
kotlinx-coroutines = "1.10.2"
composeHotReload = "1.0.0-rc02"

# Multiplatform dependencies

datastorePreferences = "1.1.7"
materialIconsExtended = "1.7.8"
voyager = "1.1.0-beta03"
roomRuntime = "2.8.2"
kotlinxSerializationJson = "1.9.0"
gson = "2.13.2"
koin = "4.1.1"

# Android dependencies
accompanistSystemuicontroller = "0.36.0"
accompanistPermissions = "0.37.3"
coreSplashscreen = "1.0.1"
startupRuntime = "1.2.0"
firebaseBom = "34.4.0"
billingClient = "8.0.0"

# Multiplatform plugins

ksp = "2.2.10-2.0.2"
serializer = "2.2.20"

# Android plugins

gmsServices = "4.4.2"
firebaseCrashlytics = "3.0.3"





[libraries]
kotlin-test = { module = "org.jetbrains.kotlin:kotlin-test", version.ref = "kotlin" }
kotlin-testJunit = { module = "org.jetbrains.kotlin:kotlin-test-junit", version.ref = "kotlin" }
junit = { module = "junit:junit", version.ref = "junit" }
androidx-core-ktx = { module = "androidx.core:core-ktx", version.ref = "androidx-core" }
androidx-testExt-junit = { module = "androidx.test.ext:junit", version.ref = "androidx-testExt" }
androidx-espresso-core = { module = "androidx.test.espresso:espresso-core", version.ref = "androidx-espresso" }
androidx-appcompat = { module = "androidx.appcompat:appcompat", version.ref = "androidx-appcompat" }
androidx-activity-compose = { module = "androidx.activity:activity-compose", version.ref = "androidx-activity" }
androidx-lifecycle-viewmodelCompose = { module = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose", version.ref = "androidx-lifecycle" }
androidx-lifecycle-runtimeCompose = { module = "org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose", version.ref = "androidx-lifecycle" }
kotlinx-coroutinesSwing = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-swing", version.ref = "kotlinx-coroutines" }

# libraries
accompanist-systemuicontroller = { group = "com.google.accompanist", name = "accompanist-systemuicontroller", version.ref = "accompanistSystemuicontroller" }
accompanist-permissions = { group = "com.google.accompanist", name = "accompanist-permissions", version.ref = "accompanistPermissions" }
androidx-core-splashscreen = { group = "androidx.core", name = "core-splashscreen", version.ref = "coreSplashscreen" }
androidx-datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastorePreferences" }
androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended", version.ref = "materialIconsExtended" }
voyager-navigator = { group = "cafe.adriel.voyager", name = "voyager-navigator", version.ref = "voyager" }
voyager-screenmodel = { group = "cafe.adriel.voyager", name = "voyager-screenmodel", version.ref = "voyager" }
voyager-transitions = { group = "cafe.adriel.voyager", name = "voyager-transitions", version.ref = "voyager" }
voyager-koin = { group = "cafe.adriel.voyager", name = "voyager-koin", version.ref = "voyager" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "roomRuntime" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomRuntime" }
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }
androidx-startup-runtime = { group = "androidx.startup", name = "startup-runtime", version.ref = "startupRuntime" }
com-google-firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
com-google-firebase-analytics = { group = "com.google.firebase", name = "firebase-analytics" }
com-google-firebase-crashlytics = { group = "com.google.firebase", name = "firebase-crashlytics", version.ref = "firebaseCrashlytics" }
billingClient = { group = "com.android.billingclient", name = "billing", version.ref = "billingClient" }
com-google-gson = { group = "com.google.code.gson", name = "gson", version.ref = "gson" }
koin-core = { module = "io.insert-koin:koin-core", version.ref = "koin" }
koin-android = { module = "io.insert-koin:koin-android", version.ref = "koin" }
koin-compose = { module = "io.insert-koin:koin-compose", version.ref = "koin" }
koin-compose-mp = { module = "io.insert-koin:koin-compose", version.ref = "koin" }
koin-test = { module = "io.insert-koin:koin-test", version.ref = "koin" }
kotlinx-serialization = { group = "org.jetbrains.kotlin", name= "kotlin-serialization", version.ref = "serializer" }
gms-services = { group = "com.google.gms", name= "google-services", version.ref = "gmsServices" }
firebase-crashlytics = { group = "com.google.firebase", name= "crashlytics", version.ref = "firebaseCrashlytics" }
compose-multiplatform-core = { module = "org.jetbrains.compose:compose-multiplatform-core", version.ref = "composeMultiplatform" }

[plugins]
androidApplication = { id = "com.android.application", version.ref = "agp" }
androidLibrary = { id = "com.android.library", version.ref = "agp" }
composeHotReload = { id = "org.jetbrains.compose.hot-reload", version.ref = "composeHotReload" }
composeMultiplatform = { id = "org.jetbrains.compose", version.ref = "composeMultiplatform" }
composeCompiler = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
kotlinMultiplatform = { id = "org.jetbrains.kotlin.multiplatform", version.ref = "kotlin" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
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

    // For Kotlin Multiplatform

    sourceSets {
            androidMain.dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android) // <-- ADD FOR ANDROID
            }
            commonMain.dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                // Koin for common code and Compose Multiplatform
                implementation(libs.koin.core) // <-- ADD FOR CORE DI
                implementation(libs.koin.compose.mp) // <-- ADD FOR COMPOSE MULTIPLATFORM
            }
            commonTest.dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.koin.test) // <-- ADD FOR TESTING
            }
            jvmMain.dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
                // No specific Koin dependency needed here, koin-core is sufficient
            }
            // For iosMain, koin-core from commonMain is usually sufficient
        }

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