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

    /*var gameListView = GameList("",emptyList<DocumentReference>(), ArrayList<Game>() )
    val gameListViewModel = GameListViewModel()
    val gameListsState by gameListViewModel.gameListsState.collectAsState()
    var index = 0
    Log.d("size", gameListsState.size.toString())
    if (!gameListsState.isEmpty()){
        for (i in 0 until gameListsState.size) {
            if (gameListsState[i].title == listId) {
                index = i
            }
        }
        gameListView = gameListsState[index]

    }*/



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
                onDetailClick = {String -> navController.navigate(Screen.GameDetailScreen.addId(String))},
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
                    if (!gameListState.games.isEmpty()) {
                        items(gameListState.games) { game ->
                            Log.d("heeeere", game.id)
                            Log.d("testingnow", game.toString())
                            GameGrid(
                                game = game,
                                gameList = gameListState,
                                onItemClick = { gameId ->
                                    Log.d("this is the current ID", gameId)
                                    navController.navigate(Screen.GameDetailScreen.addId(gameId))
                                }

                            ) { listId ->
                                navController.navigate(Screen.ModifyListScreen.addId(listId))
                            }

                        }
                    }
                }

            }
        }
    }
}






