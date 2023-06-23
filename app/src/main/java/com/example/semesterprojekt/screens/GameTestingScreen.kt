package com.example.semesterprojekt.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.widgets.*
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GameTestingScreen(

) {
    var text = ""
    val coroutine = rememberCoroutineScope()
    var game = Game(id = "Game4",
    title = "Final Fantasy VIII",
    releaseYear = 1999,
    publisher = "Square",
    developer = "Square",
    platform = listOf("PC","Playstation 1"),
    image = "null",
    rating = 9.3f,)
    //ratingsCount = 5321)

    coroutine.launch {
        text = Database.getGames2().toString()
    }

    Row() {
        Text(game.toString())
        Text(text)
    }
}




