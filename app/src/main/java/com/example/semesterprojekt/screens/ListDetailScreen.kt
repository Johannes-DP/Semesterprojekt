package com.example.semesterprojekt.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getGameLists
import com.example.semesterprojekt.viewmodels.ListDetailViewModel
import com.example.semesterprojekt.widgets.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListDetailScreen(
    navController: NavController,
    listId:String?

) {
    val viewModel = ListDetailViewModel()
    val lists = getGameLists()
    var gameList = lists[0]
    for (item: GameList in lists) {
        if (item.id == listId) {
            gameList = item
        }
    }
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()
    val clearState: Boolean by viewModel.clearBool.collectAsState()
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
            skipHalfExpanded = true
        )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetAddGame()
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
    ) {
        Scaffold(topBar = {
            OtherTopAppBar(
                arrowBackClicked = {
                    Log.d(
                        "testing",
                        "hereListDetail"
                    ) /* navController.popBackStack(),*/
                },
                title = " " + gameList.title,
                menuContent = {
                    DropdownMenuItem(onClick = {
                        navController.navigate(
                            Screen.ModifyListScreen.addId(
                                gameList.id
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
                    DropdownMenuItem(onClick = { viewModel.onClearClicked() }) {
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

                    DropdownMenuItem(onClick = { viewModel.onDeleteClicked() }) {
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
                    /*TODO Add more Items*/
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
            AlertDialogs(
                show = showDialogState,
                clear = clearState,
                listId = listId,
                onConfirm = { listIDString, clearBool ->
                    viewModel.clearOrDeleteList(listIDString, clearBool)
                },
                onDismiss = {viewModel.onDialogDismiss()}

            )
            Column(modifier = Modifier.padding(padding)) {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(gameList.games) { game ->
                        GameGrid(
                            game = game,
                            gameList = gameList,
                            onItemClick = { gameId ->
                                navController.navigate(Screen.GameDetailScreen.addId(gameId))
                            },
                            onLongClick = { listId ->
                                navController.navigate(Screen.ModifyListScreen.addId(listId))
                            }

                        )

                    }
                }

            }
        }
    }
}





