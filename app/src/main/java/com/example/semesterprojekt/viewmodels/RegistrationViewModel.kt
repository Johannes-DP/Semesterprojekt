package com.example.semesterprojekt.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.screens.TextfieldUiState

class RegistrationViewModel (private val repository: AuthRepository): ViewModel() {

    var textfieldUiState by mutableStateOf(TextfieldUiState())
        private set

    suspend fun signUp(){
            repository.firebaseSignUp(textfieldUiState.email,textfieldUiState.password)
            textfieldUiState.email = ""
            textfieldUiState.password = ""
    }

    suspend fun logIn(){
        repository.firebaseLogIn(textfieldUiState.email,textfieldUiState.password)
        textfieldUiState.email = ""
        textfieldUiState.password = ""
    }

    fun newState(newUiState: TextfieldUiState){
        textfieldUiState = newUiState.copy()
    }

}
