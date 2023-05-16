package com.example.semesterprojekt.repository

interface AuthRepository{

suspend fun firebaseSignUp(email: String, password: String)

fun signOut()
}