package com.example.semesterprojekt.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getGameLists
import com.example.semesterprojekt.widgets.GameGrid
import com.example.semesterprojekt.widgets.HomeTopAppBar
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.getGames
import com.example.semesterprojekt.widgets.OtherTopAppBar

@Composable
fun ListDetailScreen(
    navController: NavController,
    listId:String?

){
    val lists = getGameLists()
    var gameList = lists[0]
    for (item: GameList in lists) {
        if (item.id == listId) {
            gameList = item
        }
    }
    Scaffold(topBar = {
        OtherTopAppBar(
            arrowBackClicked = {  Log.d("testing", "hereListDetail") /* navController.popBackStack(),*/},
            title = " "+ gameList.title,
            menuContent = {
                DropdownMenuItem(onClick = { /*TODO Delete List, Clear List*/ }) {
                    Row {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear List", modifier = Modifier.padding(4.dp))
                        Text(text = "Clear List", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }

                }
                /*TODO Add more Items*/
            }
        )
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO Move to Add Movie Screen*/ })
            {
                Icon(Icons.Filled.Add, "Add Movie")
            }

        }) {padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyVerticalGrid(columns = GridCells.Fixed(3)){
                items(gameList.games){ game ->
                    GameGrid(
                        game = game,
                        onItemClick = {gameId ->
                            navController.navigate(Screen.GameDetailScreen.addId(gameId))
                        }
                    )

                }
            }

        }
    }
}




