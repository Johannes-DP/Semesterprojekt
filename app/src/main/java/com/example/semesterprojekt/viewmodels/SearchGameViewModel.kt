package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.models.Game

import javax.inject.Inject

class SearchGameViewModel @Inject constructor(private val repository: ListRepositoryImpl) :
    ViewModel() {

    var game = Game()

    suspend fun searchGame(title: String) {
        game = repository.searchGame(title)
    }

}
