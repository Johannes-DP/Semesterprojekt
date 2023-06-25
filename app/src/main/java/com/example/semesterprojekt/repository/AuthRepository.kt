package com.example.semesterprojekt.repository

import com.example.semesterprojekt.data.Database
import com.example.semesterprojekt.models.Game


class AuthRepository() {

    suspend fun firebaseSignUp(email: String, password: String) = Database.firebaseSignUp(email, password)

    suspend fun firebaseLogIn(email: String, password: String) = Database.firebaseLogIn(email,password)

    //fun getUid(): String = Database.getUid()

    fun logout() = Database.logout()

    suspend fun addGameToFirebase(game: Game) = Database.addGameToFirebase(game)

    suspend fun searchGame(title: String): Game = Database.searchGame(title)

    suspend fun getGameById(id: String?): Game = Database.getGameById(id)
}
