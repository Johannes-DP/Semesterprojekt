package com.example.semesterprojekt.data

import android.util.Log
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.Platform
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class ListRepositoryImpl : ListRepository {
    override val db = FirebaseFirestore.getInstance()

    override suspend fun firebaseSignUp(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        addUserToCollection()

    }

    override suspend fun firebaseLogIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun getLists(gameLists: ArrayList<GameList>): ArrayList<GameList> {
        if (getUid() != "") {
            db.collection("users").document(getUid()).get().addOnSuccessListener { fieldSnapshot ->
                    if (fieldSnapshot.data?.isNotEmpty() == true) {
                        val list = fieldSnapshot.data
                        if (list != null) {
                            for (document in list) {
                                val gameList = GameList(
                                    document.key,
                                    document.value as List<DocumentReference>,
                                    ArrayList()
                                )
                                gameLists.add(gameList)
                            }
                        }

                    }
                }.addOnFailureListener {
                    Log.d("Failure", "not able to get Lists")
                }.await()
        }
        return gameLists
    }


    override suspend fun getGames2(
        reference: DocumentReference, gameArrayList: ArrayList<Game>
    ): ArrayList<Game> {

        db.collection("Games").document(reference.id).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.data?.isNotEmpty() == true) {
                    val game = documentSnapshot.toObject(Game::class.java)
                    if (game != null) {
                        gameArrayList.add(game)
                    }
                }
            }.addOnFailureListener {
                Log.d("Failure", "not able to get games")
            }.await()
        return gameArrayList
    }

    override suspend fun getListById(id: String?): GameList {
        val gameLists = ArrayList<GameList>()
        var gameList = GameList("", emptyList(), ArrayList())
        getLists(gameLists)
        for (item in gameLists) {
            if (item.title == id) {
                gameList = item
                for (game in gameList.gameRefs) {
                    getGames2(game, gameList.games)
                }

            }
        }
        return gameList
    }

    override suspend fun addUserToCollection() {

        val uID = Firebase.auth.currentUser!!.uid
        Log.d("UID", "this is the uid$uID")

        val docData = hashMapOf(

            "Favorites" to arrayListOf(db.document("Games" + "/Game1")),
            "Wishlist" to arrayListOf(),
            "Played" to arrayListOf(),
        )

        db.collection("users").document(uID).set(docData)
            .addOnSuccessListener { Log.d("testing", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.d("testing tag", "Error writing document $e") }
        removeGameFromList("Game1", "Favorites")
    }

    override suspend fun addGameToList(
        game: String, listName: String
    ) {
        if (game != "dummyId") {
            val userRef = db.collection("users").document(getUid())

            userRef.update(listName, FieldValue.arrayUnion(db.document("Games/$game")))
                .addOnSuccessListener { Log.d("Success", "Successfully written") }
                .addOnFailureListener { e -> Log.w("Failure", "Error writing Document", e) }
        }
    }

    override suspend fun removeGameFromList(
        game: String, listName: String
    ) {
        val userRef = db.collection("users").document(getUid())

        userRef.update(listName, FieldValue.arrayRemove(db.document("Games/$game")))
            .addOnSuccessListener { Log.d("Success", "Successfully written") }
            .addOnFailureListener { e -> Log.w("Failure", "Error writing Document", e) }
    }

    override suspend fun filterList(id: String?, platforms: List<Platform>): GameList {
        val filteredGameList = GameList(id!!, emptyList(), ArrayList())
        val fullGameList = getListById(id)
        for (platform in platforms) {
            for (game in fullGameList.games) {
                if (game.platform.contains(platform)) {
                    if (!filteredGameList.games.contains(game)) {
                        filteredGameList.games.add(game)
                    }

                }
            }
        }
        return filteredGameList
    }

    private fun getUid(): String {
        return Firebase.auth.currentUser?.uid ?: ""
    }

    override fun logout() {
        Firebase.auth.signOut()
    }

    override suspend fun addGameToFirebase(game: Game) {
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


        db.collection("Games").document(id).set(hash)
            .addOnSuccessListener { Log.d("Success", "Successfully written") }
            .addOnFailureListener { e -> Log.w("Failure", "Error writing Document", e) }.await()
    }

    override suspend fun searchGame(title: String): Game {
        val ref = db.collection("Games")
        var game = Game()
        ref.whereEqualTo("title", title).get().addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    game = document.toObject(Game::class.java)
                    game.id = document.id
                }
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }.await()
        return game
    }

    override suspend fun getGameById(id: String?): Game {
        var gameView = Game()
        if (id != "dummyId") {
            db.collection("Games").document(id!!).get().addOnSuccessListener { document ->
                    if (document != null) {
                        val game = document.toObject(Game::class.java)!!
                        gameView = game
                    }
                }.addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                }.await()
        }
        return gameView
    }

    override suspend fun savaData(
        stars: Double, review: String, hours: Double, gameId: String
    ) {

        val hashRating = hashMapOf(
            "Stars" to stars, "UserId" to getUid(), "GameId" to gameId
        )

        val hashReview = hashMapOf(
            "Text" to review, "UserId" to getUid(), "GameId" to gameId
        )

        val hashPlaytime = hashMapOf(
            "Anzahl" to hours, "UserId" to getUid(), "GameId" to gameId
        )

        db.collection("Ratings").document(UUID.randomUUID().toString()).set(hashRating)
            .addOnSuccessListener { Log.d("Success", "Successfully written") }
            .addOnFailureListener { e -> Log.w("Failure", "Error writing Document", e) }.await()

        db.collection("Review").document(UUID.randomUUID().toString()).set(hashReview)
            .addOnSuccessListener { Log.d("Success", "Successfully written") }
            .addOnFailureListener { e -> Log.w("Failure", "Error writing Document", e) }.await()

        db.collection("Playtime").document(UUID.randomUUID().toString()).set(hashPlaytime)
            .addOnSuccessListener { Log.d("Success", "Successfully written") }
            .addOnFailureListener { e -> Log.w("Failure", "Error writing Document", e) }.await()
    }

    override suspend fun getAvgRating(gameId: String): Double {
        var holder: Any?
        var total = 0.0
        var count = 0.0
        var avg = 0.0

        db.collection("Ratings").whereEqualTo("GameId", gameId).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    holder = document.get("Stars")
                    if (holder != null) {
                        total += holder as Double
                    }
                    count += 1.0
                    //Log.d("RatingID", gameId)
                    //Log.d("Rating", total.toString())
                }
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
                count = 1.0
            }.await()
        if (count.toInt() == 0) {
            return avg
        }
        avg = total / count
        return avg

    }

    override suspend fun getAvgHours(gameId: String): Double {
        var holder: Any?
        var total = 0.0
        var count = 0.0
        var avg = 0.0

        db.collection("Playtime").whereEqualTo("GameId", gameId).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    holder = document.get("Anzahl")
                    //Log.d("holder", holder.toString())
                    if (holder != null) {
                        total += holder as Double
                    }
                    count += 1.0
                    //Log.d("Hours", total.toString())
                }
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
                count = 1.0
            }.await()
        if (count.toInt() == 0) {
            return avg
        }
        avg = total / count
        return avg

    }

    override suspend fun getReviews(gameId: String): ArrayList<String> {
        val reviews = ArrayList<String>()
        var holder: String

        db.collection("Review").whereEqualTo("GameId", gameId).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    holder = document.get("Text") as String
                    Log.d("String", holder)
                    reviews.add(holder)
                }
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }.await()
        return reviews
    }

    override suspend fun addList(title: String) {
        val hash = hashMapOf(
            title to arrayListOf(
                db.document("Games" + "/Game1")
            )
        )
        db.collection("users").document(getUid()).set(hash, SetOptions.merge())
            .addOnSuccessListener {
                Log.d("DB", "new List created")
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }.await()
        removeGameFromList("Game1", title)
    }

    override suspend fun deleteList(title: String) {
        val updates = hashMapOf<String, Any>(
            title to FieldValue.delete()
        )

        db.collection("users").document(getUid()).update(updates).addOnSuccessListener {
                Log.d("DB", "Look DB List")
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }.await()
    }
}