package com.example.semesterprojekt.data

import android.util.Log
import com.example.semesterprojekt.models.Game
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


interface Database{


    companion object {
       suspend fun firebaseSignUp(email: String, password: String) {
           Firebase.auth.createUserWithEmailAndPassword(email,password).await()
           addUsertoCollection()

        }

        suspend fun firebaseLogIn(email: String, password: String){
            Firebase.auth.signInWithEmailAndPassword(email,password).await()
        }
        suspend fun getGames(): String{
            val db = Firebase.firestore
            val document = db.collection("Games").document("Game1").get().await()

            //return document.value.toObject<Game>()!!
            return document.toString()
        }
        fun addUsertoCollection(){

            val db = Firebase.firestore
            val UID = Firebase.auth.currentUser!!.uid
            Log.d("UID", "this is the uid" + UID)

            val docData = hashMapOf(

                "Favorites" to arrayListOf(db.document("Games" + "/Game1"),db.document("Games" + "/Game5"),db.document("Games" + "/Game4")),
                "Wishlist" to arrayListOf(db.document("Games" + "/Game1"),db.document("Games" + "/Game3")),
                "Played" to arrayListOf(),
            )

            db.collection("users").document(UID)
                .set(docData)
                .addOnSuccessListener { Log.d("testing", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.d("testingtag", "Error writing document " + e) }
        }
    }
}