package com.example.semesterprojekt.models

class GameList(
    val id: Int,
    val title: String,
    val games: List<Game>,
    val count: Int
)

fun getGameLists(): List<GameList> {
    return listOf(
        GameList(
            id = 1,
            title = "Favorites",
            games = listOf(getGames()[1], getGames()[3]),
            count = 2
        ),
        GameList(
            id = 2,
            title = "Played",
            games = listOf(getGames()[1], getGames()[3], getGames()[5]),
            count = 3
        ),
        GameList(
            id = 3,
            title = "Wishlist",
            games = listOf(getGames()[2], getGames()[4]),
            count = 2
        )
    )
}