package com.example.semesterprojekt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semesterprojekt.screens.HomeScreen
import com.example.semesterprojekt.screens.Registration
import com.example.semesterprojekt.screens.Screen
import com.example.semesterprojekt.viewmodels.RegistrationViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Registration.route){

        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Registration.route){
            Registration(navController = navController, viewModel = RegistrationViewModel())
        }
    }
}
