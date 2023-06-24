package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.*
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.RegistrationViewModel
import com.example.semesterprojekt.viewmodels.RegistrationViewModelFactory
import com.example.semesterprojekt.viewmodels.UserStateViewModel
import com.example.semesterprojekt.widgets.DataTextfields
import kotlinx.coroutines.launch
import com.example.semesterprojekt.widgets.RegistrationTopBar
import kotlinx.coroutines.handleCoroutineException

@Composable
fun Registration(
    navController: NavController,
    userModel: UserStateViewModel
) {
    Scaffold(topBar = {RegistrationTopBar(
        title = "Registration",
        arrowBackClicked = { navController.popBackStack() })
    })
    {padding ->
        RegistrationContent(navController = navController,Modifier.padding(padding),userModel)

    }
}

    @Composable
    fun RegistrationContent(
        navController: NavController,
        modifier: Modifier = Modifier,
        userModel: UserStateViewModel
    ) {
        val factory = RegistrationViewModelFactory(repository = AuthRepository())
        val viewModel: RegistrationViewModel = viewModel(factory = factory)

        val coroutineScope = rememberCoroutineScope()
        val warningReg =
            "Registration failed. Please check if your email is valid and your password is long enough(6 characters)!"
        val warningLog = "Login failed. Please check your credentials"

        var showWarningR by remember {
            mutableStateOf(false)
        }

        var showWarningL by remember {
            mutableStateOf(false)
        }

        Column(Modifier.fillMaxWidth()) {

            DataTextfields(
                state = viewModel.textfieldUiState,
                onChange = { newUiState -> viewModel.newState(newUiState) })
            Row() {
                Button(onClick = {
                    coroutineScope.launch {
                        try {
                            viewModel.signUp()
                            showWarningR = false
                            userModel.user = viewModel.logIn()
                            navController.navigate(Screen.MainScreen.route)
                        } catch (e: Exception) {
                            print(e)
                            showWarningR = true
                        }
                    }
                })
                {
                    Text("Register")
                }
                Button(onClick = {
                    coroutineScope.launch {
                        try {
                            userModel.user = viewModel.logIn()
                            navController.navigate(Screen.MainScreen.route)
                            showWarningL = false
                        } catch (e: Exception) {
                            print(e)
                            showWarningL = true
                        }
                    }
                })
                {
                    Text("Login")
                }
            }

            if (showWarningR) {
                Text(warningReg)
            }
            if (showWarningL) {
                Text(warningLog)
            }
        }
    }



