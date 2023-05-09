package com.example.semesterprojekt.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

class GameList(
    val id: Int,
    val title: String,
    val games: List<Game>,
    val count: Int,
    val icon: ImageVector
)

fun getGameLists(): List<GameList> {
    return listOf(
        GameList(
            id = 1,
            title = "Favorites",
            games = listOf(getGames()[0], getGames()[2]),
            count = 2,
            icon = Icons.Default.List
        ),
        GameList(
            id = 2,
            title = "Played",
            games = listOf(getGames()[0], getGames()[2], getGames()[4]),
            count = 3,
            icon = Icons.Default.ArrowDropDown
        ),
        GameList(
            id = 3,
            title = "Wishlist",
            games = listOf(getGames()[1], getGames()[3]),
            count = 2,
            icon = Icons.Default.AddCircle
        )
    )
}