package com.example.semesterprojekt.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
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
        suspend fun getLists(gameLists: ArrayList<GameList>): ArrayList<GameList> {
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
                                gameLists.add(gameList)
                            }
                        }

                    }
                }
                .addOnFailureListener{
                    Log.d("Failure", "not able to get Lists")
                }
                .await()
            return gameLists
        }

       /* suspend fun listenToChange(
            gameListArrayList: ArrayList<GameList>
        ): ArrayList<GameList>{
            val db = Firebase.firestore
            db.collection("users").document(getUid())
                .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val documents = snapshot.documentChanges

                    for (change in documents) {
                        val gameList: GameList = change.document.toObject(GameList::class.java)
                        gameList.title = change.document.title
                        val oldGameList = getGoalsById(goal.Id!!, goalArrayList)
                        if (oldGameList != null){
                            gameListArrayList.remove(oldGameList)
                        }
                        gameListArrayList.add(gameList)
                    }
                }
            }
            return gameListArrayList
        }*/

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
            val id: String = game.id

            val hash = hashMapOf(
                "id" to game.id,
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
             var gameView = Game()
            if(id != "dummyId") {
                db.collection("Games").document(id!!)
                    .get()
                    .addOnSuccessListener { document ->
                        if(document != null) {
                            Log.d("Database",id)
                            Log.d("Database",document.toString() +" found")
                            val game = document.toObject(Game::class.java)!!
                            Log.d("Database",game.title)
                            if (game != null){
                                gameView = game
                            }

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("TAG", "Error getting documents: ", exception)
                    }
                    .await()
            }
             return gameView
        }

        suspend fun savaData(stars: Double, review: String, hours: Double,userId: String, gameId: String){
            val db = Firebase.firestore

            val hashRating = hashMapOf(
                "Stars" to stars,
                "UserId" to userId,
                "GameId" to gameId
            )

            val hashReview = hashMapOf(
                "Text" to review,
                "UserId" to userId,
                "GameId" to gameId
            )

            val hashPlaytime = hashMapOf(
                "Anzahl" to hours,
                "UserId" to userId,
                "GameId" to gameId
            )

            db.collection("Ratings").document(UUID.randomUUID().toString())
                .set(hashRating)
                .addOnSuccessListener { Log.d("Success","Successfull written") }
                .addOnFailureListener { e -> Log.w("Failure", "Error writing Document",e)}
                .await()

            db.collection("Review").document(UUID.randomUUID().toString())
                .set(hashReview)
                .addOnSuccessListener { Log.d("Success","Successfull written") }
                .addOnFailureListener { e -> Log.w("Failure", "Error writing Document",e)}
                .await()

            db.collection("Playtime").document(UUID.randomUUID().toString())
                .set(hashPlaytime)
                .addOnSuccessListener { Log.d("Success","Successfull written") }
                .addOnFailureListener { e -> Log.w("Failure", "Error writing Document",e)}
                .await()
        }

        suspend fun getAvgRating(gameId: String): Double{
            val db = Firebase.firestore
            var holder: Any?
            var total: Double = 0.0
            var count: Double  = 0.0
            var avg: Double = 0.0

            db.collection("Ratings").whereEqualTo("GameId", gameId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("TAG", "${document.id} => ${document.data}")
                        holder =  document.get("Stars")
                        if( holder != null){
                            total += holder as Double
                        }
                        count += 1.0
                        Log.d("RatingID",gameId)
                        Log.d("Rating",total.toString())
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                    count = 1.0
                }
                .await()
            if(count.toInt() == 0){
                return avg
            }
            avg = total/count
            Log.d("AVGStars", avg.toString())
            return avg

        }

        suspend fun getAvgHours(gameId: String): Double{
            val db = Firebase.firestore
            var holder: Any?
            var total: Double = 0.0
            var count: Double  = 0.0
            var avg: Double = 0.0

            db.collection("Playtime").whereEqualTo("GameId", gameId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("TAG", "${document.id} => ${document.data}")
                        holder =  document.get("Anzahl")
                        Log.d("holder", holder.toString())
                        if( holder != null){
                            total += holder as Double
                        }
                        count =+ 1.0
                        Log.d("Hours",total.toString())
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                    count = 1.0
                }
                .await()
            if(count.toInt() == 0){
                return avg
            }
            avg = total/count
            Log.d("AVGHOUR", avg.toString())
            return avg

        }
    }



}