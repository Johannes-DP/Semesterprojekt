package com.example.semesterprojekt.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

class GameList(
    val title: String,
    val gameRefs: List<com.google.firebase.firestore.DocumentReference>,
    val games: ArrayList<Game>
)
