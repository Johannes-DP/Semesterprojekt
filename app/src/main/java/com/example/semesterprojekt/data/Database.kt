package com.example.semesterprojekt.data

import com.google.firebase.auth.ktx.auth
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
    }
}