package com.example.semesterprojekt.data

import android.util.Log
import com.example.semesterprojekt.models.Game
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

interface Database{


    companion object {
       suspend fun firebaseSignUp(email: String, password: String) {
            Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        }

        suspend fun firebaseLogIn(email: String, password: String){
            Firebase.auth.signInWithEmailAndPassword(email,password).await()
        }
        suspend fun getGames(): Game?{
            val db = Firebase.firestore
            val document = db.collection("Games").document("Game1").get().await()
            return document.toObject<Game>()
        }
    }
}