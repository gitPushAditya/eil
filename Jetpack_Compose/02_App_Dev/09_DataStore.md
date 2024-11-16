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
			booleanPreferencesKey(DataStoreConstants.IsFirstTimeUser.name)
		val IS_HOME_TUTORIAL_SHOWN_KEY =
			booleanPreferencesKey(DataStoreConstants.IsHomeTutorialShown.name)
		val IS_NOTIFICATION_DIALOG_SHOWN_KEY =
			booleanPreferencesKey(DataStoreConstants.IsNotificationDialogShown.name)
		val SELECTED_PAGE_KEY = stringPreferencesKey(DataStoreConstants.SelectedPage.name)
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

Repository is the layer where we add default values to null value and do all the conversions if needed.

```kotlin
import com.visionforgestudio.drinkup.data.datastore.store.AppData
import com.visionforgestudio.drinkup.global.defaults.AppDataDefaults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataRepository @Inject constructor(private val appData: AppData) {

	val isFirstTimeUserFlow: Flow<Boolean> = appData.isFirstTimeUserFlow.map {
		it ?: AppDataDefaults.isFirstTimeUser
	}
	
	val isHomeTutorialShownFlow: Flow<Boolean> = appData.isHomeTutorialShownFlow.map {
		it ?: AppDataDefaults.isHomeTutorialShown
	}
	
	val isNotificationDialogShownFlow: Flow<Boolean> = appData.isNotificationDialogShownFlow.map {
		it ?: AppDataDefaults.isNotificationDialogShown
	}
	
	val selectedPageFlow: Flow<String> = appData.selectedPageFlow.map {
		it ?: AppDataDefaults.selectedPage
	}
	
	suspend fun setIsFirstTimeUser(isFirstTimeUser: Boolean) {
		appData.setIsFirstTimeUser(isFirstTimeUser)
	}
	
	suspend fun setIsHomeTutorialShown(isHomeTutorialShown: Boolean) {
		appData.setIsHomeTutorialShown(isHomeTutorialShown)
	}

	suspend fun setIsNotificationDialogShown(isNotificationDialogShown: Boolean) {
		appData.setIsNotificationDialogShown(isNotificationDialogShown)
	}
	
	suspend fun setSelectedPage(selectedPage: String) {
		appData.setSelectedPage(selectedPage)
	}
}
```

---

## Summary (My Approach)

- Create a sealed class to store constants key name and data store name. 
- Create DataStore Helper Function.
- Create Datastore.
- Create Repository.
- Create DefaultValuesObject

### DataStore Constants Keys Name 

```kotlin

package com.visionforgestudio.drinkup.global.constants

sealed class DataStoreConstants(val name: String) {
	// datastore names
	object UserPreferences : DataStoreConstants("user_preferences")
	object UserData : DataStoreConstants("user_data")
	object AppData : DataStoreConstants("app_data")
	
	// User Data key names
	object UserWeight : DataStoreConstants("user_weight")
	object UserGender : DataStoreConstants("user_gender")
	object UnitSystem : DataStoreConstants("unit_system")
	object GlassSize : DataStoreConstants("glass_size")
	object DailyIntake : DataStoreConstants("daily_intake")
	object ActivityLevel : DataStoreConstants("activity_level")
	object ActivityHoursStart : DataStoreConstants("activity_hours_start")
	object ActivityHoursEnd : DataStoreConstants("activity_hours_end")
	object IsAutoIntervalOn : DataStoreConstants("is_auto_interval_on")
	object AutoIntervalTime : DataStoreConstants("auto_interval_time")
	object CustomIntervalTime : DataStoreConstants("custom_interval_time")
	object NextReminderTime : DataStoreConstants("next_reminder_time")
	
	// User Preferences key names
	object IsNotificationAllowed : DataStoreConstants("is_notification_allowed")
	object IsAlarmAllowed : DataStoreConstants("is_alarm_allowed")
	object NotificationType : DataStoreConstants("notification_type")
	object IsPremiumUser : DataStoreConstants("is_premium_user")
	object SubscriptionTYpe : DataStoreConstants("subscription_type")
	object SelectedTheme : DataStoreConstants("selected_theme")
	
	// App Data key names
	object IsFirstTimeUser : DataStoreConstants("is_first_time_user")
	object IsHomeTutorialShown : DataStoreConstants("is_home_tutorial_shown")
	object IsNotificationDialogShown : DataStoreConstants("is_notification_dialog_shown")
	object SelectedPage : DataStoreConstants("selected_page")
	
}
```

---

### DataStore Helper Function

```kotlin
package com.visionforgestudio.drinkup.domain.helper

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreHelper(
	private val dataStore: DataStore<Preferences>
) {
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
	
}
```

---

### DataStore

```kotlin
package com.visionforgestudio.drinkup.data.datastore.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.visionforgestudio.drinkup.domain.helper.DataStoreHelper
import com.visionforgestudio.drinkup.global.constants.DataStoreConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

val Context.appDataDataStore by preferencesDataStore(DataStoreConstants.AppData.name)

class AppData @Inject constructor(@ApplicationContext private val context: Context) {
	
	private val dataStore = context.appDataDataStore
	
	companion object {
		val IS_FIRST_TIME_USER_KEY =
			booleanPreferencesKey(DataStoreConstants.IsFirstTimeUser.name)
		val IS_HOME_TUTORIAL_SHOWN_KEY =
			booleanPreferencesKey(DataStoreConstants.IsHomeTutorialShown.name)
		val IS_NOTIFICATION_DIALOG_SHOWN_KEY =
			booleanPreferencesKey(DataStoreConstants.IsNotificationDialogShown.name)
		val SELECTED_PAGE_KEY = stringPreferencesKey(DataStoreConstants.SelectedPage.name)
	}
	
	val dataStoreHelper = DataStoreHelper(dataStore)
	
	val isFirstTimeUserFlow: Flow<Boolean?> = dataStoreHelper.getBooleanPreferences(IS_FIRST_TIME_USER_KEY)

	val isHomeTutorialShownFlow: Flow<Boolean?> = dataStoreHelper.getBooleanPreferences(IS_HOME_TUTORIAL_SHOWN_KEY)
	
	val isNotificationDialogShownFlow: Flow<Boolean?> = dataStoreHelper.getBooleanPreferences(IS_NOTIFICATION_DIALOG_SHOWN_KEY)
	
	val selectedPageFlow: Flow<String?> = dataStoreHelper.getStringPreferences(SELECTED_PAGE_KEY)
	
	suspend fun setIsFirstTimeUser(isFirstTimeUser: Boolean) {
		dataStoreHelper.setBooleanPreferences(IS_FIRST_TIME_USER_KEY, isFirstTimeUser)
	}
	
	suspend fun setIsHomeTutorialShown(isHomeTutorialShown: Boolean) {
		dataStoreHelper.setBooleanPreferences(IS_HOME_TUTORIAL_SHOWN_KEY, isHomeTutorialShown)
	}
	
	suspend fun setIsNotificationDialogShown(isNotificationDialogShown: Boolean) {
		dataStoreHelper.setBooleanPreferences(IS_NOTIFICATION_DIALOG_SHOWN_KEY, isNotificationDialogShown)
	}
	
	suspend fun setSelectedPage(selectedPage: String) {
		dataStoreHelper.setStringPreferences(SELECTED_PAGE_KEY, selectedPage)
	}
	
}
```

---

### Default values

```kotlin
package com.visionforgestudio.drinkup.global.defaults

import com.visionforgestudio.drinkup.global.constants.AppScreens

object AppDataDefaults {
	const val IS_FIRST_TIME_USER: Boolean = true
	const val IS_HOME_TUTORIAL_SHOWN: Boolean = false
	const val IS_NOTIFICATION_DIALOG_SHOWN: Boolean = false
	val SELECTED_PAGE: String = AppScreens.HomeScreen.route
}
```

---

### Repository 

```kotlin
import com.visionforgestudio.drinkup.data.datastore.store.AppData
import com.visionforgestudio.drinkup.global.defaults.AppDataDefaults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppDataRepository @Inject constructor(private val appData: AppData) {
	
	private val scope = CoroutineScope(Dispatchers.IO)
	
	init {
		scope.launch {
			initializeDefaults()
		}
	}
	
	suspend fun initializeDefaults(){
		if (appData.isFirstTimeUserFlow.first() == null) {
			setIsFirstTimeUser(AppDataDefaults.IS_FIRST_TIME_USER)
		}
		if (appData.isHomeTutorialShownFlow.first() == null) {
			setIsHomeTutorialShown(AppDataDefaults.IS_HOME_TUTORIAL_SHOWN)
		}
		if (appData.isNotificationDialogShownFlow.first() == null) {
			setIsNotificationDialogShown(AppDataDefaults.IS_NOTIFICATION_DIALOG_SHOWN)
		}
		if (appData.selectedPageFlow.first() == null) {
			setSelectedPage(AppDataDefaults.SELECTED_PAGE)
		}
	}

	val isFirstTimeUserFlow: Flow<Boolean> = appData.isFirstTimeUserFlow.map {
		it ?: AppDataDefaults.IS_FIRST_TIME_USER
	}
	
	val isHomeTutorialShownFlow: Flow<Boolean> = appData.isHomeTutorialShownFlow.map {
		it ?: AppDataDefaults.IS_HOME_TUTORIAL_SHOWN
	}
	
	val isNotificationDialogShownFlow: Flow<Boolean> = appData.isNotificationDialogShownFlow.map {
		it ?: AppDataDefaults.IS_NOTIFICATION_DIALOG_SHOWN
	}
	
	val selectedPageFlow: Flow<String> = appData.selectedPageFlow.map {
		it ?: AppDataDefaults.SELECTED_PAGE
	}
	
	suspend fun setIsFirstTimeUser(isFirstTimeUser: Boolean) {
		appData.setIsFirstTimeUser(isFirstTimeUser)
	}
	
	suspend fun setIsHomeTutorialShown(isHomeTutorialShown: Boolean) {
		appData.setIsHomeTutorialShown(isHomeTutorialShown)
	}

	suspend fun setIsNotificationDialogShown(isNotificationDialogShown: Boolean) {
		appData.setIsNotificationDialogShown(isNotificationDialogShown)
	}
	
	suspend fun setSelectedPage(selectedPage: String) {
		appData.setSelectedPage(selectedPage)
	}
}
```
---

### Initializing (Optional)

```kotlin
package com.visionforgestudio.drinkup

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.visionforgestudio.drinkup.data.datastore.repository.AppDataRepository
import com.visionforgestudio.drinkup.data.datastore.repository.UserDataRepository
import com.visionforgestudio.drinkup.data.datastore.repository.UserPreferencesRepository
import com.visionforgestudio.drinkup.di.entrypoint.DrinkUpInitializerEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DrinkUpInitializer : Initializer<Unit> {
	
	override fun create(context: Context) {
		// Access dependencies via Hilt's EntryPointAccessors
		val app = context.applicationContext as Application
		val entryPoint = EntryPointAccessors.fromApplication(app, DrinkUpInitializerEntryPoint::class.java)
		
		// Access repositories from the entry point
		val userDataRepository = entryPoint.userDataRepository()
		val appDataRepository = entryPoint.appDataRepository()
		val userPreferencesRepository = entryPoint.userPreferencesRepository()
		
		// Initialize repositories
		CoroutineScope(Dispatchers.IO).launch {
			userDataRepository.initializeDefaults()
			appDataRepository.initializeDefaults()
			userPreferencesRepository.initializeDefaults()
		}
	}
	
	override fun dependencies(): List<Class<out Initializer<*>>> {
		return emptyList()
	}
}
```

```kotlin
package com.visionforgestudio.drinkup.di.entrypoint

import com.visionforgestudio.drinkup.data.datastore.repository.AppDataRepository
import com.visionforgestudio.drinkup.data.datastore.repository.UserDataRepository
import com.visionforgestudio.drinkup.data.datastore.repository.UserPreferencesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DrinkUpInitializerEntryPoint {
	fun userDataRepository(): UserDataRepository
	fun appDataRepository(): AppDataRepository
	fun userPreferencesRepository(): UserPreferencesRepository
}
```

```xml
<provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false">
            <meta-data
                android:name=".DrinkUpInitializer"
                android:value="androidx.startup" />
        </provider>
```