import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.*
import com.example.instant_culture.model.Screens
import com.example.instant_culture.ui.compable.BeginView
import com.example.instant_culture.ui.compable.ConfirmationView
import com.example.instant_culture.ui.compable.QuestionView

@Composable
fun FadeTransition(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        content()
    }
}

@Composable
fun ScaffoldComposable() {
    val navigationController = rememberNavController()
    val backStackEntry by navigationController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(backStackEntry?.destination?.route ?: Screens.Home.title)

    NavHost(navController = navigationController, startDestination = Screens.Home.name) {
        composable(route = Screens.Home.name) {
            FadeTransition(visible = currentScreen == Screens.Home) {
                BeginView(
                    onClick = {
                        println("go to question")
                        navigationController.navigate(route = Screens.Confirmation.name)
                    }
                )
            }
        }
        composable(route = Screens.Confirmation.name) {
            FadeTransition(visible = currentScreen == Screens.Confirmation) {
                ConfirmationView(
                    onClick = {
                        println("go to confirmation")
                        navigationController.navigate(route = Screens.Waiting.name)
                    }
                )
            }
        }
        composable(route = Screens.Question.name) {
            FadeTransition(visible = currentScreen == Screens.Question) {
                QuestionView(
                    onClick = {
                        println("go to question")
                        navigationController.navigate(route = Screens.Home.name)
                    }
                )
            }
        }
        composable(route = Screens.Waiting.name) {
            WaitingScreen(navigationController)
           // WaitingScreenView()
        }
    }
}


