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

class ListDetailViewModel @Inject constructor(private val id: String?): ViewModel() {


    private val _gameListState = MutableStateFlow(GameList("", emptyList(), ArrayList()))
    val gameListState: StateFlow<GameList> = _gameListState.asStateFlow()

    init {
        viewModelScope.launch{
            if (id == "dummyId"){
                Log.d("dummyId triggered", id)
            }
            _gameListState.value = Database.getListById(id)

        }
    }

    suspend fun addGameToList(listName: String, game: Game) {
        Database.addGametoList(game.id, listName)
    }

    suspend fun removeGameFromList(title:String, listId: String?){
        if(listId != null)
            Database.removeGameFromList(title,listId)
    }

    suspend fun deleteList(listId: String){
        Database.deleteList(listId)
    }


    suspend fun getGameById(id: String?): Game {
        Log.d("test", Database.getGameById(id).id)
        return Database.getGameById(id)

    }
}