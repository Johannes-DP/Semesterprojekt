package com.example.semesterprojekt.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.viewmodels.GameListViewModel
import com.example.semesterprojekt.viewmodels.ListDetailViewModel
import com.example.semesterprojekt.viewmodels.UserStateViewModel
import com.example.semesterprojekt.widgets.*
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListDetailScreen(
    navController: NavController,
    listId:String?,
    userModel: UserStateViewModel,

) {
    val listDetailViewModel = ListDetailViewModel(listId)
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
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
    ) {
        Scaffold(topBar = {
            OtherTopAppBar(
                arrowBackClicked = {
                    navController.popBackStack()
                },
                title = " " + listId, //TODO
                menuContent = {
                    DropdownMenuItem(onClick = {
                        navController.navigate(
                            Screen.ModifyListScreen.addId(
                                "1"//gameList.id
                            )
                        )
                    }) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit List",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(
                                text = "Edit List", modifier = Modifier
                                    .width(100.dp)
                                    .padding(4.dp)
                            )
                        }

                    }
                    DropdownMenuItem(onClick = { Log.d("Clear List", /*gameList.id*/"1") }) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear List",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(
                                text = "Clear List", modifier = Modifier
                                    .width(100.dp)
                                    .padding(4.dp)
                            )
                        }

                    }

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
                    /*items(listDetailUiState.selectablePlatformItems) { platformItem ->
                        Chip(
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (platformItem.isSelected)
                                    colorResource(id = com.example.semesterprojekt.R.color.purple_200)
                                else
                                    colorResource(id = com.example.semesterprojekt.R.color.white)
                            ),
                            onClick = {listDetailUiState.copy(platform= listDetailUiState.selectPlatform(platformItem))
                            coroutineScope.launch {
                                listDetailViewModel.filterList(listId,listDetailUiState.platform)
                                Log.d("in onclick", listDetailUiState.platform.toString())
                            }
                            }) {
                            Text(text = platformItem.title)
                        }
                    }*/
                    items(listDetailUiState.selectablePlatformItems) { platformItem ->
                        Chip(
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (platformItem.isSelected)
                                    colorResource(id = com.example.semesterprojekt.R.color.purple_200)
                                else
                                    colorResource(id = com.example.semesterprojekt.R.color.white)
                            ),
                            onClick = {listDetailUiState.copy(platform= listDetailUiState.selectPlatform(platformItem))
                                coroutineScope.launch {
                                    listDetailViewModel.filterList(listId,listDetailUiState.platform)
                                    Log.d("in onclick", listDetailUiState.selectablePlatformItems.size.toString())
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
                                gameList = gameListState,
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






