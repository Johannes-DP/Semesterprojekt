package com.example.semesterprojekt.models

class Game(
    val id: String,
    val title: String,
    val releaseYear: Int,
    val publisher: String,
    val developer: String,
    val platform: List<String>,
    val image: String
)

fun getGames(): List<Game>{
    return listOf(
        Game(
            id = "Game1",
            title = "Roots of Pacha",
            releaseYear = 2023,
            publisher = "Crytivo",
            developer = "Soda Den",
            platform = listOf("PC"),
            image = "https://ksr-ugc.imgix.net/assets/032/794/881/eb344f1de1c45bcdfdf2126d4b2e77c4_original.png?ixlib=rb-4.0.2&crop=faces&w=1552&h=873&fit=crop&v=1616109932&auto=format&frame=1&q=92&s=daab41311fb537d00427d14aa9df39ba"
        ),
        Game(
            id = "Game2",
            title = "League of Legends",
            releaseYear = 2009,
            publisher = "Tencent",
            developer = "Riot Games",
            platform = listOf("PC"),
            image = "https://cdn1.epicgames.com/offer/24b9b5e323bc40eea252a10cdd3b2f10/LoL_1200x1600-15ad6c981af8d98f50e833eac7843986"
        ),
        Game(
            id = "Game3",
            title = "Call of Duty: Modern Warfare 2",
            releaseYear = 2009,
            publisher = "Activision",
            developer = "Infinity Ward",
            platform = listOf("PC","Playstation 3","XBox 360", "Nintendo DS"),
            image = "https://assets-prd.ignimgs.com/2022/05/24/call-of-duty-modern-warfare-2-button-02-1653417394041.jpg"
        ),
        Game(
            id = "Game4",
            title = "Final Fantasy VIII",
            releaseYear = 1999,
            publisher = "Square",
            developer = "Square",
            platform = listOf("PC","Playstation 1"),
            image = "null"
        ),
        Game(
            id = "Game5",
            title = "Age of Empires II",
            releaseYear = 1999,
            publisher = "Microsoft Game Studios",
            developer = "Ensemble Studios",
            platform = listOf("PC", "Playstation 2"),
            image = "https://upload.wikimedia.org/wikipedia/en/4/45/Age_of_Empires_II_Definitive_Edition_cover_art.jpeg"
        )
    )
}