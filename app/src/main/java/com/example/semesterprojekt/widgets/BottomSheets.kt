package com.example.semesterprojekt.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.semesterprojekt.viewmodels.GameListViewModel
import com.example.semesterprojekt.viewmodels.ListDetailViewModel
import com.example.semesterprojekt.viewmodels.SearchGameViewModel
import kotlinx.coroutines.launch


@Composable
fun BottomSheetAddList(
    gameListViewModel: GameListViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    var title by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                modifier = Modifier
                    .width(80.dp),
                thickness = 6.dp,
            )
        }

        Text(text = "Add List", style = MaterialTheme.typography.h5)

        Row(
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                SimpleTextField(
                    value = title,
                    label = "List Title",
                    isError = false,
                    errMsg = "Pls enter Title",
                    onChange = { title = it }
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            gameListViewModel.addList(title)
                        }
                    }
                ) {
                    Text("Add List")
                }

            }
        }
    }
}

@Composable
fun BottomSheetAddGame(
    listDetailViewModel: ListDetailViewModel,
    searchViewModel: SearchGameViewModel,
    listId: String,
    onDetailClick: (String) -> Unit = {}
) {

    val coroutineScope = rememberCoroutineScope()
    var title by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                modifier = Modifier
                    .width(80.dp),
                thickness = 6.dp,
            )
        }

        Row {
            Text(text = "Add Game", style = MaterialTheme.typography.h5)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                SimpleTextField(
                    value = title,
                    label = "Title",
                    isError = false,
                    errMsg = "Pls enter Title",
                    onChange = { title = it }
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (!result){
                                searchViewModel.searchGame(title)
                            }
                            result = !result
                        }
                    }
                ) {
                    Text("Search Game")
                }
                if (result) {
                    if (searchViewModel.game.id == "dummyId") {
                        Text("No Game Found!")
                    } else {
                        GameSearchGrid(
                            game = searchViewModel.game,
                            onAddToListClick = { String ->
                                coroutineScope.launch {
                                    listDetailViewModel.addGameToList(
                                        listId,
                                        listDetailViewModel.getGameById(String)
                                    )
                                }
                            },
                            onDetailClick = onDetailClick
                        )
                    }
                }
            }
        }
    }
}
