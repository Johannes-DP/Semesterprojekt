package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.*
import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.getGames
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.RegistrationViewModel
import com.example.semesterprojekt.viewmodels.RegistrationViewModelFactory
import com.example.semesterprojekt.widgets.DataTextfields
import kotlinx.coroutines.launch
import com.example.semesterprojekt.widgets.RegistrationTopBar

@Composable
suspend fun LogIn(
    navController: NavController
) {

    Database.getGames()
}

