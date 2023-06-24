package com.example.semesterprojekt.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    private var _gameLists: MutableLiveData<ArrayList<GameList>> = MutableLiveData<ArrayList<GameList>>()
    val gameLists: ArrayList<GameList> = ArrayList()


    init {
        Log.d("init", "init is started")
        viewModelScope.launch{
            getListsView()
            Log.d("viemodelinit", gameLists.toString())

        }
    }


    suspend fun getListsView() {
        _gameLists.value = Database.getLists(gameLists)
        Log.d("viewModelList", gameLists.toString())
        for (item in gameLists){
            Log.d("viewModelListFor", item.title)
            Log.d("viewModelListFor", item.games.toString())
            getGamesView(item)
        }
        _gameLists.postValue(gameLists)
    }

    suspend fun getGamesView(gamesList: GameList) {

        for (game in gamesList.gameRefs){
            Database.getGames2(game, gamesList.games)
        }

        Log.d("viewmodel1", gamesList.toString())
        for (games in gamesList.games){
            Log.d("testing", games.developer)
        }

    }
}