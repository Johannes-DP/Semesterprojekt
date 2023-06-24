package com.example.semesterprojekt.models

import java.util.*

class GameNew(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val releaseYear: String,
    val publisher: String,
    val developer: String,
    val platform: List<String>,
    val image: String,
    val rating: String,
    // val ratingsCount: Int
){
    constructor(): this("dummyId","dummyTitle", "2512", "dummyPub0", "dummyDev", listOf("dummy1","dummy2"), "dummyIm", "3.2f" )
}