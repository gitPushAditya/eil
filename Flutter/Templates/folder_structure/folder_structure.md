```bash
lib/
│
├── core/
│   ├── constants/
│   │   └── app_constants.dart         # Global app constants
│   ├── enums/
│   │   └── app_enums.dart             # Application enums
│   ├── extensions/
│   │   └── context_extensions.dart    # Extension methods for context or other classes
│   ├── utils/
│       └── app_router.dart            # GoRouter configuration and app routes
│       └── helpers.dart               # Helper functions, utilities
│
├── data/
│   ├── db/
│   │   └── database_service.dart      # Database logic (e.g., sqflite or others)
│   ├── local/
│   │   └── shared_pref_service.dart   # Local storage (e.g., shared preferences)
│   ├── models/
│   │   └── user_model.dart            # Data models
│   ├── providers/
│   │   └── user_provider.dart         # Provider for user state management
│   │   └── theme_provider.dart        # Provider for theme management
│   └── services/
│       └── api_service.dart           # Services for API calls
│       └── notification_service.dart  # Services for notifications (e.g., Firebase)
│
├── logic/
│   ├── controllers/
│   │   └── user_controller.dart       # Handles user-related logic
│   ├── use_cases/
│       └── user_use_case.dart         # Business logic for user (use case patterns)
│       └── theme_use_case.dart        # Business logic for theme-related changes
│
├── ui/
│   ├── components/
│   │   └── custom_button.dart         # Reusable components like buttons, cards, etc.
│   │   └── custom_card.dart           # Custom reusable card
│   ├── pages/
│   │   ├── home_page.dart             # Home page
│   │   ├── profile_page.dart          # Profile page
│   │   └── settings_page.dart         # Settings page for theme change, etc.
│   ├── themes/
│   │   └── app_themes.dart            # Light and Dark theme configuration
│   └── widgets/
│       └── loader_widget.dart         # Common widget like loader, etc.
│
├── app.dart                           # Root widget where providers are set up
└── main.dart                          # Entry point of the app

```
