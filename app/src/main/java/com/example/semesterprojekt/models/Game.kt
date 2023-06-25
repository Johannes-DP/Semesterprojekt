package com.example.semesterprojekt.models

class Game(
    var id: String,
    var title: String,
    var releaseYear: String,
    var publisher: String,
    var developer: String,
    var platform: List<Platform>,
    var image: String,
    var rating: String,
    var avgRating: Double,
    var avgHours: Double
) {
    constructor() : this(
        "dummyId",
        "dummyTitle",
        "2512",
        "dummyPub0",
        "dummyDev",
        listOf(Platform.PC),
        "dummyIm",
        "3.2f",
        0.0,
        0.0
    )

    constructor(id: String) : this(
        id,
        "dummyTitle",
        "2512",
        "dummyPub0",
        "dummyDev",
        listOf(Platform.PC),
        "dummyIm",
        "3.2f",
        0.0,
        0.0
    )

}