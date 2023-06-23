package com.example.semesterprojekt.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.getGameLists
import com.example.semesterprojekt.viewmodels.UserStateViewModel
import com.example.semesterprojekt.widgets.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userModel: UserStateViewModel
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
            skipHalfExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetAddList()
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
    ) {

        Scaffold(topBar = {
            HomeTopAppBar(
                title = "Your Lists ",
                menuContent = {
                    DropdownMenuItem(onClick = { navController.navigate(Screen.AddGameScreen.route) }) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Add Game",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(
                                text = "Add Game", modifier = Modifier
                                    .width(100.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                    DropdownMenuItem(onClick = {
                        userModel.logout()
                        navController.popBackStack()
                    }) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "Logout",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(
                                text = "Logout", modifier = Modifier
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
                    Icon(Icons.Filled.Add, "Add List")
                }

            }) { padding ->
            BackHandler(enabled = modalBottomSheetState.isVisible) {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
            MainContent(
                modifier = Modifier.padding(padding),
                navController = navController
            )
        }
    }
}


@Composable
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
    gameLists: List<GameList> = getGameLists())
{
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(gameLists){ gameList ->
            GameListGrid(
                gameList = gameList,
                onItemClick = {listId ->
                    navController.navigate(Screen.ListDetailScreen.addId(listId))
                }
            )

        }
    }
}
