package com.example.semesterprojekt.screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.ListItemSelectable
import com.example.semesterprojekt.models.Platform
import com.example.semesterprojekt.models.Validator
import java.util.*

data class AddGameUiState(
    val title: String = "",
    val releaseYear: String ="",
    val publisher: String = "",
    val developer: String = "",
    val platform: List<Platform> = listOf(),
    val image: String = "",
    val rating: String = "",
    var selectablePlatformItems: SnapshotStateList<ListItemSelectable> = Platform.values().toList().map(){ platform ->
        ListItemSelectable(title=platform.toString())
    }.toMutableStateList(),
    val titleErr: Boolean = false,
    val yearErr: Boolean = false,
    val pubErr: Boolean = false,
    val devErr: Boolean = false,
    val platErr: Boolean = false,
    val imgErr: Boolean = false,
    val ratingErr: Boolean = false,
    val actionEnabled: Boolean = false
)

fun AddGameUiState.selectPlatform(item: ListItemSelectable): List<Platform>{
    selectablePlatformItems.find {it.title == item.title}?.let {platform ->
        platform.isSelected = !platform.isSelected
    }
    return selectablePlatformItems.filter{ item -> item.isSelected }.map { listItemSelectable ->
        Platform.valueOf(listItemSelectable.title)
    }
}

fun AddGameUiState.toGame(): Game = Game(
    id = UUID.randomUUID().toString(),
    title = title,
    releaseYear = releaseYear,
    publisher = publisher,
    developer = developer,
    platform = platform,
    image = image,
    rating = rating
)


fun AddGameUiState.hasError(): Boolean{
    val titleResult = Validator.validateGameTitle(title)
    val yearResult = Validator.validateGameYear(releaseYear)
    val pubResult = Validator.validateGamePublisher(publisher)
    val devResult = Validator.validateGameDeveloper(developer)
    val platResult = Validator.validateGamePlatform(platform)
    val imgResult = Validator.validateGameImage(image)
    val ratingResult = Validator.validateGameRating(rating)
    return listOf(
        titleResult,
        yearResult,
        pubResult,
        devResult,
        platResult,
        imgResult,
        ratingResult
    ).any{!it.successful}
}