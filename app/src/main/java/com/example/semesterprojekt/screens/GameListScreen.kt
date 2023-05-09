package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getGameLists
import com.example.semesterprojekt.widgets.GameListGrid
import com.example.semesterprojekt.widgets.HomeTopAppBar

@Composable
fun GameListScreen(
    navController: NavController
){
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Your Lists",
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
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO Move to detailed List Screen*/ })
            {
                Icon(Icons.Filled.Add, "Add List")
            }

        }) {padding ->
        MainContent(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

/*@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController
){
    GameLists(
        modifier = modifier,
        navController = navController
    )
}

@Composable
fun GameLists(
    modifier: Modifier,
    navController: NavController,
    gameLists: List<GameList> = getGameLists()
)
{
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(gameLists){ gameList ->
            GameListGrid(
                gameList = gameList
            )

        }
    }
}*/
