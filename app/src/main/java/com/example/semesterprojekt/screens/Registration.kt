package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import com.example.semesterprojekt.viewmodels.RegistrationViewModel
import com.example.semesterprojekt.widgets.EmailField
import com.example.semesterprojekt.widgets.PasswordField
import kotlinx.coroutines.launch

@Composable
fun Registration(
    navController: NavController,
    viewModel: RegistrationViewModel
){
    val coroutineScope = rememberCoroutineScope()

    var email by remember{
    mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
        Column( Modifier.fillMaxWidth()) {

            OutlinedTextField(
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
            )

            /*OutlinedTextField(
                value = password,
                onValueChange ={password=it},
                label = {Text("label")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if(passwordIsVisible){
                    VisualTransformation.None}else{
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val icon = if (passwordIsVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }
                    IconButton(
                        onClick = {
                            passwordIsVisible = !passwordIsVisible
                        }
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null
                        )
                    }


                }
            )*/
            Button(onClick = {
                coroutineScope.launch {
                    viewModel.signUp(email = email, password = password)
                }
            })
            {
                Text("Register")
            }
        }


        }



