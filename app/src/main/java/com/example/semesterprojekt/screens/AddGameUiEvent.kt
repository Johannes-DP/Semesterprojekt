package com.example.semesterprojekt.screens

sealed class AddGameUiEvent {
    object TitleChanged: AddGameUiEvent()

    object YearChanged: AddGameUiEvent()

    object PublisherChanged: AddGameUiEvent()

    object DeveloperChanged: AddGameUiEvent()

    object PlatformChanged: AddGameUiEvent()

    object ImageChanged: AddGameUiEvent()

    object RatingChanged: AddGameUiEvent()

}