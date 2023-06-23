package com.example.semesterprojekt.viewmodels

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class GameListViewModel : ViewModel() {
    private var _gameLists: MutableLiveData<ArrayList<GameList>> =
        MutableLiveData<ArrayList<GameList>>()
    val gameLists: ArrayList<GameList> = ArrayList()

    init {
        Log.d("init", "init is started")
        viewModelScope.launch{
            getListsView()
            _gameLists.value = gameLists
            Log.d("viemodelinit", gameLists.toString())

        }
    }
    private var _gameList: MutableLiveData<ArrayList<Game>> =
        MutableLiveData<ArrayList<Game>>()
    val gameList: ArrayList<Game> = ArrayList()

    suspend fun getGamesView(gamesList: GameList) {

            for (game in gamesList.gameRefs){
                Database.getGames2(game, gamesList.games)
            }

            Log.d("viewmodel1", gamesList.toString())
            for (games in gamesList.games){
                Log.d("testing", games.developer)
            }

    }
    suspend fun getListsView() {
        Database.getLists(gameLists)
        Log.d("viewModelList", gameLists.toString())
        for (item in gameLists){
            Log.d("viewModelListFor", item.title)
            Log.d("viewModelListFor", item.games.toString())
            getGamesView(item)
        }
    }
}