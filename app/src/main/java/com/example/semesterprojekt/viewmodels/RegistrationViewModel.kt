package com.example.semesterprojekt.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.screens.TextfieldUiState
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val repository: ListRepositoryImpl) :
    ViewModel() {

    var textfieldUiState by mutableStateOf(TextfieldUiState())
        private set

    suspend fun signUp() {
        repository.firebaseSignUp(textfieldUiState.email, textfieldUiState.password)
    }

    suspend fun logIn() {
        repository.firebaseLogIn(textfieldUiState.email, textfieldUiState.password)
        textfieldUiState.email = ""
        textfieldUiState.password = ""
    }

    fun newState(newUiState: TextfieldUiState) {
        textfieldUiState = newUiState.copy()
       // Log.d("testTextField", textfieldUiState.email)
        //Log.d("testNewUiStat", newUiState.email)
    }

}
