package com.example.semesterprojekt.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.models.Validator
import com.example.semesterprojekt.screens.AddGameUiEvent
import com.example.semesterprojekt.screens.AddGameUiState
import com.example.semesterprojekt.screens.hasError
import com.example.semesterprojekt.screens.toGame
import javax.inject.Inject

class AddGameViewModel @Inject constructor(private val repository: ListRepositoryImpl) :
    ViewModel() {

    var addGameUiState by mutableStateOf(AddGameUiState())
        private set


    suspend fun addGameToFirebase() {
        repository.addGameToFirebase(addGameUiState.toGame())
    }

    fun updateUiState(newGameUiState: AddGameUiState, event: AddGameUiEvent) {
        var state = AddGameUiState()

        when (event) {
            is AddGameUiEvent.TitleChanged -> {
                val titleResult = Validator.validateGameTitle(newGameUiState.title)
                state =
                    if (!titleResult.successful) newGameUiState.copy(titleErr = true) else newGameUiState.copy(
                        titleErr = false
                    )
            }

            is AddGameUiEvent.YearChanged -> {
                val yearResult = Validator.validateGameYear(newGameUiState.releaseYear)
                state =
                    if (!yearResult.successful) newGameUiState.copy(yearErr = true) else newGameUiState.copy(
                        yearErr = false
                    )
            }

            is AddGameUiEvent.PublisherChanged -> {
                val pubResult = Validator.validateGamePublisher(newGameUiState.publisher)
                state =
                    if (!pubResult.successful) newGameUiState.copy(pubErr = true) else newGameUiState.copy(
                        pubErr = false
                    )
            }

            is AddGameUiEvent.DeveloperChanged -> {
                val devResult = Validator.validateGameDeveloper(newGameUiState.developer)
                state =
                    if (!devResult.successful) newGameUiState.copy(devErr = true) else newGameUiState.copy(
                        devErr = false
                    )
            }

            is AddGameUiEvent.PlatformChanged -> {
                val platResult = Validator.validateGamePlatform(newGameUiState.platform)
                state =
                    if (!platResult.successful) newGameUiState.copy(platErr = true) else newGameUiState.copy(
                        platErr = false
                    )
            }

            is AddGameUiEvent.ImageChanged -> {
                val imgResult = Validator.validateGameImage(newGameUiState.image)
                state =
                    if (!imgResult.successful) newGameUiState.copy(imgErr = true) else newGameUiState.copy(
                        imgErr = false
                    )
            }

            is AddGameUiEvent.RatingChanged -> {
                val ratingResult = Validator.validateGameRating(newGameUiState.rating)
                state =
                    if (!ratingResult.successful) newGameUiState.copy(ratingErr = true) else newGameUiState.copy(
                        ratingErr = false
                    )
            }
        }
        addGameUiState = state.copy(actionEnabled = !newGameUiState.hasError())
    }
}
