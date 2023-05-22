package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.*
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.RegistrationViewModel
import com.example.semesterprojekt.viewmodels.RegistrationViewModelFactory
import com.example.semesterprojekt.widgets.DataTextfields
import kotlinx.coroutines.launch

@Composable
fun Registration(
    navController: NavController
){
    val factory = RegistrationViewModelFactory(repository = AuthRepository())
    val viewModel: RegistrationViewModel = viewModel(factory= factory)


    val coroutineScope = rememberCoroutineScope()
    val warning = "Registration failed. Please check if your email is valid and your password is long enough(6 characters)!"

    var showWarning by remember{
        mutableStateOf(false)
    }

        Column( Modifier.fillMaxWidth()) {

            DataTextfields(state = viewModel.textfieldUiState, onChange = {newUiState->viewModel.newState(newUiState)})

            Button(onClick = {
                coroutineScope.launch {
                    try{
                        viewModel.signUp()
                        showWarning = false
                    }catch (e: Exception){
                        print (e)
                        showWarning = true
                    }
                }
            })
            {
                Text("Register")
            }
            if(showWarning)
            Text(warning)
        }

}

