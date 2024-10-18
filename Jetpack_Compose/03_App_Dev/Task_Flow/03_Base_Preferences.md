# Base Preferences

## Add TaskFlowDataConstants

global -> constants -> TaskFlowDataConstants

```Kotlin
package com.visionforgestudio.taskflow.global.constants

class TaskFlowDataConstants {
	companion object{
		// data store names
		const val USER_PREFERENCES = "user_preferences"
		const val USER_DATA = "user_data"
		const val APP_DATA = "app_data"

		// User preferences key tags
		const val SELECTED_THEME = "selected_theme"
		const val SELECTED_NOTIFICATION_TYPE = "selected_notification_type"
		const val SELECTED_NOTIFICATION_TUNE = "selected_notification_tune"
		const val START_OF_DAY = "start_of_day"
		const val END_OF_DAY = "end_of_day"
		const val START_OF_DAY_NOTIFICATION = "start_of_day_notification"
		const val START_OF_DAY_NOTIFICATION_TYPE = "start_of_day_notification_type"
		const val START_OF_DAY_NOTIFICATION_TUNE = "start_of_day_notification_tune"
		const val END_OF_DAY_NOTIFICATION = "end_of_day_notification"
		const val END_OF_DAY_NOTIFICATION_TYPE = "end_of_day_notification_type"
		const val END_OF_DAY_NOTIFICATION_TUNE = "end_of_day_notification_tune"
		
		// User data key tags
		const val USER_ID = "user_id"
		const val USER_NAME = "user_name"
		const val USER_EMAIL = "user_email"
		const val IS_PREMIUM_USER = "is_premium_user"
		const val IS_FIRST_TIME_USER = "is_first_time_user"
		const val IS_LOGGED_IN = "is_logged_in"
		
		// App Data key tags
		const val SELECTED_PAGE = "selected_page"
		const val CATEGORIES_LIST = "categories_list"
	}
}
```

--- 

## Add UserPreferences

data -> datastore -> UserPreferences

- Selected Theme
- Selected Notification Type
- Selected Notification Tune
- Start of Day
- End of Day
- Start of Day Notification
- Start of Day Notification Type
- Start of Day Notification Tune
- End of Day Notification
- End of Day Notification Type
- End of Day Notification Tune

```Kotlin
package com.visionforgestudio.taskflow.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.visionforgestudio.taskflow.global.constants.TaskFlowDataConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.userPreferencesDatastore by preferencesDataStore(TaskFlowDataConstants.USER_PREFERENCES)

class UserPreferences(private val context: Context) {
	
	private val dataStore = context.userPreferencesDatastore
	
	companion object {
		val SELECTED_THEME_KEY = stringPreferencesKey(TaskFlowDataConstants.SELECTED_THEME)
		val SELECTED_NOTIFICATION_TYPE_KEY =
			stringPreferencesKey(TaskFlowDataConstants.SELECTED_NOTIFICATION_TYPE)
		val SELECTED_NOTIFICATION_TUNE_KEY =
			stringPreferencesKey(TaskFlowDataConstants.SELECTED_NOTIFICATION_TUNE)
		val START_OF_DAY_KEY = stringPreferencesKey(TaskFlowDataConstants.START_OF_DAY)
		val END_OF_DAY_KEY = stringPreferencesKey(TaskFlowDataConstants.END_OF_DAY)
		val START_OF_DAY_NOTIFICATION_KEY =
			booleanPreferencesKey(TaskFlowDataConstants.START_OF_DAY_NOTIFICATION)
		val START_OF_DAY_NOTIFICATION_TYPE_KEY =
			stringPreferencesKey(TaskFlowDataConstants.START_OF_DAY_NOTIFICATION_TYPE)
		val START_OF_DAY_NOTIFICATION_TUNE_KEY =
			stringPreferencesKey(TaskFlowDataConstants.START_OF_DAY_NOTIFICATION_TUNE)
		val END_OF_DAY_NOTIFICATION_KEY =
			booleanPreferencesKey(TaskFlowDataConstants.END_OF_DAY_NOTIFICATION)
		val END_OF_DAY_NOTIFICATION_TUNE_KEY =
			stringPreferencesKey(TaskFlowDataConstants.END_OF_DAY_NOTIFICATION_TUNE)
		val END_OF_DAY_NOTIFICATION_TYPE_KEY =
			stringPreferencesKey(TaskFlowDataConstants.END_OF_DAY_NOTIFICATION_TYPE)
	}
	
	private fun getStringPreference(key: Preferences.Key<String>): Flow<String?> {
		return dataStore.data.catch { exception ->
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	private fun getBooleanPreference(key: Preferences.Key<Boolean>): Flow<Boolean?> {
		return dataStore.data.catch { exception ->
			emit(emptyPreferences())
		}.map { preferences ->
			preferences[key]
		}
	}
	
	private suspend fun setStringPreference(key: Preferences.Key<String>, value: String) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	private suspend fun setBooleanPreference(key: Preferences.Key<Boolean>, value: Boolean) {
		dataStore.edit { preferences ->
			preferences[key] = value
		}
	}
	
	// Get all Values
	
	val selectedThemeFlow: Flow<String?> = getStringPreference(SELECTED_THEME_KEY)
	val selectedNotificationTypeFlow: Flow<String?> =
		getStringPreference(SELECTED_NOTIFICATION_TYPE_KEY)
	val selectedNotificationTuneFlow: Flow<String?> =
		getStringPreference(SELECTED_NOTIFICATION_TUNE_KEY)
	val startOfDayFlow: Flow<String?> = getStringPreference(START_OF_DAY_KEY)
	val endOfDayFlow: Flow<String?> = getStringPreference(END_OF_DAY_KEY)
	val startOfDayNotificationFlow: Flow<Boolean?> =
		getBooleanPreference(START_OF_DAY_NOTIFICATION_KEY)
	val startOfDayNotificationTypeFlow: Flow<String?> =
		getStringPreference(START_OF_DAY_NOTIFICATION_TYPE_KEY)
	val startOfDayNotificationTuneFlow: Flow<String?> =
		getStringPreference(START_OF_DAY_NOTIFICATION_TUNE_KEY)
	val endOfDayNotificationFlow: Flow<Boolean?> = getBooleanPreference(END_OF_DAY_NOTIFICATION_KEY)
	val endOfDayNotificationTuneFlow: Flow<String?> =
		getStringPreference(END_OF_DAY_NOTIFICATION_TUNE_KEY)
	val endOfDayNotificationTypeFlow: Flow<String?> =
		getStringPreference(END_OF_DAY_NOTIFICATION_TYPE_KEY)
	
	
	// Set All Values
	
	suspend fun setSelectedTheme(theme: String) {
		setStringPreference(SELECTED_THEME_KEY, theme)
	}
	
	suspend fun setSelectedNotificationType(type: String) {
		setStringPreference(SELECTED_NOTIFICATION_TYPE_KEY, type)
	}
	
	suspend fun setSelectedNotificationTune(tune: String) {
		setStringPreference(SELECTED_NOTIFICATION_TUNE_KEY, tune)
	}
	
	suspend fun setStartOfDay(startOfDay: String) {
		setStringPreference(START_OF_DAY_KEY, startOfDay)
	}
	
	suspend fun setEndOfDay(endOfDay: String) {
		setStringPreference(END_OF_DAY_KEY, endOfDay)
	}
	
	suspend fun setStartOfDayNotification(startOfDayNotification: Boolean) {
		setBooleanPreference(START_OF_DAY_NOTIFICATION_KEY, startOfDayNotification)
	}
	
	suspend fun setStartOfDayNotificationType(startOfDayNotificationType: String) {
		setStringPreference(START_OF_DAY_NOTIFICATION_TYPE_KEY, startOfDayNotificationType)
	}
	
	suspend fun setStartOfDayNotificationTune(startOfDayNotificationTune: String) {
		setStringPreference(START_OF_DAY_NOTIFICATION_TUNE_KEY, startOfDayNotificationTune)
	}
	
	suspend fun setEndOfDayNotification(endOfDayNotification: Boolean) {
		setBooleanPreference(END_OF_DAY_NOTIFICATION_KEY, endOfDayNotification)
	}
	
	suspend fun setEndOfDayNotificationType(endOfDayNotificationType: String) {
		setStringPreference(END_OF_DAY_NOTIFICATION_TYPE_KEY, endOfDayNotificationType)
	}
	
	suspend fun setEndOfDayNotificationTune(endOfDayNotificationTune: String) {
		setStringPreference(END_OF_DAY_NOTIFICATION_TUNE_KEY, endOfDayNotificationTune)
	}
	
}
```

---

