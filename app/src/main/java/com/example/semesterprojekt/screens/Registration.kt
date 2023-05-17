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
import com.example.semesterprojekt.widgets.DataTextfields
import kotlinx.coroutines.launch

@Composable
fun Registration(
    navController: NavController
){
    val viewModel1: RegistrationViewModel = viewModel()


    val coroutineScope = rememberCoroutineScope()
    val warning = "Registration failed. Please check if your email is valid and your password is long enough(6 characters)!"

    var showWarning by remember{
        mutableStateOf(false)
    }

    /*var email by remember{
    mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }*/
        Column( Modifier.fillMaxWidth()) {

            DataTextfields(state = viewModel1.textfieldUiState, onChange = {newUiState->viewModel1.newState(newUiState)})

            /*OutlinedTextField(
                value = email,
                onValueChange ={email=it},
                label = {Text("EMail")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(percent = 20),
                keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            var showPassword by remember { mutableStateOf(value = false) }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = { Text(text = "Type password here") },
                shape = RoundedCornerShape(percent = 20),
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                }
            )*/

            Button(onClick = {
                coroutineScope.launch {
                    try{
                        viewModel1.signUp()
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

