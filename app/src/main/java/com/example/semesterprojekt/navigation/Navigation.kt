package com.example.semesterprojekt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.semesterprojekt.screens.*

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){

        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.ListDetailScreen.route, arguments= listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ){ backStackEntry ->
            ListDetailScreen(navController = navController,
                listId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY))
        }
        composable(route = Screen.GameDetailScreen.route, arguments= listOf(navArgument(name = GAME_ARGUMENT_KEY) {type = NavType.StringType})
        ){ backStackEntry ->
            GameDetailScreen(navController = navController,
                gameId = backStackEntry.arguments?.getString(GAME_ARGUMENT_KEY))
        }
    }
}
