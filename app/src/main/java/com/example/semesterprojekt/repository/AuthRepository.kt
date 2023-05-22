package com.example.semesterprojekt.repository

import com.example.semesterprojekt.data.Database


class AuthRepository() {

    suspend fun firebaseSignUp(email: String, password: String) =
        Database.firebaseSignUp(email, password)

    suspend fun firebaseLogIn(email: String, password: String) = Database.firebaseLogIn(email,password)

}
