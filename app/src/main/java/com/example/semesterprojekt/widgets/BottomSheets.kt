package com.example.semesterprojekt.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.GameListViewModel
import com.example.semesterprojekt.viewmodels.ListDetailViewModel
import com.example.semesterprojekt.viewmodels.SearchGameViewModel
import com.example.semesterprojekt.viewmodels.SearchGameViewModelFactory
import kotlinx.coroutines.launch


@Composable
fun BottomSheetAddList() {

    val gameListViewModel = GameListViewModel()
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
    listId: String,
    onDetailClick: (String) -> Unit = {}
) {

    val factory = SearchGameViewModelFactory(repository = AuthRepository())
    val searchViewModel: SearchGameViewModel = viewModel(factory = factory)
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
                            searchViewModel.searchGame(title)
                            result = !result
                        }
                    }
                ) {
                    Text("Search Game")
                }
                if (result) {
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
                        /*navController.navigate(Screen.GameDetailScreen.addId(gameId))*/
                    )
                }
            }
        }

    }
}
