# Koin Implementation Roadmap for Multiplatform Apps

## Step 1: Add Koin Dependencies

Add Koin dependencies to your `build.gradle.kts` files:

**Project-level `build.gradle.kts`:**
```kotlin
buildscript {
    dependencies {
        // Add Koin version in versions catalog or directly
    }
}
```

**Module-level `build.gradle.kts` (commonMain):**
```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            // Koin Core
            implementation("io.insert-koin:koin-core:3.5.0")
            
            // Koin Compose (for Compose Multiplatform)
            implementation("io.insert-koin:koin-compose:1.1.0")
        }
        
        androidMain.dependencies {
            // Koin Android (if using Android-specific features)
            implementation("io.insert-koin:koin-android:3.5.0")
        }
    }
}
```

## Step 2: Create DI Module Files

Create module files in `di/` package following this structure:

**`di/AppModule.kt`** - Application-level dependencies:
```kotlin
package com.visionforgestudio.deepflow.di

import org.koin.dsl.module

val appModule = module {
    // ViewModels, Utilities, Managers
    // Example: single { AppManager() }
}
```

**`di/DatabaseModule.kt`** - Database dependencies:
```kotlin
package com.visionforgestudio.deepflow.di

import org.koin.dsl.module

val databaseModule = module {
    // Room database, DAOs
    // Platform-specific implementations go here
}
```

**`di/NetworkModule.kt`** - Network dependencies:
```kotlin
package com.visionforgestudio.deepflow.di

import org.koin.dsl.module

val networkModule = module {
    // Ktor client, API services
    single { /* HttpClient configuration */ }
}
```

**`di/RepositoryModule.kt`** - Repository layer:
```kotlin
package com.visionforgestudio.deepflow.di

import org.koin.dsl.module

val repositoryModule = module {
    // Repositories
    single { /* Repository(get(), get()) */ }
}
```

**`di/UseCaseModule.kt`** - Use cases/domain logic:
```kotlin
package com.visionforgestudio.deepflow.di

import org.koin.dsl.module

val useCaseModule = module {
    // Use cases
    factory { /* UseCase(get()) */ }
}
```

## Step 3: Define Components with Koin

Components should receive dependencies through constructor injection:

**Example Component:**
```kotlin
class DefaultSplashComponent(
    componentContext: ComponentContext,
    private val checkAuthUseCase: CheckAuthUseCase, // Injected
    private val onNavigateToOnboarding: () -> Unit,
    private val onNavigateToLogin: () -> Unit,
    private val onNavigateToHome: () -> Unit
) : SplashComponent, ComponentContext by componentContext {
    // Component logic
}
```

## Step 4: Initialize Koin in Application

**For Android (`androidMain/MainActivity.kt`):**
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        startKoin {
            androidContext(this@MainActivity)
            modules(
                appModule,
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule
            )
        }
        
        // Rest of the setup
    }
}
```

**For other platforms (iOS, Desktop, etc.):**

Create a common initialization function in `commonMain`:

```kotlin
// di/KoinInitializer.kt
fun initKoin() {
    startKoin {
        modules(
            appModule,
            databaseModule,
            networkModule,
            repositoryModule,
            useCaseModule
        )
    }
}
```

## Step 5: Inject Dependencies in RootComponent

**`ui/root/DefaultRootComponent.kt`:**
```kotlin
class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    
    // Get Koin instance
    private val koin = KoinPlatform.getKoin()
    
    private val navigation = StackNavigation<Config>()
    
    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Splash,
            handleBackButton = true,
            childFactory = ::child,
        )
    
    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Splash -> RootComponent.Child.SplashChild(
                DefaultSplashComponent(
                    componentContext = componentContext,
                    checkAuthUseCase = koin.get(), // Inject from Koin
                    onNavigateToOnboarding = { /* navigation */ },
                    onNavigateToLogin = { /* navigation */ },
                    onNavigateToHome = { /* navigation */ }
                )
            )
            // Other screens...
        }
}
```

## Step 6: Define Dependencies in Modules

**Example: UseCaseModule with all dependencies:**
```kotlin
val useCaseModule = module {
    factory { CheckAuthUseCase(get()) }
    factory { LoginUseCase(get(), get()) }
    factory { SaveTimerSettingsUseCase(get()) }
    // Add all use cases
}
```

**Example: RepositoryModule:**
```kotlin
val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<TimerRepository> { TimerRepositoryImpl(get()) }
    // Add all repositories
}
```

## Step 7: Koin Scopes (Optional for Decompose Components)

Define scoped dependencies for specific features:

```kotlin
val featureModule = module {
    scope<SomeComponent> {
        scoped { FeatureSpecificDependency() }
    }
}
```

## Key Koin Concepts

- **`single`**: Creates singleton instance (shared across app)
- **`factory`**: Creates new instance every time
- **`get()`**: Retrieves dependency from Koin
- **`inject()`**: Lazy injection (alternative to `get()`)
- **`androidContext()`**: Provides Android Context (Android only)

## Common Patterns

### 1. Inject in Component
```kotlin
private val repository: Repository = koin.get()
```

### 2. Multiple Parameters
```kotlin
class MyUseCase(
    private val repo1: Repository1,
    private val repo2: Repository2
)

// In module
factory { MyUseCase(get(), get()) }
```

### 3. Named Dependencies
```kotlin
single(named("cached")) { CachedRepository() }
single(named("remote")) { RemoteRepository() }

// Usage
get(named("cached"))
```

## Troubleshooting

1. **No definition found**: Ensure module is loaded in `startKoin`
2. **Circular dependencies**: Use lazy injection or refactor
3. **Platform-specific**: Use `expect`/`actual` for platform-specific implementations

This roadmap covers the complete Koin setup for a multiplatform Decompose-based app. Follow these steps in order for any new project.