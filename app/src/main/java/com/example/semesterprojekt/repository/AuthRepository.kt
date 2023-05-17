package com.example.semesterprojekt.repository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class AuthRepository(){

suspend fun firebaseSignUp(email: String, password: String) {
    val auth = Firebase.auth
    auth.createUserWithEmailAndPassword(email,password).await()
}

//fun signOut() = AuthDao.signOut()
}
