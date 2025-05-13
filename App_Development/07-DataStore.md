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

---