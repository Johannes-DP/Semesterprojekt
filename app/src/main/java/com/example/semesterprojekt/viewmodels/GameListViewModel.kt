package com.example.semesterprojekt.viewmodels

import android.provider.ContactsContract.Data
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


    private val _gameListsState = MutableStateFlow(ArrayList<GameList>())
    val gameListsState: StateFlow<ArrayList<GameList>> = _gameListsState.asStateFlow()
    var gameLists: ArrayList<GameList> = ArrayList()


    init {
        viewModelScope.launch {
            gameLists = Database.getLists(gameLists)
            if (gameLists.isEmpty()) {
                Log.d("GamesViewModel", "Empty List")
            } else {
                Log.d("GamesViewModel", "List Filled")
                _gameListsState.value = gameLists
            }

        }
    }

    suspend fun addList(title:String){
        Database.addList(title)
    }

}

