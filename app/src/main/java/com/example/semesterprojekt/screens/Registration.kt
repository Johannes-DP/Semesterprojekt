package com.example.semesterprojekt.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.viewmodels.RegistrationViewModel
import com.example.semesterprojekt.widgets.DataTextFields
import com.example.semesterprojekt.widgets.RegistrationTopBar
import kotlinx.coroutines.launch


@Composable
fun Registration(
    navController: NavController,
    viewModel: RegistrationViewModel
) {
    Scaffold(topBar = {
        RegistrationTopBar(
        title = "Registration"
        )
    })
    {padding ->
        RegistrationContent(navController = navController, viewModel = viewModel, modifier = Modifier.padding(padding))

    }
}

    @Composable
    fun RegistrationContent(
        navController: NavController,
        viewModel: RegistrationViewModel,
        modifier: Modifier = Modifier
    ) {

       // val viewModel= RegistrationViewModel(repository = ListRepositoryImpl())

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

            DataTextFields(
                state = viewModel.textfieldUiState,
                onChange = { newUiState -> viewModel.newState(newUiState)
                Log.d("test", viewModel.textfieldUiState.email)})
            Row() {
                Button(onClick = {
                    coroutineScope.launch {
                        try {
                            viewModel.signUp()
                            showWarningR = false
                            viewModel.logIn()
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
                            viewModel.logIn()
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



