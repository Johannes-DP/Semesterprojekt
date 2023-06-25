package com.example.semesterprojekt.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.models.*
import com.example.semesterprojekt.screens.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListDetailViewModel @Inject constructor(
    private val id: String?,
    private val repository: ListRepositoryImpl
) : ViewModel() {


    private val _gameListState = MutableStateFlow(GameList("", emptyList(), ArrayList()))
    val gameListState: StateFlow<GameList> = _gameListState.asStateFlow()

    var listDetailUiState by mutableStateOf(ListDetailUiState())
        private set


    init {
        viewModelScope.launch {
            _gameListState.value = repository.getListById(id)
        }
    }

    suspend fun addGameToList(listName: String, game: Game) {
        repository.addGameToList(game.id, listName)
        _gameListState.value = repository.getListById(listName)
    }

    suspend fun removeGameFromList(title: String, listId: String?) {
        if (listId != null)
            repository.removeGameFromList(title, listId)
        _gameListState.value = repository.getListById(listId)
    }

    suspend fun deleteList(listId: String) {
        repository.deleteList(listId)
    }


    suspend fun getGameById(id: String?): Game {
        return repository.getGameById(id)

    }

    suspend fun filterList(id: String?, platforms: List<Platform>) {
        if (platforms.isEmpty()) {
            _gameListState.value = repository.getListById(id)
        } else {
            _gameListState.value = repository.filterList(id, platforms)
        }

    }
}