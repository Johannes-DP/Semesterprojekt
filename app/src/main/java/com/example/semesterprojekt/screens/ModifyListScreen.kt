package com.example.semesterprojekt.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getGameLists
import com.example.semesterprojekt.viewmodels.UserStateViewModel
import com.example.semesterprojekt.widgets.EditGameList
import com.example.semesterprojekt.widgets.EditTopAppBar
import com.example.semesterprojekt.widgets.GameGrid
import com.example.semesterprojekt.widgets.OtherTopAppBar

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ModifyListScreen(
    navController: NavController,
    listId:String?,
    userModel: UserStateViewModel

) {
    val lists = getGameLists()
    var gameList = lists[0]
    for (item: GameList in lists) {
        if (item.id == listId) {
            gameList = item
        }
    }

    var value by remember {
        mutableStateOf(gameList.title)
    }

    Scaffold(
        topBar = {
            EditTopAppBar(
                arrowBackClicked = {
                     navController.popBackStack()
                },
                title = " " + gameList.title,

                )
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = value,
                    onValueChange = { newTitle ->
                        value = newTitle
                    },
                    //enabled = false,
                    readOnly = true,
                )
            }
            Column(
                /*modifier = Modifier
                    .verticalScroll(rememberScrollState())*/
            )
            {
                LazyColumn(modifier = Modifier) {
                    items(gameList.games) { game ->
                        EditGameList(game = game)


                    }
                }
            }
        }
    }
}


