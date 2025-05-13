# Navigation

## Nav Destinations

```kt
sealed class NavDestinations(val route: String) {
    // For normal navigation 
    data object Intro: NavDestinations("intro")

    //For passing variable
    data object HabitInfo: NavDestinations("habit_info/{habitId}") {
        fun createRoute(habitId: String): String {
            return "habit_info/$habitId"
        }
    }

    // For passing nullable variable
    data object AddHabit: NavDestinations("add_habit?habitId={habitId}") {
        fun createRoute(): String {
            return "add_habit"
        }
        fun createRouteWithEdit(habitId: String): String {
            Log.d("NavDestinations", "Navigating to AddHabit with habitId: $habitId") // Log the habitId
            return "add_habit?habitId=$habitId"
        }
    }
}
```

---

## App Navigation

```kt

@Composable
fun AppNavigation(
    viewModel: AppNavigationViewModel = hiltViewModel(),
){
    val navController = rememberNavController()

    val isFirstTimeUser by viewModel.isFirstTimeUser.collectAsState()

    val startDestination = if (isFirstTimeUser){
        NavDestinations.Intro.route
    } else {
        NavDestinations.HabitToday.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        // For No  Variables

        composable(NavDestinations.Intro.route) {
            IntroScreen(navController)
        }

        // For passing variable

        composable(
            route = NavDestinations.HabitInfo.route,
            arguments = listOf(navArgument("habitId") { type = NavType.StringType })
        ) { backStackEntry ->
            val habitId = backStackEntry.arguments?.getString("habitId") ?: ""
            HabitInfoScreen(navController, habitId)
        }

        // For Passing Nullable Variable

        composable(
            route = "add_habit?habitId={habitId}",
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val habitId = backStackEntry.arguments?.getString("habitId")
            AddHabitScreen(navController, habitId, snackbarHostState)
        }
    }

}

```

---