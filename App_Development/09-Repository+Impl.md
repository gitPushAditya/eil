# Repository + Implementation

## Interface

```kt
interface ActiveHabitRepo {
    suspend fun insertActiveHabit(habit: ActiveHabit): Long
    suspend fun insertActiveHabits(habits: List<ActiveHabit>)
    suspend fun updateActiveHabit(habit: ActiveHabit)
    suspend fun deleteActiveHabit(habit: ActiveHabit)
    suspend fun deleteHabitById(habitId: String)
    fun getHabitById(habitId: String): Flow<ActiveHabit?>
    suspend fun getHabitByIdOnce(habitId: String): ActiveHabit?
    fun getAllHabits(): Flow<List<ActiveHabit>>
    fun getHabitsByState(state: HabitState): Flow<List<ActiveHabit>>
    fun getHabitsByCategory(category: LifeAreaTopic): Flow<List<ActiveHabit>>
    suspend fun updateHabitState(habitId: String, newState: HabitState)
    suspend fun updateHabitPosition(habitId: String, position: Int)
    fun getHabitsForDate(date: LocalDate, state: HabitState = HabitState.ACTIVE): Flow<List<ActiveHabit>>
}
```

---

## Implementation

```kt

@Singleton
class ActiveHabitRepoImpl @Inject constructor(
    private val dao: ActiveHabitDao,
    @ApplicationContext private val context: Context
) : ActiveHabitRepo {
    
    override suspend fun insertActiveHabit(habit: ActiveHabit): Long {
        val id = dao.insertHabit(habit)
        AlarmUseCase.scheduleHabitReminders(context, habit)
        return id
    }

    override suspend fun insertActiveHabits(habits: List<ActiveHabit>) {
        dao.insertHabits(habits)
        habits.filter { it.habitState == HabitState.ACTIVE }.forEach {
            AlarmUseCase.scheduleHabitReminders(context, it)
        }
    }


    override suspend fun updateActiveHabit(habit: ActiveHabit) {
        dao.updateHabit(habit)

        // Cancel old alarms (in case reminders were changed)
        AlarmUseCase.cancelHabitReminders(context, habit)

        // Reschedule updated reminders
        AlarmUseCase.scheduleHabitReminders(context, habit)
    }


    override suspend fun deleteActiveHabit(habit: ActiveHabit) {
        AlarmUseCase.cancelHabitReminders(context, habit)
        dao.deleteHabit(habit)
    }

    override suspend fun deleteHabitById(habitId: String) {
        val habit = dao.getHabitByIdOnce(habitId) // You'll need a suspend version for direct access
        if (habit != null) {
            AlarmUseCase.cancelHabitReminders(context, habit)
        }
        dao.deleteHabitById(habitId)
    }


    override fun getHabitById(habitId: String): Flow<ActiveHabit?> {
        return dao.getHabitById(habitId)
    }

    override suspend fun getHabitByIdOnce(habitId: String): ActiveHabit? {
        return dao.getHabitByIdOnce(habitId)
    }

    override fun getAllHabits(): Flow<List<ActiveHabit>> {
        return dao.getAllHabits()
    }

    override fun getHabitsByState(state: HabitState): Flow<List<ActiveHabit>> {
        return dao.getHabitsByState(state)
    }

    override fun getHabitsByCategory(category: LifeAreaTopic): Flow<List<ActiveHabit>> {
        return dao.getHabitsByCategory(category)
    }

    override suspend fun updateHabitState(habitId: String, newState: HabitState) {
        val habit = dao.getHabitByIdOnce(habitId) ?: return

        dao.updateHabitState(habitId, newState)

        if (newState == HabitState.ACTIVE) {
            AlarmUseCase.scheduleHabitReminders(context, habit.copy(habitState = HabitState.ACTIVE))
        } else {
            AlarmUseCase.cancelHabitReminders(context, habit)
        }
    }


    override suspend fun updateHabitPosition(habitId: String, position: Int) {
        dao.updateHabitPosition(habitId, position)
    }

    override fun getHabitsForDate(date: LocalDate, state: HabitState): Flow<List<ActiveHabit>> {
        return dao.getHabitsForDate(date, state)
    }
}

```