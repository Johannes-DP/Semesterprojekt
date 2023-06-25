package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.getDefault
import com.example.semesterprojekt.repository.AuthRepository

class SearchGameViewModel(private val repository:AuthRepository): ViewModel() {

    var game: Game = getDefault()

    suspend fun searchGame(title: String){
        game = repository.searchGame(title)
        }

}
