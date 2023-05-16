package com.example.semesterprojekt.data

import com.example.semesterprojekt.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDao @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository{

    override suspend fun firebaseSignUp(email: String, password: String){
            auth.createUserWithEmailAndPassword(email,password).await()
    }

    override fun signOut() {
        auth.signOut()
    }

}