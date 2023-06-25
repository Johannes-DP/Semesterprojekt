package com.example.semesterprojekt.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.viewmodels.ListDetailViewModel
import com.example.semesterprojekt.viewmodels.SearchGameViewModel
import com.example.semesterprojekt.widgets.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListDetailScreen(
    navController: NavController,
    listId:String?

) {
    val listDetailViewModel = ListDetailViewModel(listId, repository = ListRepositoryImpl())
    val searchGameViewModel = SearchGameViewModel(repository = ListRepositoryImpl())
    val gameListState by listDetailViewModel.gameListState.collectAsState()
    val listDetailUiState = listDetailViewModel.listDetailUiState

    val modalBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
            skipHalfExpanded = true
        )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetAddGame(
                listDetailViewModel = listDetailViewModel,
                searchViewModel = searchGameViewModel,
                onDetailClick = { String ->
                    navController.navigate(
                        Screen.GameDetailScreen.addId(
                            String
                        )
                    )
                },
                listId = listId!!
            )
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
    ) {
        Scaffold(topBar = {
            OtherTopAppBar(
                arrowBackClicked = {
                    navController.popBackStack()
                },
                title = " " + listId, //TODO
                menuContent = {
                    DropdownMenuItem(onClick = {
                        coroutineScope.launch {
                            if(listId != null)
                                listDetailViewModel.deleteList(listId)
                            navController.popBackStack()
                        }

                    }) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete List",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(
                                text = "Delete List", modifier = Modifier
                                    .width(100.dp)
                                    .padding(4.dp)
                            )
                        }

                    }
                }
            )
        },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                })
                {
                    Icon(Icons.Filled.Add, "Add Movie")
                }

            }) { padding ->
            BackHandler(enabled = modalBottomSheetState.isVisible) {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }

            Column(modifier = Modifier.padding(padding), horizontalAlignment = Alignment.CenterHorizontally) {
                LazyHorizontalGrid(
                    modifier = Modifier.height(100.dp),
                    rows = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {

                    items(listDetailUiState.selectablePlatformItems) { platformItem ->
                        Chip(
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (platformItem.isSelected)
                                    colorResource(id = com.example.semesterprojekt.R.color.purple_200)
                                else
                                    colorResource(id = com.example.semesterprojekt.R.color.white)
                            ),
                            onClick = {
                                listDetailUiState.platform = listDetailUiState.selectPlatform(platformItem)

                                coroutineScope.launch {
                                    listDetailViewModel.filterList(listId,listDetailUiState.platform)
                                }
                            }) {
                            Text(text = platformItem.title)
                        }
                    }
                }
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    if (!gameListState.games.isEmpty()) {
                        items(gameListState.games) { game ->
                            GameGrid(
                                game = game,
                                onItemClick = { gameId ->
                                    navController.navigate(Screen.GameDetailScreen.addId(gameId))
                                },
                                onLongClick = {gameId ->
                                    coroutineScope.launch {
                                        listDetailViewModel.removeGameFromList(gameId,listId)

                                        navController.popBackStack()
                                        if(listId != null)
                                    navController.navigate(Screen.ListDetailScreen.addId(listId))
                                    }

                                }
                            )

                        }
                    }
                }

            }
        }
    }
}






