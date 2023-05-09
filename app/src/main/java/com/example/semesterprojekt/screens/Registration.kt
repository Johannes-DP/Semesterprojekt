package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Registration(
    navController: NavController
){
    Column() {
        OutlinedTextField(value = "email", onValueChange = {onChange(it)})
    }
}