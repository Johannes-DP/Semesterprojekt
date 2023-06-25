package com.example.semesterprojekt.data


import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.Platform
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

interface ListRepository {
    val db: FirebaseFirestore

    suspend fun firebaseSignUp(email: String, password: String)

    suspend fun addUserToCollection()

    suspend fun firebaseLogIn(email: String, password: String)

    fun logout()

    suspend fun savaData(stars: Double, review: String, hours: Double, gameId: String)

    suspend fun getLists(gameLists: ArrayList<GameList>): ArrayList<GameList>

    suspend fun getGames2(
        reference: DocumentReference, gameArrayList: ArrayList<Game>
    ): ArrayList<Game>

    suspend fun getListById(id: String?): GameList

    suspend fun addGameToList(game: String, listName: String)

    suspend fun removeGameFromList(game: String, listName: String)

    suspend fun filterList(id: String?, platforms: List<Platform>): GameList

    suspend fun addGameToFirebase(game: Game)

    suspend fun searchGame(title: String): Game

    suspend fun getGameById(id: String?): Game

    suspend fun getAvgRating(gameId: String): Double

    suspend fun getAvgHours(gameId: String): Double

    suspend fun getReviews(gameId: String): ArrayList<String>

    suspend fun addList(title: String)

    suspend fun deleteList(title: String)

}