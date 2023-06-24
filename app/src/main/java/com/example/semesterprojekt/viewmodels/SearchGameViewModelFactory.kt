package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.semesterprojekt.repository.AuthRepository

class SearchGameViewModelFactory(private val repository: AuthRepository): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(SearchGameViewModel::class.java)){
            return SearchGameViewModel(repository) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}