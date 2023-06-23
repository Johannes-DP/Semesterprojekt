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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.viewmodels.GameListViewModel
import com.example.semesterprojekt.viewmodels.UserStateViewModel
import com.example.semesterprojekt.widgets.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListDetailScreen(
    navController: NavController,
    listId:String?,
    userModel: UserStateViewModel,
    gameListViewModel: GameListViewModel

) {
    var index = 0
    for (i in 0 until gameListViewModel.gameLists.size) {
        if (gameListViewModel.gameLists[i].title == listId) {
            index = i
        }
    }
    val gameListView = gameListViewModel.gameLists[index]
    Log.d("listdetail", gameListView.toString())

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

                    DropdownMenuItem(onClick = { Log.d("DeleteList", "1" /*gameList.id*/) }) {
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
            Column(modifier = Modifier.padding(padding)) {
                Log.d("test", "hello")
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    Log.d("test", "hello2")
                    Log.d("test", gameListView.games.toString())
                    items(gameListView.games) { game ->
                        Log.d("heeeere", "testing")
                        GameGrid(
                            game = game,
                            gameList = gameListView,
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






