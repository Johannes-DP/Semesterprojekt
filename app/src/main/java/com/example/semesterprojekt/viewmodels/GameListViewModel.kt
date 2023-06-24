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

class GameListViewModel : ViewModel() {
    /*companion object{
        @Volatile
        private var instance: GameListViewModel? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: GameListViewModel().also {instance = it}
            }
    }*/


    private val _gameListsState = MutableStateFlow(ArrayList<GameList>())
    val gameListsState: StateFlow<ArrayList<GameList>> = _gameListsState.asStateFlow()
    val gameLists: ArrayList<GameList> = ArrayList()


    private val _gamesState = MutableStateFlow(ArrayList<Game>())
    val gamesState: StateFlow<ArrayList<Game>> = _gamesState.asStateFlow()
    val games: ArrayList<Game> = ArrayList()

    val gameState = MutableStateFlow(Game())


    init {
        viewModelScope.launch {
            Database.getLists(gameLists)
            if (gameLists.isEmpty()) {
                Log.d("GamesViewModel", "Empty List")
            } else {
                _gameListsState.value = gameLists
            }

        }
    }




    }

