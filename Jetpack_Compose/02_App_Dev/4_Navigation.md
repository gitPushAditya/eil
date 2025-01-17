# Navigation

## Defining Screens Variables

To navigate to ech screen, we are going to need a string route for each screen. To avoid any mistakes, we will define
these variables in a separate AppScreens sealed class.

```kotlin
sealed class AppScreens(val route: String) {
    data object Intro : AppScreens("intro")
    data object Home : AppScreens("home")
    data object Timeline : AppScreens("timeline")
    data object Statistics : AppScreens("statistics")
    data object Settings : AppScreens("settings")
    data object Profile : AppScreens("profile")
    data object Alarm : AppScreens("alarm")
    data object Database : AppScreens("database")
    data object Premium : AppScreens("premium")
    data object Contact : AppScreens("contact")
}
```

Now we can access routes by writing - `AppScreens.Intro.route`

--- 

## NavController

NavController is responsible remembering which screen is currently visible and for navigating to different screens.

```kotlin

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.visionforgestudio.drinkup.presentation.screens.home.HomeScreen
import com.visionforgestudio.drinkup.presentation.screens.intro.IntroScreen
import com.visionforgestudio.drinkup.presentation.screens.premium.PremiumScreen
import com.visionforgestudio.drinkup.presentation.screens.settings.SettingsScreen
import com.visionforgestudio.drinkup.presentation.screens.settings.subscreens.alarms.AlarmsScreen
import com.visionforgestudio.drinkup.presentation.screens.settings.subscreens.contact.ContactScreen
import com.visionforgestudio.drinkup.presentation.screens.settings.subscreens.database.DatabaseScreen
import com.visionforgestudio.drinkup.presentation.screens.settings.subscreens.profile.ProfileScreen
import com.visionforgestudio.drinkup.presentation.screens.statistics.StatisticsScreen
import com.visionforgestudio.drinkup.presentation.screens.timeline.TimelineScreen

@Composable
fun NavController(

) {
    val navController = rememberNavController()

    // TODO: Define first screen based on first time launch or not

    val startDestination = AppScreens.Intro.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppScreens.Intro.route) { IntroScreen(navController) }
        composable(AppScreens.Home.route) { HomeScreen(navController) }
        composable(AppScreens.Timeline.route) { TimelineScreen(navController) }
        composable(AppScreens.Statistics.route) { StatisticsScreen(navController) }
        composable(AppScreens.Settings.route) { SettingsScreen(navController) }
        composable(AppScreens.Profile.route) { ProfileScreen(navController) }
        composable(AppScreens.Alarm.route) { AlarmsScreen(navController) }
        composable(AppScreens.Database.route) { DatabaseScreen(navController) }
        composable(AppScreens.Premium.route) { PremiumScreen(navController) }
        composable(AppScreens.Contact.route) { ContactScreen(navController) }
    }

}

```