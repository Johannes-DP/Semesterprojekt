package com.example.semesterprojekt.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semesterprojekt.screens.TextfieldUiState


@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    errMsg: String = "",
    isError: Boolean,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onDone: () -> Unit = {},
    onChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            onChange(it)
        },
        label = { Text(text = label) },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
    )
    if (isError){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = errMsg,
            fontSize = 14.sp,
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
fun DataTextFields(state: TextfieldUiState, onChange: (TextfieldUiState)->Unit) {

    OutlinedTextField(
        value = state.email,
        onValueChange = { input -> onChange(state.copy(email = input)) },
        label = { Text("Email") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent = 20),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )

    var passwordIsVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = state.password,
        onValueChange = { input -> onChange(state.copy(password = input)) },
        label = { Text("Password") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent = 20),
        visualTransformation = if (passwordIsVisible) {
            VisualTransformation.None
        } else {
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

