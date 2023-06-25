package com.example.semesterprojekt.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.*
import com.example.semesterprojekt.screens.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListDetailViewModel @Inject constructor(private val id: String?): ViewModel() {


    private val _gameListState = MutableStateFlow(GameList("", emptyList(), ArrayList()))
    val gameListState: StateFlow<GameList> = _gameListState.asStateFlow()

    var listDetailUiState by mutableStateOf(ListDetailUiState())
        private set

    var platforms = listOf<Platform>()
    var platformsSelected = listOf<Platform>()

    init {
        viewModelScope.launch {
            if (id == "dummyId") {
                Log.d("dummyId triggered", id)
            }
            _gameListState.value = Database.getListById(id)

        }
    }

    suspend fun addGameToList(listName: String, game: Game) {
        Database.addGametoList(game.id, listName)
        _gameListState.value = Database.getListById(listName)
    }

    suspend fun removeGameFromList(title:String, listId: String?){
        if(listId != null)
            Database.removeGameFromList(title,listId)
        _gameListState.value = Database.getListById(listId)
    }

    suspend fun deleteList(listId: String){
        Database.deleteList(listId)
    }


    suspend fun getGameById(id: String?): Game {
        Log.d("test", Database.getGameById(id).id)
        return Database.getGameById(id)

    }
    suspend fun filterList(id: String?, platforms: List<Platform>){
        Log.d("testing", id + " - " + platforms.toString())
        //Database.filterList(id, platforms)
        if (platforms.isEmpty()){
            Log.d("platforms", "its empty")
            _gameListState.value = Database.getListById(id)
        } else {
            Log.d("platforms", "its full")
            _gameListState.value = Database.filterList(id, platforms)
        }

    }
}