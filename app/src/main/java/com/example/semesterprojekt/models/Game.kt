package com.example.semesterprojekt.models

class Game(
    val id: Int,
    val title: String,
    val releaseYear: Int,
    val publisher: String,
    val developer: String,
    val platform: List<String>
)

fun getGames(): List<Game>{
    return listOf(
        Game(
            id = 1,
            title = "Roots of Pacha",
            releaseYear = 2023,
            publisher = "Crytivo",
            developer = "Soda Den",
            platform = listOf("PC")
        ),
        Game(
            id = 2,
            title = "League of Legends",
            releaseYear = 2009,
            publisher = "Tencent",
            developer = "Riot Games",
            platform = listOf("PC")
        ),
        Game(
            id = 3,
            title = "Call of Duty: Modern Warfare 2",
            releaseYear = 2009,
            publisher = "Activision",
            developer = "Infinity Ward",
            platform = listOf("PC","Playstation 3","XBox 360", "Nintendo DS")
        ),
        Game(
            id = 4,
            title = "Final Fantasy VIII",
            releaseYear = 1999,
            publisher = "Square",
            developer = "Square",
            platform = listOf("PC","Playstation 1")
        ),
        Game(
            id = 5,
            title = "Age of Empires II",
            releaseYear = 1999,
            publisher = "Microsoft Game Studios",
            developer = "Ensemble Studios",
            platform = listOf("PC", "Playstation 2")
        )
    )
}