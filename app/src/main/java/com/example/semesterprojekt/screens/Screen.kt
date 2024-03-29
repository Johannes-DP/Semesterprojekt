package com.example.semesterprojekt.screens

const val DETAIL_ARGUMENT_KEY = "listId"
const val GAME_ARGUMENT_KEY = "gameId"

sealed class Screen(val route: String) {

    object MainScreen : Screen("main")

    object Registration : Screen("registration")

    object ListDetailScreen : Screen("listDetail/{$DETAIL_ARGUMENT_KEY}") {
        fun addId(id: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id)
        }
    }

    object GameDetailScreen : Screen("gameDetail/{$GAME_ARGUMENT_KEY}") {
        fun addId(id: String): String {
            return this.route.replace(oldValue = "{$GAME_ARGUMENT_KEY}", newValue = id)
        }
    }
    object AddGameScreen : Screen("addGame")

    object SearchGameScreen : Screen("searchGameScreen")

    object ReviewScreen : Screen("reviewScreen/{$GAME_ARGUMENT_KEY}") {
        fun addId(id: String): String {
            return this.route.replace(oldValue = "{$GAME_ARGUMENT_KEY}", newValue = id)
        }
    }
}



