package com.example.semesterprojekt.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor(private val id: String?/*, private val repository: Database*/): ViewModel() {

    val gameState = MutableStateFlow(Game())
    var game = Game()

    init {
        viewModelScope.launch{
            if (id == "dummyId"){
                Log.d("dummyId triggered", id)
            }
            game = Database.getGameById(id)
            game.avgRating = Database.getAvgRating(game.id)
            game.avgHours =  Database.getAvgHours(game.id)
            gameState.value = game

        }
    }

    suspend fun savaData(stars: Double, review: String, hours: Double, userId: String, gameId: String){
        Database.savaData(stars,review,hours,userId,gameId)
    }
}