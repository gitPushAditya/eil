# Database

## Entity

```kt
@Entity(tableName = "habits")
data class ActiveHabit(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val category: LifeAreaTopic,
    val icon: HabitIcon,
    val difficulty: HabitDifficulty = HabitDifficulty.MEDIUM,
    val frequencyType: FrequencyType = FrequencyType.DAILY,
    val frequencyDays: Set<DayOfWeek> = emptySet(),
    val habitState: HabitState = HabitState.ACTIVE,
    val targetUnit: TargetUnit = TargetUnit.COUNT,
    val targetCount: Int = 1,
    val targetTime: Int = 0,  // in minutes
    val reminders: List<Reminder> = emptyList(),
    val color: Int = 0,
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate? = null,  // null if not archived
    val completions: List<HabitCompletion> = emptyList(),
    val pointsEarned: Int = 0,
    val todos: List<HabitTodo> = emptyList(),
    val sortPosition: Int = 0  // For custom sorting
)
```

--- 

## DAO

```kt

@Dao
interface ActiveHabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: ActiveHabit): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabits(habits: List<ActiveHabit>)

    @Update
    suspend fun updateHabit(habit: ActiveHabit)

    @Delete
    suspend fun deleteHabit(habit: ActiveHabit)

    @Query("DELETE FROM habits WHERE id = :habitId")
    suspend fun deleteHabitById(habitId: String)

    @Query("SELECT * FROM habits WHERE id = :habitId")
    fun getHabitById(habitId: String): Flow<ActiveHabit?>

    @Query("SELECT * FROM habits ORDER BY sortPosition ASC")
    fun getAllHabits(): Flow<List<ActiveHabit>>

    @Query("SELECT * FROM habits WHERE habitState = :state ORDER BY sortPosition ASC")
    fun getHabitsByState(state: HabitState): Flow<List<ActiveHabit>>

    @Query("SELECT * FROM habits WHERE id = :habitId LIMIT 1")
    suspend fun getHabitByIdOnce(habitId: String): ActiveHabit?


    @Query("SELECT * FROM habits WHERE category = :category ORDER BY sortPosition ASC")
    fun getHabitsByCategory(category: LifeAreaTopic): Flow<List<ActiveHabit>>

    @Query("UPDATE habits SET habitState = :newState WHERE id = :habitId")
    suspend fun updateHabitState(habitId: String, newState: HabitState)

    @Query("UPDATE habits SET sortPosition = :position WHERE id = :habitId")
    suspend fun updateHabitPosition(habitId: String, position: Int)
    
    @Query("SELECT * FROM habits WHERE startDate <= :date AND (endDate IS NULL OR endDate >= :date) AND habitState = :state ORDER BY sortPosition ASC")
    fun getHabitsForDate(date: LocalDate, state: HabitState = HabitState.ACTIVE): Flow<List<ActiveHabit>>
}
```

---

## Convertors

```kt

class Convertors {
    // Custom Gson instance with LocalDate type adapter
    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        .create()

    // LocalDate adapter for Gson
    private class LocalDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        override fun serialize(src: LocalDate, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }

        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDate {
            return LocalDate.parse(json.asString, DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }

    // LocalDate converters
    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE) }
    }

    // LocalTime converters
    @TypeConverter
    fun fromLocalTime(value: LocalTime?): String? {
        return value?.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }

    @TypeConverter
    fun toLocalTime(value: String?): LocalTime? {
        return value?.let { LocalTime.parse(it, DateTimeFormatter.ISO_LOCAL_TIME) }
    }

    // DayOfWeek Set converters
    @TypeConverter
    fun fromDayOfWeekSet(days: Set<DayOfWeek>): String {
        val dayValues = days.map { it.value }
        return gson.toJson(dayValues)
    }

    @TypeConverter
    fun toDayOfWeekSet(value: String): Set<DayOfWeek> {
        val type = TypeToken.getParameterized(List::class.java, Int::class.javaObjectType).type
        val dayValues: List<Int> = gson.fromJson(value, type)
        return dayValues.map { DayOfWeek.of(it) }.toSet()
    }

    // Reminder List converters
    @TypeConverter
    fun fromReminderList(reminders: List<Reminder>): String {
        return GsonBuilder()
            .registerTypeAdapter(LocalTime::class.java, object : JsonSerializer<LocalTime> {
                override fun serialize(
                    src: LocalTime,
                    typeOfSrc: Type,
                    context: JsonSerializationContext
                ): JsonElement {
                    return JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME))
                }
            })
            .create()
            .toJson(reminders)
    }

    @TypeConverter
    fun toReminderList(value: String): List<Reminder> {
        return GsonBuilder()
            .registerTypeAdapter(LocalTime::class.java, object : JsonDeserializer<LocalTime> {
                override fun deserialize(
                    json: JsonElement,
                    typeOfT: Type,
                    context: JsonDeserializationContext
                ): LocalTime {
                    return LocalTime.parse(json.asString, DateTimeFormatter.ISO_LOCAL_TIME)
                }
            })
            .create()
            .fromJson(value, object : TypeToken<List<Reminder>>() {}.type)
    }

    // HabitCompletion List converters
    @TypeConverter
    fun fromCompletionList(completions: List<HabitCompletion>): String {
        return gson.toJson(completions)
    }

    @TypeConverter
    fun toCompletionList(value: String): List<HabitCompletion> {
        val type = object : TypeToken<List<HabitCompletion>>() {}.type
        return gson.fromJson(value, type)
    }

    // HabitTodo List converters
    @TypeConverter
    fun fromTodoList(todos: List<HabitTodo>): String {
        return gson.toJson(todos)
    }

    @TypeConverter
    fun toTodoList(value: String): List<HabitTodo> {
        val type = object : TypeToken<List<HabitTodo>>() {}.type
        return gson.fromJson(value, type)
    }

    // Enum converters
    @TypeConverter
    fun fromFrequencyType(type: FrequencyType): String {
        return type.name
    }

    @TypeConverter
    fun toFrequencyType(value: String): FrequencyType {
        return FrequencyType.valueOf(value)
    }

    @TypeConverter
    fun fromHabitDifficulty(difficulty: HabitDifficulty): String {
        return difficulty.name
    }

    @TypeConverter
    fun toHabitDifficulty(value: String): HabitDifficulty {
        return HabitDifficulty.valueOf(value)
    }

    @TypeConverter
    fun fromHabitState(state: HabitState): String {
        return state.name
    }

    @TypeConverter
    fun toHabitState(value: String): HabitState {
        return HabitState.valueOf(value)
    }

    @TypeConverter
    fun fromTargetUnit(unit: TargetUnit): String {
        return unit.name
    }

    @TypeConverter
    fun toTargetUnit(value: String): TargetUnit {
        return TargetUnit.valueOf(value)
    }

    @TypeConverter
    fun fromLifeAreaTopic(topic: LifeAreaTopic): String {
        return topic.id
    }

    @TypeConverter
    fun toLifeAreaTopic(value: String): LifeAreaTopic {
        return LifeAreaTopics.getAllLifeAreaTopics().firstOrNull { it.id == value }
            ?: LifeAreaTopics.getAllLifeAreaTopics().first()
    }

    // ImageVector converters - Note: This is simplified and you may need a more robust solution
    @TypeConverter
    fun fromHabitIcon(icon: HabitIcon): String {
        return icon.name
    }

    @TypeConverter
    fun fromHabitIcon(value: String): HabitIcon {
        return HabitIcons.allIcons.firstOrNull { it.name == value }
            ?: HabitIcons.allIcons.first()

    }
}

```

---

## Database

```kt
@Database(
    entities = [ActiveHabit::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Convertors::class)
abstract class ActiveHabitDatabase: RoomDatabase(){
    abstract fun activeHabitDao(): ActiveHabitDao
}

```

---

## Database Module

```kt

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ActiveHabitDatabase {
        return Room.databaseBuilder(
            context,
            ActiveHabitDatabase::class.java,
            "active_habit_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideActiveHabitDao(database: ActiveHabitDatabase): ActiveHabitDao {
        return database.activeHabitDao()
    }
}
```

---