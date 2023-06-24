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
    companion object{
        @Volatile
        private var instance: GameListViewModel? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: GameListViewModel().also {instance = it}
            }
    }


    private val _gameListsState = MutableStateFlow(ArrayList<GameList>())
    val gameListsState: StateFlow<ArrayList<GameList>> = _gameListsState.asStateFlow()
    val gameLists: ArrayList<GameList> = ArrayList()

    private val _gamesState = MutableStateFlow(ArrayList<Game>())
    val gamesState: StateFlow<ArrayList<Game>> = _gamesState.asStateFlow()
    val games: ArrayList<Game> = ArrayList()

    val gameState = MutableStateFlow(Game())




    init {
        viewModelScope.launch{
            Database.getLists(gameLists)
            if (gameLists.isEmpty()){
                Log.d("GamesViewModel", "Empty List")
            } else {
                _gameListsState.value = gameLists
                for (item in gameLists){
                    Log.d("vieModelGame","import 1")
                    Log.d("viewModelListFor", item.games.toString())
                    getGames(item)
                    _gamesState.value = item.games
                }
            }
            Log.d("testgameState", _gamesState.value.toString())
        }

    }

    /*suspend fun getListsView() {
        Database.getLists(gameLists)
        _gameLists.value = gameLists

        /*Log.d("viewModelList", gameLists.toString())
        if (gameLists != null) {
            for (item in gameLists){
                Log.d("viewModelListFor", item.title)
                Log.d("viewModelListFor", item.games.toString())
                getGamesView(item)
            }
        }
        _gameLists.postValue(gameLists)*/
    }*/



    suspend fun getGames(gamesList: GameList) {

        for (game in gamesList.gameRefs){
            Database.getGames2(game, gamesList.games)
        }
        Log.d("viewmodel1", gamesList.toString())
        for (games in gamesList.games){
            Log.d("testing", games.developer)
        }
    }

    suspend fun getGameById(id: String?): Game{
        return Database.getGameById(id)
    }
}

