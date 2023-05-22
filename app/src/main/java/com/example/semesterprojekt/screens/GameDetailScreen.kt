package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getGameLists
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.getGames
import com.example.semesterprojekt.widgets.*

@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId:String?

){
    val games = getGames()
    var game = games[0]
    for (item: Game in games) {
        if (item.id == gameId) {
            game = item
        }
    }
    Scaffold(topBar = {
        OtherTopAppBar(
            arrowBackClicked = {navController.popBackStack()},
            title = " "+ game.title,
            menuContent = {
                DropdownMenuItem(onClick = { /*TODO Navigate to EditProfileScreen*/ }) {
                    Row {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Profile", modifier = Modifier.padding(4.dp))
                        Text(text = "Edit Profile", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }

                }
                /*TODO Add more Items*/
            }
        )

        }) {padding ->
        Column(modifier = Modifier.padding(padding), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                elevation = 5.dp,

                ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        GameImage(imageUrl = game.image)
                    }
                }
            }
            GameName(name = game.title, MaterialTheme.typography.h5)
            GameDetails(game = game)
        }
    }
}




