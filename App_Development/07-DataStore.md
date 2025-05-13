# DataStore

## App Data Store

```kt
private val Context.appDataStore by preferencesDataStore("app_data_store")

@Singleton
class AppDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val utils: CFDataStore
){
    private val dataStore = context.appDataStore

    override fun getIsFirstTimeUser(): Flow<Boolean?> {
        return utils.getPreferences(
            dataStore,
            AppDataStoreKeys.IsFirstTimeUser.key
        )
    }

    override suspend fun setIsFirstTimeUser(isFirstTimeUser: Boolean) {
        utils.setPreferences(
            dataStore,
            AppDataStoreKeys.IsFirstTimeUser.key,
            isFirstTimeUser
        )
    }
}
```

---

## App Data Store Keys

```kt

sealed class AppDataStoreKeys<T>(val key: Preferences.Key<T>){

    data object IsFirstTimeUser: AppDataStoreKeys<Boolean>(
        key = booleanPreferencesKey("is_first_time_user")
    )

    data object SelectedLifeAreaTopic: AppDataStoreKeys<String>(
        key = stringPreferencesKey("selected_life_area_topic")
    )

    data object SelectedObstacles: AppDataStoreKeys<String>(
        key = stringPreferencesKey("selected_obstacles")
    )

    data object SelectedDefaultHabits: AppDataStoreKeys<String>(
        key = stringPreferencesKey("selected_default_habits")
    )

    data object IsPremiumUser: AppDataStoreKeys<Boolean>(
        key = booleanPreferencesKey("is_premium_user")
    )

    data object PremiumType: AppDataStoreKeys<String>(
        key = stringPreferencesKey("premium_type")
    )

    data object IsAllPermissionsGranted: AppDataStoreKeys<Boolean>(
        key = booleanPreferencesKey("is_all_permissions_granted")
    )

    data object IsDarkModeEnabled: AppDataStoreKeys<Boolean>(
        key = booleanPreferencesKey("is_dark_mode_enabled")
    )

    data object AchievementScore: AppDataStoreKeys<Int>(
        key = intPreferencesKey("achievement_score")
    )

    data object NotificationType: AppDataStoreKeys<String>(
        key = stringPreferencesKey("notification_type")
    )

    data object DefaultReminderTime: AppDataStoreKeys<String>(
        key = stringPreferencesKey("default_reminder_time")
    )

    data object QuietHoursStartTime: AppDataStoreKeys<String>(
        key = stringPreferencesKey("quiet_hours_start_time")
    )

    data object QuietHoursEndTime: AppDataStoreKeys<String>(
        key = stringPreferencesKey("quiet_hours_end_time")
    )

    data object HabitSortType: AppDataStoreKeys<String>(
        key = stringPreferencesKey("habit_sort_type")
    )
}

```

--- 

## CFDataStore

```kt
object CFDataStore {
    fun <T> getPreferences(
        dataStore: DataStore<Preferences>,
        key: Preferences.Key<T>
    ): Flow<T?> {
        return dataStore.data.catch{
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[key]
        }
    }

    suspend fun <T> setPreferences(
        dataStore: DataStore<Preferences>,
        key: Preferences.Key<T>,
        value: T
    ) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}
```

--- 

## CFDataStoreModule

```kt
@Module
@InstallIn(SingletonComponent::class)
object CFDataStoreModule {

    @Provides
    @Singleton
    fun provideCFDataStore(): CFDataStore{
        return CFDataStore
    }

}
```

## Data Store Module

```kt

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideAppDataStore(
        @ApplicationContext context: Context,
        utils: CFDataSore
    ): AppDataStore {
        return AppDataStoreImpl(
            context,
            utils
        )
    }
}
```

---