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
import com.example.semesterprojekt.screens.*
import com.example.semesterprojekt.viewmodels.UserStateViewModel


@Composable
fun Navigation(){
    val navController = rememberNavController()
    val userState = UserStateViewModel()

    NavHost(navController = navController, startDestination = Screen.GameTestingScreen.route){

        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController,userState)
        }

        composable(route = Screen.GameTestingScreen.route){
            GameTestingScreen()
        }

        composable(route = Screen.Registration.route){
            Registration(navController = navController,userState)
        }

        composable(route = Screen.ListDetailScreen.route, arguments= listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ){ backStackEntry ->
            ListDetailScreen(navController = navController,
                listId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY),userState)
        }
        composable(route = Screen.ModifyListScreen.route, arguments= listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ){ backStackEntry ->
            ModifyListScreen(navController = navController,
                listId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY),userState)
        }
        composable(route = Screen.GameDetailScreen.route, arguments= listOf(navArgument(name = GAME_ARGUMENT_KEY) {type = NavType.StringType})
        ){ backStackEntry ->
            GameDetailScreen(navController = navController,
                gameId = backStackEntry.arguments?.getString(GAME_ARGUMENT_KEY),userState)
            }
        }
}
