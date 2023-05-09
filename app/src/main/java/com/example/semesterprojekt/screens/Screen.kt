package com.example.semesterprojekt.screens

sealed class Screen (val route: String){

    object MainScreen : Screen("main")
}