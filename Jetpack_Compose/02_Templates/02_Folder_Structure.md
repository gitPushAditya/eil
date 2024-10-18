# Folder Structure

com.example.android
├── MainActivity.kt
├── App.kt
│  
│  
├── data
│ ├── network
│ │ ├── service
│ │ ├── client
│ │ ├── model
│ │ └── error
│ │
│ ├── db
│ │ ├── entity
│ │ ├── dao
│ │ ├── database
│ │ ├── converter
│ │ └── migration
│ │
│ ├── pref
│ │  
│ └── repository
│
├── domain
│ ├── model
│ └── repository
│
├── di
│ ├── modules
│ ├── scopes
│ └── components
│
├── ui
│ ├── screens
│ │ ├──screen1
│ │ │ ├── Screen1.kt
│ │ │ ├── VideModel1.kt
│ │ │ └── components
│ │ │ ├── Component1.kt
│ │ │ └── Component2.kt
│ │ │
│ │ └──screen2
│ │ ├── Screen2.kt
│ │ ├── VideModel2.kt
│ │ └── components
│ │ ├── Component1.kt
│ │ └── Component2.kt
│ │
│ ├── common
│ │ ├── shared components
│ │ └── shared viewmodels
│ │  
│ └── theme
│ │ ├── color
│ │ ├── shape
│ │ ├── theme
│ │ └── type
│ │
│ └── navigation
│  
├── notification
├── workers
├── constants
└── utils

## MainActivity.kt

Contains the main entry point of the application, like the MainActivity.

## App.kt

Contains the Application class and custom Application components.

## data

Handles data-related operations like API calls, database interactions, and data storage.

### data.network

Contains API service interfaces and network-related classes.

**service**

Contains Retrofit service interfaces responsible for defining API endpoints and request methods.
Each service interface represents a specific API endpoint and contains methods for making HTTP requests.

**client**

Contains the configuration and setup of the HTTP client, typically using Retrofit or another networking library.
Includes classes for setting up interceptors, authentication, and other network-related configurations.

**model**

Contains data models specific to the API responses.
These models represent the structure of data returned from the API and may include serialization/deserialization logic.

**error**

Contains classes or enums representing API error responses or exceptions.
Handles error mapping and conversion from network errors to domain-specific error types.

### data.db

The db package manages interactions with the local database using Room or another persistence library.

**entity**

Contains Room entities representing database tables.
Each entity class typically corresponds to a database table and defines its schema.

**dao**

Contains Data Access Objects (DAOs) responsible for defining database operations.
Each DAO interface contains methods for performing CRUD operations on a specific entity.

**database**

Contains the Room database class responsible for creating and managing the database instance.
Defines the database schema and sets up the DAOs.

**converter**

Contains TypeConverters for converting custom data types to/from supported database types.
Use TypeConverters when Room does not support mapping certain data types directly.

**migration**

Contains migration classes for handling database schema migrations.
Define migration strategies for upgrading the database schema when the app is updated.

### data.pref

The pref package is responsible for managing key-value base data storage using SharedPreferences or DataStorage.

### data.repository

Implements the repository pattern to provide a clean API for data access.

## domain

Contains business logic and domain-specific entities.

### domain.model

Contains domain-specific data models/entities.

### domain.repository

Defines repository interfaces representing data access points.

## di

Manages dependency injection setup using Dagger/Hilt.

### modules

Contains Dagger/Hilt modules for providing dependencies.

### scopes

Defines custom ApplicationComponent and other DI-related components.

### components

Contains custom scopes if needed.

## ui

### ui.screens

This directory contains UI-related components organized by screens or features of the application.

**screen**

Contains the Composable function defining the UI for Screen.

**viewmodel**

Houses the ViewModel responsible for managing the UI logic and data for Screen.

**components**

Contains reusable UI components specific to Screen.

### ui.common

This directory contains shared components and ViewModels that are reused across multiple screens or features of the application.

**shared components**

Contains reusable UI components shared across different screens.

**shared viewmodels**

Contains ViewModels shared across different screens, managing common UI logic or data.

### ui.theme

Contains Compose themes and styling configurations.

## ui.navigation

Manages navigation logic using Jetpack Navigation.

## notification

Include classes responsible for creating, displaying, and managing notifications.

## workers

Place each Worker class in this package, defining its specific task or job.

## utils

Contains utility classes and helper functions.

## constants

Contains all the constants used in the app.
