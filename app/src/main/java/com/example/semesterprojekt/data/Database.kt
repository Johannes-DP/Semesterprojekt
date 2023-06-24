package com.example.semesterprojekt.data

import android.util.Log
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getDefault
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.ArrayList


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
        suspend fun getLists(
            gameListArrayList: ArrayList<GameList>
        ): ArrayList<GameList>{
            val db = Firebase.firestore
            db.collection("users").document(getUid())
                .get()
                .addOnSuccessListener { fieldSnapshot ->
                    if (fieldSnapshot.getData()?.isNotEmpty() == true){
                        val list = fieldSnapshot.data
                        if (list != null) {
                            for (document in list){
                                Log.d("testnow", document.toString())
                                val gameList = GameList(document.key,
                                    document.value as List<DocumentReference>,
                                    ArrayList()
                                )
                                gameListArrayList.add(gameList)
                            }
                        }

                    }
                  //  val gameList = fieldSnapshot.data.toObject<GameList>()
                   // Log.d("after", gameList.toString())
                }
                .await()
            return gameListArrayList
        }

        suspend fun getGames2(
            reference: DocumentReference,
            gameArrayList: ArrayList<Game>
        ): ArrayList<Game> {

            val db = Firebase.firestore
            db.collection("Games").document(reference.id)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.getData()?.isNotEmpty() == true){
                        val game = documentSnapshot.toObject(Game::class.java)
                        if (game != null) {
                            Log.d("test", game.title)
                            gameArrayList.add(game)
                        }

                    }
                    //  val gameList = fieldSnapshot.data.toObject<GameList>()
                    // Log.d("after", gameList.toString())
                }

                .addOnFailureListener{
                    Log.d("Failure", "not able to get games")
                }
                .await()
            return gameArrayList
        }
        suspend fun getGamesRef(
            gameArrayList: ArrayList<Game>
        ): ArrayList<Game> {

            val db = Firebase.firestore
            db.collection("Games")
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (!documentSnapshot.isEmpty){
                        val list = documentSnapshot.documents
                        for (document in list){
                            val game: Game? = document.toObject(Game::class.java)
                            if (game != null){
                                Log.d("success", game.title)
                                gameArrayList.add(game)
                                Log.d("List", gameArrayList.toString())
                            }
                        }

                    }

                }

                .addOnFailureListener{
                    Log.d("Failure", "not able to get games")
                }
                .await()
            return gameArrayList
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
            Log.d("uid",Firebase.auth.currentUser!!.uid )
            return Firebase.auth.currentUser!!.uid
        }
        fun logout(){
            Firebase.auth.signOut()
        }

        suspend fun addGameToFirebase(game: Game){
            val db = Firebase.firestore
            val id: String = UUID.randomUUID().toString()

            val hash = hashMapOf(
                "developer" to game.developer,
                "image" to game.image,
                "platform" to game.platform,
                "publisher" to game.publisher,
                "rating" to game.rating,
                "releaseYear" to game.releaseYear,
                "title" to game.title
            )


            db.collection("Games").document(id)
                .set(hash)
                .addOnSuccessListener { Log.d("Success","Successfull written") }
                .addOnFailureListener { e -> Log.w("Failure", "Error writing Document",e)}
                .await()
        }

        suspend fun searchGame(title:String): Game{
            val db = Firebase.firestore
            val ref = db.collection("Games")
            var game: Game = Game()
            val query = ref.whereEqualTo("title",title)
                .get()
                .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    game = document.toObject(Game::class.java)
                    game.id = document.id
                }
            }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                }
                .await()
            return game
        }
         suspend fun getGameById(id:String?): Game{
            val db = Firebase.firestore
            var game: Game = Game()
            if(id != null) {
                game = Game(id)
                val ref = db.collection("Games").document(id)
                val query = ref
                    .get()
                    .addOnSuccessListener { document ->
                        if(document != null) {
                            Log.d("Database",id)
                            Log.d("Database",document.toString() +" found")
                            game = document.toObject(Game::class.java)!!
                            Log.d("Database",game.title)

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("TAG", "Error getting documents: ", exception)
                    }
                    .await()
            }

            return game
        }

    }


}