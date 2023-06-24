package com.example.semesterprojekt.viewmodels;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.semesterprojekt.repository.AuthRepository;

class DetailViewModelFactory (private val repository: AuthRepository): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}
