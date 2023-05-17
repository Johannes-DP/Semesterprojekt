package com.example.semesterprojekt.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.semesterprojekt.screens.TextfieldUiState

@Composable
fun SimpleTextField(label: String, modifier: Modifier){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange ={text=it},
        label = {Text(label)},
        modifier = modifier
    )
}
/*
@Composable
fun EmailField(){

    OutlinedTextField(
        value = text,
        onValueChange ={text=it},
        label = {Text("Email")},
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent = 20),
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email)
    )

}
@Composable
fun PasswordField(){
    var passwordIsVisible by remember{ mutableStateOf(false) }


    OutlinedTextField(
        value = text,
        onValueChange ={text=it},
        label = {Text("Password")},
        singleLine = true,
        modifier =  Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent = 20),
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
    )
}*/

@Composable
fun DataTextfields(state: TextfieldUiState, onChange: (TextfieldUiState)->Unit){

    OutlinedTextField(
        value = state.email,
        onValueChange ={input -> onChange(state.copy(email = input))},
        label = {Text("Email")},
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent = 20),
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email)
    )

    var passwordIsVisible by remember{ mutableStateOf(false) }

    //TODO: Recomposition and Factory for ViewModel -> AuthRepository

    OutlinedTextField(
        value = state.password,
        onValueChange ={input -> onChange(state.copy(password = input))},
        label = {Text("Password")},
        singleLine = true,
        modifier =  Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent = 20),
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
    )

}

