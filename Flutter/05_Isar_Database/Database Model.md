# Isar Database Model

Note: This is an example of User Model

```dart
part 'user_model.g.dart';  
  
@collection  
@JsonSerializable()  
class UserModel {  
  Id id = Isar.autoIncrement;  
  
  // Basic User Information  
  
  @Index(unique: true)  
  String? firebaseUid;  
  
  @Index(unique: true)  
  String? username;  
  
  String? email;  
  String? displayName;  
  String? profilePictureUrl;  
  
  // User Preferences  
  
  @enumerated  
  List<AreaOfLife> areasOfLife = [];  
  
  @enumerated  
  List<BiggestHurdle> biggestHurdles = [];  
  
  String? promiseToFutureSelf;  
  
  // Premium Status  
  
  bool isPremiumUser =  
      false; // TODO: This should be checked at the starting provide premium features  
  DateTime?  
  premiumExpiryDate; // TODO: This should be checked at the starting and reset if needed  
  String?  
  subscriptionType; // TODO: Create SubscriptionType enum - monthly, yearly, lifetime  
  bool hasRemovedAds = false;  
  
  int freezeTokens = 0;  
  DateTime?  
  lastFreezeTokenReset; // TODO: This should be checked at the starting and reset if needed  
  
  // User Statistics  
  
  int totalRoutinesCreated = 0;  
  int totalTasksCompleted = 0;  
  int totalRoutinesCompleted = 0;  
  int longestStreak = 0;  
  int currentGlobalStreak = 0;  
  
  // Social Features  
  
  bool isLeaderboardEnabled = true;  
  bool isGlobalChallengesEnabled = true;  
  bool canReceiveNotifications = true; // TODO: Ask for notification permission  
  
  // App Settings  
  @enumerated  
  ThemeMode themeMode = ThemeMode.system; // Default to system theme  
  bool soundEnabled = true;  
  bool vibrationEnabled = true;  
  String defaultNotificationSound =  
      'default'; // TODO: Create NotificationSound enum  
  String defaultAlarmSound = 'default'; // TODO: Create AlarmSound enum  
  String defaultReminderTime = '08:00'; // Default reminder time  
  
  // Onboarding status  bool hasCompletedOnboarding = false;  
  bool hasCompletedTutorial = false;  
  
  // Analytics  
  DateTime? lastActiveDate;  
  int totalAppOpenCount = 0;  
  double averageRoutineCompletionRate = 0.0;  
  
  // Cloud Syncing  
  bool isCloudSyncEnabled = false;  
  DateTime? lastCloudSyncDate;  
  bool needsCloudSync = false;  
  String? cloudSyncVersion;  
  
  // Timestamps  
  DateTime createdAt = DateTime.now().toUtc();  
  DateTime updatedAt = DateTime.now().toUtc();  
  
  UserModel();  
  
  factory UserModel.fromJson(Map<String, dynamic> json) =>  
      _$UserModelFromJson(json);  
  
  Map<String, dynamic> toJson() => _$UserModelToJson(this);  
  
  // Helper method to check if user can access premium features  
  bool get canAccessPremiumFeatures {  
    if (!isPremiumUser) return false;  
    if (subscriptionType == 'lifetime') return true;  
    if (premiumExpiryDate == null) return false;  
    return DateTime.now().isBefore(premiumExpiryDate!);  
  }  
  // Helper method to get monthly freeze tokens for premium users  
  int get monthlyFreezeTokenAllowance {  
    return canAccessPremiumFeatures ? 10 : 0;  
  }}
```

## Dual Annotation: `@collection` & `@JsonSerializable`

- **What:**  
  The model is annotated with both `@collection` (Isar) and `@JsonSerializable` (for JSON/Firestore).
- **Why:**  
  Enables seamless use as both a local database model (Isar) and a cloud sync/data transfer object (Firestore, APIs), eliminating model duplication and reducing bugs.

---

## 2. `Id id = Isar.autoIncrement`

- **What:**  
  Unique, auto-incrementing local identifier for every user.
- **Why:**  
  Ensures every user record is uniquely identifiable within the Isar database, regardless of cloud identity or external data.

---

## 3. Unique Indexing: `@Index(unique: true)` for `firebaseUid` and `username`

- **What:**  
  Enforces uniqueness constraints at the database level for both Firebase UID and username.
- **Why:**  
  Guarantees rapid lookups and prevents duplicate accounts, supporting both cloud authentication and in-app social features.

---

## 4. Enum Lists with `@enumerated`

- **What:**  
  Stores multiple enum values (such as `areasOfLife` and `biggestHurdles`) as efficient native enums.
- **Why:**  
  Provides type safety, fast queries, and easy future extension as onboarding or analytics needs evolve.

---

## 5. Premium Status Logic

- **What:**  
  Tracks premium state, expiry, subscription type (`monthly`, `yearly`, `lifetime`), ad removal, and freeze tokens.
- **Why:**  
  Supports robust, maintainable feature gating and business logic for subscriptions, IAPs, and streak protection.  
  Helper getters (e.g., `canAccessPremiumFeatures`) encapsulate business rules for clarity and reuse.

---

## 6. Theme & App Settings (Including Enum Storage)

- **What:**  
  Stores theme mode as an enum, as well as sound, vibration, and notification/alarm settings.
- **Why:**  
  Enables deep user customization and accessibility, with a structure that’s easy to extend (e.g., for new themes or notification types).

---

## 7. Onboarding & Analytics Tracking

- **What:**  
  Tracks onboarding/tutorial completion, last active date, app open count, and completion rates.
- **Why:**  
  Drives personalized onboarding, user segmentation, and actionable insights for both users and the product team.

---

## 8. Cloud Sync Compatibility

- **What:**  
  Fields for tracking cloud sync status, last sync timestamps, and versioning.
- **Why:**  
  Allows for seamless, conflict-free manual cloud sync (premium feature) and prepares the model for future cloud logic or migrations.

---

## 9. Timestamp Management

- **What:**  
  Auto-captures `createdAt` and `updatedAt` in UTC.
- **Why:**  
  Essential for data integrity, audit trails, and resolving sync conflicts.

---

## 10. Social & Streak Features

- **What:**  
  Flags for leaderboard/challenges, notification permissions, and freeze token management.
- **Why:**  
  Supports opt-in social features, privacy, and advanced motivational mechanics like freeze tokens and streak tracking.

---

## 11. Extensibility & Future Proofing

- **Soft Delete Flag:**  
  *Recommended addition*: `isDeleted` for recoverable user deletion.
- **Locale/Language:**  
  For future localization.
- **Device Info:**  
  For analytics/debugging.
- **Versioning:**  
  For easier migrations and upgrades.

---

## Architectural Rationale

- **Single Source of Truth:**  
  The model is designed to be used across local (Isar) and remote (Firestore/JSON) layers, reducing maintenance and bugs.
- **Type Safety:**  
  Heavy use of enums and indexed fields ensures both performance and reliability.
- **Extensible:**  
  The design anticipates future features, such as new onboarding options, premium logic, or additional analytics.

---