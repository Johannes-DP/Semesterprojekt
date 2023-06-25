package com.example.semesterprojekt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semesterprojekt.screens.HomeScreen
import com.example.semesterprojekt.screens.Registration
import com.example.semesterprojekt.screens.Screen
import androidx.navigation.navArgument
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.screens.*
import com.example.semesterprojekt.viewmodels.RegistrationViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val registrationViewModel = RegistrationViewModel(repository = ListRepositoryImpl())

    NavHost(navController = navController, startDestination = Screen.Registration.route) {

        composable(route = Screen.MainScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Registration.route) {
            Registration(navController = navController, viewModel = registrationViewModel)
        }

        composable(route = Screen.AddGameScreen.route) {
            AddGameScreen(navController = navController)
        }

        composable(route = Screen.SearchGameScreen.route) {
            SearchGameScreen(navController = navController)
        }

        composable(
            route = Screen.ListDetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ListDetailScreen(
                navController = navController,
                listId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY)
            )
        }
        composable(
            route = Screen.GameDetailScreen.route,
            arguments = listOf(navArgument(name = GAME_ARGUMENT_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            GameDetailScreen(
                navController = navController,
                gameId = backStackEntry.arguments?.getString(GAME_ARGUMENT_KEY)
            )
        }
        composable(
            route = Screen.ReviewScreen.route,
            arguments = listOf(navArgument(name = GAME_ARGUMENT_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            ReviewScreen(
                navController = navController,
                gameId = backStackEntry.arguments?.getString(GAME_ARGUMENT_KEY)
            )
        }
    }
}

