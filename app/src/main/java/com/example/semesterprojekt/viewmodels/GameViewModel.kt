package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor(private val id: String?): ViewModel() {

    val gameState = MutableStateFlow(Game())
    var game = Game()
    val reviewState = MutableStateFlow(ArrayList<String>())
    private var reviews = ArrayList<String>()
    init {
        viewModelScope.launch{
            game = Database.getGameById(id)
            game.avgRating = Database.getAvgRating(game.id)
            game.avgHours =  Database.getAvgHours(game.id)
            gameState.value = game
            reviews = Database.getReviews(game.id)
            reviewState.value = reviews

        }
    }

    suspend fun savaData(stars: Double, review: String, hours: Double, gameId: String){
        Database.savaData(stars,review,hours,gameId)
    }
}