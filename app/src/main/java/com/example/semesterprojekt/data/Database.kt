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
            db.collection("Games")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("gettingGames", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("gettingGames", "Error getting documents: ", exception)
                }
            //return document.value.toObject<Game>()!!
            return document.toString()
        }

        suspend fun getGames2(): Game? {
            //val gameList: ArrayList<>
            val db = Firebase.firestore
            val docRef = db.collection("Games").document("Game6")
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()){
                        val game = documentSnapshot.toObject<Game>()
                        Log.d("testing Game", game.toString())


                    }

                }
            return null

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

        fun getUid(): String{
            return Firebase.auth.currentUser!!.uid
        }

        fun logout(){
            Firebase.auth.signOut()
        }

        suspend fun addGameToFirebase(game: Game){
            val db = Firebase.firestore
            var x: Int = 7

            val hash = hashMapOf(
                "developer" to game.developer,
                "image" to game.image,
                "platform" to game.platform,
                "publisher" to game.publisher,
                "rating" to game.rating,
                "releaseYear" to game.releaseYear,
                "title" to game.title


            )
            db.collection("Games").document("Game"+x)
                .set(hash)
                .addOnSuccessListener { Log.d("Success","Successfull written") }
                .addOnFailureListener { e -> Log.w("Failure", "Error writing Document",e)}

            x+=1

        }

    }
}