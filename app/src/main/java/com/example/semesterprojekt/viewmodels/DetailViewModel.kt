package com.example.semesterprojekt.viewmodels;

import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.repository.AuthRepository;

class DetailViewModel (private val repository: AuthRepository): ViewModel(){


    var game = Game()

    suspend fun getGameById(id: String?){
         game = repository.getGameById(id)
    }
}
