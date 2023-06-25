package com.example.semesterprojekt.models

class GameList(
    val title: String,
    val gameRefs: List<com.google.firebase.firestore.DocumentReference>,
    val games: ArrayList<Game>
)
