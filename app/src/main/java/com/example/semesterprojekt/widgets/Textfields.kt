package com.example.semesterprojekt.widgets

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
@Composable
fun EmailField(label: String, modifier: Modifier){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange ={text=it},
        label = {Text(label)},
        singleLine = true,
        modifier = modifier,
        shape = RoundedCornerShape(percent = 20),
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}
@Composable
fun PasswordField(label: String, modifier: Modifier){
    var text by remember { mutableStateOf("") }
    var passwordIsVisible by remember{ mutableStateOf(false) }


    OutlinedTextField(
        value = text,
        onValueChange ={text=it},
        label = {Text(label)},
        singleLine = true,
        modifier = modifier,
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