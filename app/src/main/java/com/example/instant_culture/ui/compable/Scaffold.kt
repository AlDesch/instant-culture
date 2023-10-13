package com.example.instant_culture.ui.compable

import WaitingScreen
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.*
import com.example.instant_culture.R
import com.example.instant_culture.model.Screens
import com.example.instant_culture.services.JsonParser

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
fun ScaffoldComposable(applicationContext: Context) {
    val navigationController = rememberNavController()
    val backStackEntry by navigationController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(backStackEntry?.destination?.route ?: Screens.Home.title)
    val questionOrder = remember { mutableIntStateOf(0) }
    val questionDifficulty = remember { mutableIntStateOf(0) }

    NavHost(navController = navigationController, startDestination = Screens.Home.name) {
        composable(route = Screens.Home.name) {
            FadeTransition(visible = currentScreen == Screens.Home) {
                BeginView(
                    onClickPlay = {
                        navigationController.navigate(route = Screens.Confirmation.name)
                    },
                )
            }
        }
        composable(route = Screens.Confirmation.name) {
            FadeTransition(visible = currentScreen == Screens.Confirmation) {
                ConfirmationView(
                    onClickReday = {
                        navigationController.navigate(route = Screens.Waiting.name)
                    },
                    onClickHome = {
                        navigationController.navigate(route = Screens.Home.name)
                    }
                )
            }
        }
        composable(route = Screens.Question.name) {
            FadeTransition(visible = currentScreen == Screens.Question) {
                QuestionView(
                    onClick = {
                        navigationController.navigate(route = Screens.Home.name)
                    },
                    onClickNext = {
                        questionOrder.intValue++
                        if (JsonParser().getQuestionsFromJson(
                                applicationContext,
                                questionDifficulty.intValue
                            )?.size == questionOrder.intValue
                        ) {
                            questionDifficulty.intValue++
                            questionOrder.intValue = 0
                        }
                        println(questionDifficulty.intValue)
                        if (questionDifficulty.intValue == 3) {
                            questionDifficulty.intValue = 0
                            questionOrder.intValue = 0
                            navigationController.navigate(route = Screens.Home.name)
                            return@QuestionView
                        }
                        navigationController.navigate(route = Screens.Question.name)
                    },
                    onClickHome = {
                        questionDifficulty.intValue = 0
                        questionOrder.intValue = 0
                        navigationController.navigate(route = Screens.Home.name)
                    },
                    questions = JsonParser().getQuestionsFromJson(
                        applicationContext,
                        questionDifficulty.intValue
                    ),
                    questionOrder = questionOrder.intValue,
                    background = when (questionDifficulty.intValue) {
                        0 -> R.drawable.vertvertbackground
                        1 -> R.drawable.vvbackground
                        2 -> R.drawable.bbackground
                        else -> R.drawable.vertvertbackground
                    },
                    difficulty = questionDifficulty.intValue,
                    rotatingSpeed = when (questionDifficulty.intValue) {
                        0 -> 30000
                        1 -> 10000
                        2 -> 1000
                        else -> 30000
                    },
                )
            }
        }
        composable(route = Screens.Waiting.name) {
            WaitingScreen(navigationController)
        }
    }
}


