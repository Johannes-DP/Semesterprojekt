package com.example.semesterprojekt.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.ListRepository
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.models.GameList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameListViewModel @Inject constructor(private val repository: ListRepositoryImpl) : ViewModel() {


    private val _gameListsState = MutableStateFlow(ArrayList<GameList>())
    val gameListsState: StateFlow<ArrayList<GameList>> = _gameListsState.asStateFlow()
    var gameLists: ArrayList<GameList> = ArrayList()


    init {
        viewModelScope.launch {
            gameLists = repository.getLists(gameLists)
            if (gameLists.isEmpty()) {
                Log.d("GamesViewModel", "Empty List")
            } else {
                Log.d("GamesViewModel", "List Filled")
                _gameListsState.value = gameLists
            }

        }
    }

    suspend fun addList(title:String){
        repository.addList(title)
        gameLists.clear()
        gameLists = repository.getLists(gameLists)
        _gameListsState.value = gameLists
    }

    fun logout(){
        repository.logout()
    }

}

