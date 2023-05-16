package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun Registration(
    navController: NavController,
    viewModel: RegistrationViewModel
){
    var email by remember{
    mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
        Column( Modifier.fillMaxWidth()) {
            EmailField(label = "Email", modifier = Modifier.fillMaxWidth())
            PasswordField(label = "Password", modifier = Modifier.fillMaxWidth())


            Button(onClick = { viewModel.signUp(email, password) })
            {
                Text("Register")
            }
        }
}

