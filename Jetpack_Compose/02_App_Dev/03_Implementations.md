# Implementations

## Project Level Gradle

```
plugins {
        // By Default
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.kotlin.android) apply false
	alias(libs.plugins.kotlin.compose) apply false

        // Ksp - Kotlin symbol processing - kotlin compiler
	id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply false

        // dagger-hilt - dependency injections
	id("com.google.dagger.hilt.android") version "2.51.1" apply false

        // serializer
	id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0" apply false
}
```

---

## App Level Gradle

```kotlin
plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)

	id("com.google.devtools.ksp") version "2.0.20-1.0.24"
	id("com.google.dagger.hilt.android") version "2.51.1"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}

//...

compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}

//...

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)

	//compose navigation
	implementation("androidx.navigation:navigation-compose:2.8.4")
	//hilt
	implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
	implementation("com.google.dagger:hilt-android:2.51.1")
	ksp("com.google.dagger:hilt-android-compiler:2.51.1")

	// datastore
	implementation("androidx.datastore:datastore-preferences:1.1.1")

	// room database
	implementation("androidx.room:room-runtime:2.6.1")
	ksp("androidx.room:room-compiler:2.6.1")
	implementation("androidx.room:room-ktx:2.6.1")

	// icons - extended
	implementation("androidx.compose.material:material-icons-extended:1.7.5")

	//json
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

	// accompanist-permissions
	implementation("com.google.accompanist:accompanist-permissions:0.31.2-alpha")

	// splash screen
	implementation("androidx.core:core-splashscreen:1.0.1")

	// google ads
	implementation("com.google.android.gms:play-services-ads:23.5.0")


	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
```
---

## Final Implementations File

### Project Gradle -

```kotlin
plugins {
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.kotlin.android) apply false
	alias(libs.plugins.kotlin.compose) apply false
	alias(libs.plugins.ksp) apply false
	alias(libs.plugins.hilt) apply false
	alias(libs.plugins.kotlin.serializer) apply false
}
```

---

### App Gradle -

```kotlin
plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
	alias(libs.plugins.kotlin.serializer)
}

android {
	namespace = "com.visionforgestudio.drinkup"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.visionforgestudio.drinkup"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		compose = true
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)

	//compose navigation
	implementation(libs.androidx.navigation.compose)
	//hilt
	implementation(libs.androidx.hilt.navigation.compose)
	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	// datastore
	implementation(libs.androidx.datastore.preferences)

	// room database
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)

	// icons - extended
	implementation(libs.androidx.material.icons.extended)

	//json
	implementation(libs.kotlinx.serialization.json)

	// accompanist-permissions
	implementation(libs.accompanist.permissions)

	// splash screen
	implementation(libs.androidx.core.splashscreen)

	// google ads
	implementation(libs.play.services.ads)


	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
```

---

### Versions Toml -

```toml
[versions]
accompanistPermissions = "0.31.2-alpha"
agp = "8.7.1"
coreSplashscreen = "1.0.1"
datastorePreferences = "1.1.1"
hiltAndroid = "2.51.1"
hiltNavigationCompose = "1.2.0"
kotlin = "2.0.20"
coreKtx = "1.15.0"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
kotlinxSerializationJson = "1.6.3"
lifecycleRuntimeKtx = "2.8.7"
activityCompose = "1.9.3"
composeBom = "2024.04.01"
materialIconsExtended = "1.7.5"
navigationCompose = "2.8.4"
playServicesAds = "23.5.0"
roomRuntime = "2.6.1"

#plugins

ksp = "2.0.20-1.0.24"
hilt = "2.51.1"
serializer = "1.8.0"


[libraries]
accompanist-permissions = { module = "com.google.accompanist:accompanist-permissions", version.ref = "accompanistPermissions" }
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-core-splashscreen = { module = "androidx.core:core-splashscreen", version.ref = "coreSplashscreen" }
androidx-datastore-preferences = { module = "androidx.datastore:datastore-preferences", version.ref = "datastorePreferences" }
androidx-hilt-navigation-compose = { module = "androidx.hilt:hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
androidx-material-icons-extended = { module = "androidx.compose.material:material-icons-extended", version.ref = "materialIconsExtended" }
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "roomRuntime" }
androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "roomRuntime" }
hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "hiltAndroid" }
hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hiltAndroid" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }
play-services-ads = { module = "com.google.android.gms:play-services-ads", version.ref = "playServicesAds" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }

# ksp
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
# hilt
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
# serializer
kotlin-serializer = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "serializer" }
```

---