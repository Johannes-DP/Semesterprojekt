package com.example.semesterprojekt.viewmodels;

import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.repository.AuthRepository

class RegistrationViewModel (
    private val repository: AuthRepository
    ): ViewModel() {

    suspend fun signUp(email: String, password: String){
        repository.firebaseSignUp(email,password)
    }
}
