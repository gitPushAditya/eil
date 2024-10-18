# Folder, Themes, and Dependencies

## Create Project File - TaskFlow

---
## Create Folder Structure

- data
    - datastore
    - db
        - dao
        - database
        - entity
        - mapper
        - migration
    - mapper
    - network
        - client
        - error
        - model
        - service
    - repository
- di
    - components
    - modules
    - scopes
- domain
    - model
    - repository
    - usecases
- global
    - constants
    - utils
    - viewmodel
- notification
- ui
    - common
    - navigation
    - screens
    - theme
        - color
        - shape
        - themes
        - type
- workers

---

## Add Dependencies 

- Ksp
- Json Serializer
- Data Store(Preferences)
- Room Database
- Dagger Hilt
- Extended Icons


### Versions File

```Toml
[versions]
agp = "8.7.1"
kotlin = "2.0.20"
coreKtx = "1.13.1"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
lifecycleRuntimeKtx = "2.8.6"
activityCompose = "1.9.3"
composeBom = "2024.10.00"
#datastore
datastorePreferences = "1.1.1"
#ksp
ksp = "2.0.20-1.0.24"
#room
room = "2.6.1"
#hilt
hilt = "2.51.1"
hiltNaviagation = "1.2.0"
json = "1.5.0"


[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
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
# material icons extended
androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended" }
# datastore
androidx-datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastorePreferences" }
# room
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-compiler = {group = "androidx.room", name= "room-compiler", version.ref = "room"}
# hilt
hilt-navigation = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNaviagation" }
google-hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
google-hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }
#json
json = {group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "json"}


[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
# ksp
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
# hilt
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
# serializer
kotlin-serializer = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "json" }
```

### Project Level Gradle

```Gradle
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.kotlin.android) apply false
	alias(libs.plugins.kotlin.compose) apply false
	alias(libs.plugins.ksp) apply false
	alias(libs.plugins.hilt) apply false

}
```

### Gradle App Level

```Gradle


plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
	alias(libs.plugins.kotlin.serializer)
}

android {
	namespace = "com.visionforgestudio.taskflow"
	compileSdk = 34
	
	defaultConfig {
		applicationId = "com.visionforgestudio.taskflow"
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
	// datastore
	implementation(libs.androidx.datastore.preferences)
	// hilt
	implementation(libs.google.hilt.android)
	implementation(libs.hilt.navigation)
	ksp(libs.google.hilt.compiler)
	// room
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	// icons -extended
	implementation(libs.androidx.material.icons.extended)
	// json
	implementation(libs.json)
	
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

## Setup Hilt

### root directory -> TaskFlowApp

```Kotlin
package com.visionforgestudio.taskflow

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskFlowApp: Application() {
}
```

### In AndroidManifest.xml

```XML
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:name=".TaskFlowApp" // Add this line ---------------------------
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.TaskFlow"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.TaskFlow">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

In MainActivity

```Kotlin
package com.visionforgestudio.taskflow

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
import com.visionforgestudio.taskflow.ui.theme.themes.TaskFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Add this ------------------
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			TaskFlowTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Greeting(
						name = "Android",
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	TaskFlowTheme {
		Greeting("Android")
	}
}
```

---

## Add App Themes

ui -> theme -> themes -> AppThemes

```Kotlin
package com.visionforgestudio.taskflow.ui.theme.themes

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

sealed class AppThemes(val colors: ColorScheme) {
	object DefaultLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF256489),
			surfaceTint = Color(0xFF256489),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFC8E6FF),
			onPrimaryContainer = Color(0xFF001E2F),
			secondary = Color(0xFF4F606E),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFD3E5F5),
			onSecondaryContainer = Color(0xFF0B1D29),
			tertiary = Color(0xFF64597C),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFEADDFF),
			onTertiaryContainer = Color(0xFF1F1635),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFF6F9FE),
			onBackground = Color(0xFF181C20),
			surface = Color(0xFFF6F9FE),
			onSurface = Color(0xFF181C20),
			surfaceVariant = Color(0xFFDDE3EA),
			onSurfaceVariant = Color(0xFF41474D),
			outline = Color(0xFF71787E),
			outlineVariant = Color(0xFFC1C7CE),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF2D3135),
			inverseOnSurface = Color(0xFFEEF1F6),
			inversePrimary = Color(0xFF94CDF7),
			surfaceDim = Color(0xFFD7DADF),
			surfaceBright = Color(0xFFF6F9FE),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFF1F4F9),
			surfaceContainer = Color(0xFFEBEEF3),
			surfaceContainerHigh = Color(0xFFE5E8ED),
			surfaceContainerHighest = Color(0xFFDFE3E8),
		)
	)
	
	object DefaultDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFF94CDF7),
			surfaceTint = Color(0xFF94CDF7),
			onPrimary = Color(0xFF00344D),
			primaryContainer = Color(0xFF004C6E),
			onPrimaryContainer = Color(0xFFC8E6FF),
			secondary = Color(0xFFB7C9D9),
			onSecondary = Color(0xFF21323F),
			secondaryContainer = Color(0xFF384956),
			onSecondaryContainer = Color(0xFFD3E5F5),
			tertiary = Color(0xFFCEC0E8),
			onTertiary = Color(0xFF352B4B),
			tertiaryContainer = Color(0xFF4C4163),
			onTertiaryContainer = Color(0xFFEADDFF),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF101417),
			onBackground = Color(0xFFDFE3E8),
			surface = Color(0xFF101417),
			onSurface = Color(0xFFDFE3E8),
			surfaceVariant = Color(0xFF41474D),
			onSurfaceVariant = Color(0xFFC1C7CE),
			outline = Color(0xFF8B9198),
			outlineVariant = Color(0xFF41474D),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFDFE3E8),
			inverseOnSurface = Color(0xFF2D3135),
			inversePrimary = Color(0xFF256489),
			surfaceDim = Color(0xFF101417),
			surfaceBright = Color(0xFF353A3E),
			surfaceContainerLowest = Color(0xFF0A0F12),
			surfaceContainerLow = Color(0xFF181C20),
			surfaceContainer = Color(0xFF1C2024),
			surfaceContainerHigh = Color(0xFF262A2E),
			surfaceContainerHighest = Color(0xFF313539),
		)
	)
	
	object ForestLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF3B6939),
			surfaceTint = Color(0xFF3B6939),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFBCF0B4),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF52634F),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFD5E8CF),
			onSecondaryContainer = Color(0xFF111F0F),
			tertiary = Color(0xFF38656A),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFBCEBF0),
			onTertiaryContainer = Color(0xFF002023),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFF7FBF1),
			onBackground = Color(0xFF191D17),
			surface = Color(0xFFF7FBF1),
			onSurface = Color(0xFF191D17),
			surfaceVariant = Color(0xFFDEE5D8),
			onSurfaceVariant = Color(0xFF424940),
			outline = Color(0xFF72796F),
			outlineVariant = Color(0xFFC2C9BD),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF2D322C),
			inverseOnSurface = Color(0xFFEFF2E9),
			inversePrimary = Color(0xFFA1D39A),
			surfaceDim = Color(0xFFD8DBD2),
			surfaceBright = Color(0xFFF7FBF1),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFF1F5EC),
			surfaceContainer = Color(0xFFECEFE6),
			surfaceContainerHigh = Color(0xFFE6E9E0),
			surfaceContainerHighest = Color(0xFFE0E4DB),
		)
	)
	
	object ForestDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFA1D39A),
			surfaceTint = Color(0xFFA1D39A),
			onPrimary = Color(0xFF0A390F),
			primaryContainer = Color(0xFF235024),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFBACCB3),
			onSecondary = Color(0xFF253423),
			secondaryContainer = Color(0xFF3B4B38),
			onSecondaryContainer = Color(0xFFD5E8CF),
			tertiary = Color(0xFFA0CFD4),
			onTertiary = Color(0xFF00363B),
			tertiaryContainer = Color(0xFF1F4D52),
			onTertiaryContainer = Color(0xFFBCEBF0),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF10140F),
			onBackground = Color(0xFFE0E4DB),
			surface = Color(0xFF10140F),
			onSurface = Color(0xFFE0E4DB),
			surfaceVariant = Color(0xFF424940),
			onSurfaceVariant = Color(0xFFC2C9BD),
			outline = Color(0xFF8C9388),
			outlineVariant = Color(0xFF424940),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFE0E4DB),
			inverseOnSurface = Color(0xFF2D322C),
			inversePrimary = Color(0xFF3B6939),
			surfaceDim = Color(0xFF10140F),
			surfaceBright = Color(0xFF363A34),
			surfaceContainerLowest = Color(0xFF0B0F0A),
			surfaceContainerLow = Color(0xFF191D17),
			surfaceContainer = Color(0xFF1D211B),
			surfaceContainerHigh = Color(0xFF272B25),
			surfaceContainerHighest = Color(0xFF323630),
		)
	)
	
	object GoldenLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF775A0B),
			surfaceTint = Color(0xFF775A0B),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFFFDF9E),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF6B5D3F),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFF5E0BB),
			onSecondaryContainer = Color(0xFF241A04),
			tertiary = Color(0xFF4A6547),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFCCEBC4),
			onTertiaryContainer = Color(0xFF072109),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFFFF8F2),
			onBackground = Color(0xFF1F1B13),
			surface = Color(0xFFFFF8F2),
			onSurface = Color(0xFF1F1B13),
			surfaceVariant = Color(0xFFEDE1CF),
			onSurfaceVariant = Color(0xFF4D4639),
			outline = Color(0xFF7F7667),
			outlineVariant = Color(0xFFD0C5B4),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF353027),
			inverseOnSurface = Color(0xFFF9EFE2),
			inversePrimary = Color(0xFFE9C16C),
			surfaceDim = Color(0xFFE2D9CC),
			surfaceBright = Color(0xFFFFF8F2),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFFCF2E5),
			surfaceContainer = Color(0xFFF6ECDF),
			surfaceContainerHigh = Color(0xFFF1E7D9),
			surfaceContainerHighest = Color(0xFFEBE1D4),
		)
	)
	
	object GoldenDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFE9C16C),
			surfaceTint = Color(0xFFE9C16C),
			onPrimary = Color(0xFF3F2E00),
			primaryContainer = Color(0xFF5B4300),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFD8C4A0),
			onSecondary = Color(0xFF3A2F15),
			secondaryContainer = Color(0xFF52452A),
			onSecondaryContainer = Color(0xFFF5E0BB),
			tertiary = Color(0xFFB0CFAA),
			onTertiary = Color(0xFF1D361C),
			tertiaryContainer = Color(0xFF334D31),
			onTertiaryContainer = Color(0xFFCCEBC4),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF17130B),
			onBackground = Color(0xFFEBE1D4),
			surface = Color(0xFF17130B),
			onSurface = Color(0xFFEBE1D4),
			surfaceVariant = Color(0xFF4D4639),
			onSurfaceVariant = Color(0xFFD0C5B4),
			outline = Color(0xFF998F80),
			outlineVariant = Color(0xFF4D4639),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFEBE1D4),
			inverseOnSurface = Color(0xFF353027),
			inversePrimary = Color(0xFF775A0B),
			surfaceDim = Color(0xFF17130B),
			surfaceBright = Color(0xFF3E382F),
			surfaceContainerLowest = Color(0xFF110E07),
			surfaceContainerLow = Color(0xFF1F1B13),
			surfaceContainer = Color(0xFF231F17),
			surfaceContainerHigh = Color(0xFF2E2921),
			surfaceContainerHighest = Color(0xFF39342B),
		)
	)
	
	object MidnightLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF475D92),
			surfaceTint = Color(0xFF475D92),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFD9E2FF),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF575E71),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFDCE2F9),
			onSecondaryContainer = Color(0xFF141B2C),
			tertiary = Color(0xFF725572),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFFDD7FA),
			onTertiaryContainer = Color(0xFF2A132C),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFFAF8FF),
			onBackground = Color(0xFF1A1B20),
			surface = Color(0xFFFAF8FF),
			onSurface = Color(0xFF1A1B20),
			surfaceVariant = Color(0xFFE1E2EC),
			onSurfaceVariant = Color(0xFF44464F),
			outline = Color(0xFF757780),
			outlineVariant = Color(0xFFC5C6D0),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF2F3036),
			inverseOnSurface = Color(0xFFF1F0F7),
			inversePrimary = Color(0xFFB0C6FF),
			surfaceDim = Color(0xFFDAD9E0),
			surfaceBright = Color(0xFFFAF8FF),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFF4F3FA),
			surfaceContainer = Color(0xFFEEEDF4),
			surfaceContainerHigh = Color(0xFFE8E7EF),
			surfaceContainerHighest = Color(0xFFE2E2E9),
		)
	)
	
	object MidnightDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFB0C6FF),
			surfaceTint = Color(0xFFB0C6FF),
			onPrimary = Color(0xFF152E60),
			primaryContainer = Color(0xFF2E4578),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFC0C6DC),
			onSecondary = Color(0xFF293042),
			secondaryContainer = Color(0xFF404659),
			onSecondaryContainer = Color(0xFFDCE2F9),
			tertiary = Color(0xFFE0BBDD),
			onTertiary = Color(0xFF412742),
			tertiaryContainer = Color(0xFF593D5A),
			onTertiaryContainer = Color(0xFFFDD7FA),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF121318),
			onBackground = Color(0xFFE2E2E9),
			surface = Color(0xFF121318),
			onSurface = Color(0xFFE2E2E9),
			surfaceVariant = Color(0xFF44464F),
			onSurfaceVariant = Color(0xFFC5C6D0),
			outline = Color(0xFF8F9099),
			outlineVariant = Color(0xFF44464F),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFE2E2E9),
			inverseOnSurface = Color(0xFF2F3036),
			inversePrimary = Color(0xFF475D92),
			surfaceDim = Color(0xFF121318),
			surfaceBright = Color(0xFF38393F),
			surfaceContainerLowest = Color(0xFF0C0E13),
			surfaceContainerLow = Color(0xFF1A1B20),
			surfaceContainer = Color(0xFF1E1F25),
			surfaceContainerHigh = Color(0xFF282A2F),
			surfaceContainerHighest = Color(0xFF33353A),
		)
	)
	
	object RoyalLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF68548E),
			surfaceTint = Color(0xFF68548E),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFEBDDFF),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF635B70),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFE9DEF8),
			onSecondaryContainer = Color(0xFF1F182B),
			tertiary = Color(0xFF7E525D),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFFFD9E1),
			onTertiaryContainer = Color(0xFF31101B),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFFEF7FF),
			onBackground = Color(0xFF1D1B20),
			surface = Color(0xFFFEF7FF),
			onSurface = Color(0xFF1D1B20),
			surfaceVariant = Color(0xFFE7E0EB),
			onSurfaceVariant = Color(0xFF49454E),
			outline = Color(0xFF7A757F),
			outlineVariant = Color(0xFFCBC4CF),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF322F35),
			inverseOnSurface = Color(0xFFF5EFF7),
			inversePrimary = Color(0xFFD3BCFD),
			surfaceDim = Color(0xFFDED8E0),
			surfaceBright = Color(0xFFFEF7FF),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFF8F1FA),
			surfaceContainer = Color(0xFFF2ECF4),
			surfaceContainerHigh = Color(0xFFEDE6EE),
			surfaceContainerHighest = Color(0xFFE7E0E8),
		)
	)
	
	object RoyalDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFD3BCFD),
			surfaceTint = Color(0xFFD3BCFD),
			onPrimary = Color(0xFF38265C),
			primaryContainer = Color(0xFF4F3D74),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFCDC2DB),
			onSecondary = Color(0xFF342D40),
			secondaryContainer = Color(0xFF4B4358),
			onSecondaryContainer = Color(0xFFE9DEF8),
			tertiary = Color(0xFFF0B7C5),
			onTertiary = Color(0xFF4A2530),
			tertiaryContainer = Color(0xFF643B46),
			onTertiaryContainer = Color(0xFFFFD9E1),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF151218),
			onBackground = Color(0xFFE7E0E8),
			surface = Color(0xFF151218),
			onSurface = Color(0xFFE7E0E8),
			surfaceVariant = Color(0xFF49454E),
			onSurfaceVariant = Color(0xFFCBC4CF),
			outline = Color(0xFF948F99),
			outlineVariant = Color(0xFF49454E),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFE7E0E8),
			inverseOnSurface = Color(0xFF322F35),
			inversePrimary = Color(0xFF68548E),
			surfaceDim = Color(0xFF151218),
			surfaceBright = Color(0xFF3B383E),
			surfaceContainerLowest = Color(0xFF0F0D13),
			surfaceContainerLow = Color(0xFF1D1B20),
			surfaceContainer = Color(0xFF211F24),
			surfaceContainerHigh = Color(0xFF2C292F),
			surfaceContainerHighest = Color(0xFF36343A),
		)
	)
	
	object SlateLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF116682),
			surfaceTint = Color(0xFF116682),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFBDE9FF),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF4D616C),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFD0E6F2),
			onSecondaryContainer = Color(0xFF081E27),
			tertiary = Color(0xFF5D5B7D),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFE3DFFF),
			onTertiaryContainer = Color(0xFF191836),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFF6FAFD),
			onBackground = Color(0xFF171C1F),
			surface = Color(0xFFF6FAFD),
			onSurface = Color(0xFF171C1F),
			surfaceVariant = Color(0xFFDCE4E9),
			onSurfaceVariant = Color(0xFF40484C),
			outline = Color(0xFF70787D),
			outlineVariant = Color(0xFFC0C8CD),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF2C3134),
			inverseOnSurface = Color(0xFFEDF1F5),
			inversePrimary = Color(0xFF8BD0EF),
			surfaceDim = Color(0xFFD6DBDE),
			surfaceBright = Color(0xFFF6FAFD),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFF0F4F8),
			surfaceContainer = Color(0xFFEAEEF2),
			surfaceContainerHigh = Color(0xFFE4E9EC),
			surfaceContainerHighest = Color(0xFFDFE3E7),
		)
	)
	
	object SlateDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFF8BD0EF),
			surfaceTint = Color(0xFF8BD0EF),
			onPrimary = Color(0xFF003546),
			primaryContainer = Color(0xFF004D64),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFB4CAD6),
			onSecondary = Color(0xFF1F333C),
			secondaryContainer = Color(0xFF354A53),
			onSecondaryContainer = Color(0xFFD0E6F2),
			tertiary = Color(0xFFC6C2EA),
			onTertiary = Color(0xFF2E2D4D),
			tertiaryContainer = Color(0xFF454364),
			onTertiaryContainer = Color(0xFFE3DFFF),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF0F1417),
			onBackground = Color(0xFFDFE3E7),
			surface = Color(0xFF0F1417),
			onSurface = Color(0xFFDFE3E7),
			surfaceVariant = Color(0xFF40484C),
			onSurfaceVariant = Color(0xFFC0C8CD),
			outline = Color(0xFF8A9297),
			outlineVariant = Color(0xFF40484C),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFDFE3E7),
			inverseOnSurface = Color(0xFF2C3134),
			inversePrimary = Color(0xFF116682),
			surfaceDim = Color(0xFF0F1417),
			surfaceBright = Color(0xFF353A3D),
			surfaceContainerLowest = Color(0xFF0A0F11),
			surfaceContainerLow = Color(0xFF171C1F),
			surfaceContainer = Color(0xFF1B2023),
			surfaceContainerHigh = Color(0xFF262B2D),
			surfaceContainerHighest = Color(0xFF303538),
		)
	)
	
	object SoftLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF8E4957),
			surfaceTint = Color(0xFF8E4957),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFFFD9DE),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF75565B),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFFFD9DE),
			onSecondaryContainer = Color(0xFF2C1519),
			tertiary = Color(0xFF795831),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFFFDDBA),
			onTertiaryContainer = Color(0xFF2B1700),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFFFF8F7),
			onBackground = Color(0xFF22191A),
			surface = Color(0xFFFFF8F7),
			onSurface = Color(0xFF22191A),
			surfaceVariant = Color(0xFFF3DDDF),
			onSurfaceVariant = Color(0xFF524345),
			outline = Color(0xFF847375),
			outlineVariant = Color(0xFFD6C2C3),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF382E2F),
			inverseOnSurface = Color(0xFFFEEDEE),
			inversePrimary = Color(0xFFFFB2BE),
			surfaceDim = Color(0xFFE7D6D7),
			surfaceBright = Color(0xFFFFF8F7),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFFFF0F1),
			surfaceContainer = Color(0xFFFBEAEB),
			surfaceContainerHigh = Color(0xFFF5E4E5),
			surfaceContainerHighest = Color(0xFFF0DEE0),
		)
	)
	
	object SoftDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFFFB2BE),
			surfaceTint = Color(0xFFFFB2BE),
			onPrimary = Color(0xFF561D2A),
			primaryContainer = Color(0xFF71333F),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFE5BDC2),
			onSecondary = Color(0xFF43292D),
			secondaryContainer = Color(0xFF5C3F43),
			onSecondaryContainer = Color(0xFFFFD9DE),
			tertiary = Color(0xFFEBBF90),
			onTertiary = Color(0xFF452B08),
			tertiaryContainer = Color(0xFF5F411C),
			onTertiaryContainer = Color(0xFFFFDDBA),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF191112),
			onBackground = Color(0xFFF0DEE0),
			surface = Color(0xFF191112),
			onSurface = Color(0xFFF0DEE0),
			surfaceVariant = Color(0xFF524345),
			onSurfaceVariant = Color(0xFFD6C2C3),
			outline = Color(0xFF9F8C8E),
			outlineVariant = Color(0xFF524345),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFF0DEE0),
			inverseOnSurface = Color(0xFF382E2F),
			inversePrimary = Color(0xFF8E4957),
			surfaceDim = Color(0xFF191112),
			surfaceBright = Color(0xFF413738),
			surfaceContainerLowest = Color(0xFF140C0D),
			surfaceContainerLow = Color(0xFF22191A),
			surfaceContainer = Color(0xFF261D1E),
			surfaceContainerHigh = Color(0xFF312829),
			surfaceContainerHighest = Color(0xFF3C3233),
		)
	)
	
	object SunsetLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF8F4C35),
			surfaceTint = Color(0xFF8F4C35),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFFFDBD0),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF77574D),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFFFDBD0),
			onSecondaryContainer = Color(0xFF2C160E),
			tertiary = Color(0xFF6A5E2F),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFF4E2A7),
			onTertiaryContainer = Color(0xFF221B00),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFFFF8F6),
			onBackground = Color(0xFF231917),
			surface = Color(0xFFFFF8F6),
			onSurface = Color(0xFF231917),
			surfaceVariant = Color(0xFFF5DED7),
			onSurfaceVariant = Color(0xFF53433F),
			outline = Color(0xFF85736E),
			outlineVariant = Color(0xFFD8C2BB),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF392E2B),
			inverseOnSurface = Color(0xFFFFEDE8),
			inversePrimary = Color(0xFFFFB59D),
			surfaceDim = Color(0xFFE8D6D1),
			surfaceBright = Color(0xFFFFF8F6),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFFFF1ED),
			surfaceContainer = Color(0xFFFCEAE5),
			surfaceContainerHigh = Color(0xFFF7E4DF),
			surfaceContainerHighest = Color(0xFFF1DFDA),
		)
	)
	
	object SunsetDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFFFB59D),
			surfaceTint = Color(0xFFFFB59D),
			onPrimary = Color(0xFF55200C),
			primaryContainer = Color(0xFF723520),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFE7BDB1),
			onSecondary = Color(0xFF442A21),
			secondaryContainer = Color(0xFF5D4036),
			onSecondaryContainer = Color(0xFFFFDBD0),
			tertiary = Color(0xFFD7C68D),
			onTertiary = Color(0xFF3A3005),
			tertiaryContainer = Color(0xFF51461A),
			onTertiaryContainer = Color(0xFFF4E2A7),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF1A110F),
			onBackground = Color(0xFFF1DFDA),
			surface = Color(0xFF1A110F),
			onSurface = Color(0xFFF1DFDA),
			surfaceVariant = Color(0xFF53433F),
			onSurfaceVariant = Color(0xFFD8C2BB),
			outline = Color(0xFFA08D87),
			outlineVariant = Color(0xFF53433F),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFF1DFDA),
			inverseOnSurface = Color(0xFF392E2B),
			inversePrimary = Color(0xFF8F4C35),
			surfaceDim = Color(0xFF1A110F),
			surfaceBright = Color(0xFF423733),
			surfaceContainerLowest = Color(0xFF140C0A),
			surfaceContainerLow = Color(0xFF231917),
			surfaceContainer = Color(0xFF271D1B),
			surfaceContainerHigh = Color(0xFF322825),
			surfaceContainerHighest = Color(0xFF3D322F),
		)
	)
	
	object TealLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF006A60),
			surfaceTint = Color(0xFF006A60),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFF9EF2E4),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF4A635F),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFCCE8E2),
			onSecondaryContainer = Color(0xFF05201C),
			tertiary = Color(0xFF456179),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFCCE5FF),
			onTertiaryContainer = Color(0xFF001E31),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFF4FBF8),
			onBackground = Color(0xFF161D1C),
			surface = Color(0xFFF4FBF8),
			onSurface = Color(0xFF161D1C),
			surfaceVariant = Color(0xFFDAE5E1),
			onSurfaceVariant = Color(0xFF3F4947),
			outline = Color(0xFF6F7977),
			outlineVariant = Color(0xFFBEC9C6),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF2B3230),
			inverseOnSurface = Color(0xFFECF2EF),
			inversePrimary = Color(0xFF82D5C8),
			surfaceDim = Color(0xFFD5DBD9),
			surfaceBright = Color(0xFFF4FBF8),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFEFF5F2),
			surfaceContainer = Color(0xFFE9EFED),
			surfaceContainerHigh = Color(0xFFE3EAE7),
			surfaceContainerHighest = Color(0xFFDDE4E1),
		)
	)
	
	object TealDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFF82D5C8),
			surfaceTint = Color(0xFF82D5C8),
			onPrimary = Color(0xFF003731),
			primaryContainer = Color(0xFF005048),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFB1CCC6),
			onSecondary = Color(0xFF1C3531),
			secondaryContainer = Color(0xFF334B47),
			onSecondaryContainer = Color(0xFFCCE8E2),
			tertiary = Color(0xFFADCAE6),
			onTertiary = Color(0xFF153349),
			tertiaryContainer = Color(0xFF2D4961),
			onTertiaryContainer = Color(0xFFCCE5FF),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF0E1513),
			onBackground = Color(0xFFDDE4E1),
			surface = Color(0xFF0E1513),
			onSurface = Color(0xFFDDE4E1),
			surfaceVariant = Color(0xFF3F4947),
			onSurfaceVariant = Color(0xFFBEC9C6),
			outline = Color(0xFF899390),
			outlineVariant = Color(0xFF3F4947),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFDDE4E1),
			inverseOnSurface = Color(0xFF2B3230),
			inversePrimary = Color(0xFF006A60),
			surfaceDim = Color(0xFF0E1513),
			surfaceBright = Color(0xFF343B39),
			surfaceContainerLowest = Color(0xFF090F0E),
			surfaceContainerLow = Color(0xFF161D1C),
			surfaceContainer = Color(0xFF1A2120),
			surfaceContainerHigh = Color(0xFF252B2A),
			surfaceContainerHighest = Color(0xFF303635),
		)
	)
	
	object VibrantLightTheme : AppThemes(
		colors = lightColorScheme(
			primary = Color(0xFF904A42),
			surfaceTint = Color(0xFF904A42),
			onPrimary = Color(0xFFFFFFFF),
			primaryContainer = Color(0xFFFFDAD5),
			onPrimaryContainer = Color(0xFF000000),
			secondary = Color(0xFF775652),
			onSecondary = Color(0xFFFFFFFF),
			secondaryContainer = Color(0xFFFFDAD5),
			onSecondaryContainer = Color(0xFF2C1512),
			tertiary = Color(0xFF705C2E),
			onTertiary = Color(0xFFFFFFFF),
			tertiaryContainer = Color(0xFFFCDFA6),
			onTertiaryContainer = Color(0xFF261A00),
			error = Color(0xFFBA1A1A),
			onError = Color(0xFFFFFFFF),
			errorContainer = Color(0xFFFFDAD6),
			onErrorContainer = Color(0xFF410002),
			background = Color(0xFFFFF8F7),
			onBackground = Color(0xFF231918),
			surface = Color(0xFFFFF8F7),
			onSurface = Color(0xFF231918),
			surfaceVariant = Color(0xFFF5DDDA),
			onSurfaceVariant = Color(0xFF534341),
			outline = Color(0xFF857370),
			outlineVariant = Color(0xFFD8C2BE),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFF392E2C),
			inverseOnSurface = Color(0xFFFFEDEA),
			inversePrimary = Color(0xFFFFB4A9),
			surfaceDim = Color(0xFFE8D6D3),
			surfaceBright = Color(0xFFFFF8F7),
			surfaceContainerLowest = Color(0xFFFFFFFF),
			surfaceContainerLow = Color(0xFFFFF0EE),
			surfaceContainer = Color(0xFFFCEAE7),
			surfaceContainerHigh = Color(0xFFF7E4E1),
			surfaceContainerHighest = Color(0xFFF1DEDC),
		)
	)
	
	object VibrantDarkTheme : AppThemes(
		colors = darkColorScheme(
			primary = Color(0xFFFFB4A9),
			surfaceTint = Color(0xFFFFB4A9),
			onPrimary = Color(0xFF561E18),
			primaryContainer = Color(0xFF73342C),
			onPrimaryContainer = Color(0xFFFFFFFF),
			secondary = Color(0xFFE7BDB7),
			onSecondary = Color(0xFF442926),
			secondaryContainer = Color(0xFF5D3F3B),
			onSecondaryContainer = Color(0xFFFFDAD5),
			tertiary = Color(0xFFDFC38C),
			onTertiary = Color(0xFF3E2E04),
			tertiaryContainer = Color(0xFF574419),
			onTertiaryContainer = Color(0xFFFCDFA6),
			error = Color(0xFFFFB4AB),
			onError = Color(0xFF690005),
			errorContainer = Color(0xFF93000A),
			onErrorContainer = Color(0xFFFFDAD6),
			background = Color(0xFF1A1110),
			onBackground = Color(0xFFF1DEDC),
			surface = Color(0xFF1A1110),
			onSurface = Color(0xFFF1DEDC),
			surfaceVariant = Color(0xFF534341),
			onSurfaceVariant = Color(0xFFD8C2BE),
			outline = Color(0xFFA08C89),
			outlineVariant = Color(0xFF534341),
			scrim = Color(0xFF000000),
			inverseSurface = Color(0xFFF1DEDC),
			inverseOnSurface = Color(0xFF392E2C),
			inversePrimary = Color(0xFF904A42),
			surfaceDim = Color(0xFF1A1110),
			surfaceBright = Color(0xFF423735),
			surfaceContainerLowest = Color(0xFF140C0B),
			surfaceContainerLow = Color(0xFF231918),
			surfaceContainer = Color(0xFF271D1C),
			surfaceContainerHigh = Color(0xFF322826),
			surfaceContainerHighest = Color(0xFF3D3231),
		)
	)
	
}
```

---

## Run The App Once