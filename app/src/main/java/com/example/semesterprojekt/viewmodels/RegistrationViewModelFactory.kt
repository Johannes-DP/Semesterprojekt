package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.semesterprojekt.repository.AuthRepository

class RegistrationViewModelFactory(private val repository: AuthRepository):ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass:Class<T>):T{
        if(modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
            return RegistrationViewModel(repository) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}