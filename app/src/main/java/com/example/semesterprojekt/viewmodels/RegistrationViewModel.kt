package com.example.semesterprojekt.viewmodels;

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

            val email = textfieldUiState.email
            val password = textfieldUiState.password
            repository.firebaseSignUp(email,password)
    }

    suspend fun logIn(): String{
        val email = textfieldUiState.email
        val password = textfieldUiState.password
        repository.firebaseLogIn(email,password)
        return repository.getUid()
    }

    fun newState(newUiState: TextfieldUiState){
        textfieldUiState = newUiState.copy()
    }

}
