package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.ListRepository
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.models.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor(private val id: String?, private val repository: ListRepositoryImpl): ViewModel() {

    val gameState = MutableStateFlow(Game())
    var game = Game()
    val reviewState = MutableStateFlow(ArrayList<String>())
    private var reviews = ArrayList<String>()
    init {
        viewModelScope.launch{
            game = repository.getGameById(id)
            game.avgRating = repository.getAvgRating(game.id)
            game.avgHours =  repository.getAvgHours(game.id)
            gameState.value = game
            reviews = repository.getReviews(game.id)
            reviewState.value = reviews

        }
    }

    suspend fun savaData(stars: Double, review: String, hours: Double, gameId: String){
        repository.savaData(stars,review,hours,gameId)
    }
}