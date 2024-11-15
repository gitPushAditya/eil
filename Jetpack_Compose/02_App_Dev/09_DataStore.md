# DataStore

We will create datastore files in `data/datastore`.

Normally in app, we can use three different datastores. 

- UserPreferences - App Settings based on user preferences. Eg. Notification On/Off, Themes, etc.
- UserData - Data related to user which will be used in app. Eg. UserName etc.
- AppData - Data which is completely managed by app. Eg. SelectedScreen etc.

## Steps to create datastore

### Step 1 - Define a variable to access datastore.

```kotlin
val Context.appDataDataStore by preferencesDataStore(DataStoreConstants.APP_DATA.name)
```

### Step 2 - Create a class with dependeny injection and define private datastore.

```kotlin
class AppData  @Inject constructor(@ApplicationContext private val context: Context){
	private val dataStore = context.appDataDataStore
}
```

---

### Step 3 - Create keys as companion objects.

```kotlin
val Context.appDataDataStore by preferencesDataStore(DataStoreConstants.APP_DATA.name)

class AppData  @Inject constructor(@ApplicationContext private val context: Context){
	private val dataStore = context.appDataDataStore

    companion object {
		val IS_FIRST_TIME_USER_KEY =
			booleanPreferencesKey(DataStoreConstants.IS_FIRST_TIME_USER.name)
		val IS_HOME_TUTORIAL_SHOWN_KEY =
			booleanPreferencesKey(DataStoreConstants.IS_HOME_TUTORIAL_SHOWN.name)
		val IS_NOTIFICATION_DIALOG_SHOWN_KEY =
			booleanPreferencesKey(DataStoreConstants.IS_NOTIFICATION_DIALOG_SHOWN.name)
		val SELECTED_PAGE_KEY = stringPreferencesKey(DataStoreConstants.SELECTED_PAGE.name)
	}
}
```

*Note: It is a good practice to create a sealed class to store all the constatns name.*

---

### Step 4 - Get Data

```kotlin
fun getStringPreferences(key: Preferences.Key<String>): Flow<String?> {
		return dataStore.data.catch {
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	fun getBooleanPreferences(key: Preferences.Key<Boolean>): Flow<Boolean?> {
		return dataStore.data.catch {
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	fun getIntPreferences(key: Preferences.Key<Int>): Flow<Int?> {
		return dataStore.data.catch {
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	fun getLongPreferences(key: Preferences.Key<Long>): Flow<Long?> {
		return dataStore.data.catch {
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	fun getFloatPreferences(key: Preferences.Key<Float>): Flow<Float?> {
		return dataStore.data.catch {
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	fun getStringSetPreferences(key: Preferences.Key<Set<String>>): Flow<Set<String>?> {
		return dataStore.data.catch {
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
```

---

### Step 5 - Set Data

```kotlin
suspend fun setStringPreferences(key: Preferences.Key<String>, value: String) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	suspend fun setBooleanPreferences(key: Preferences.Key<Boolean>, value: Boolean) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	suspend fun setIntPreferences(key: Preferences.Key<Int>, value: Int) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	suspend fun setLongPreferences(key: Preferences.Key<Long>, value: Long) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	suspend fun setFloatPreferences(key: Preferences.Key<Float>, value: Float) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	suspend fun setStringSetPreferences(key: Preferences.Key<Set<String>>, value: Set<String>) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}

```


---

## Connecting to Repository



