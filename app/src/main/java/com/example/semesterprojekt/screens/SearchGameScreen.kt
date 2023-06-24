package com.example.semesterprojekt.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.SearchGameViewModel
import com.example.semesterprojekt.viewmodels.SearchGameViewModelFactory
import com.example.semesterprojekt.viewmodels.UserStateViewModel
import com.example.semesterprojekt.widgets.GameGrid
import com.example.semesterprojekt.widgets.MinimalisticAppBar
import com.example.semesterprojekt.widgets.SimpleTextField
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.rpc.context.AttributeContext.Auth
import kotlinx.coroutines.launch

@Composable
fun SearchGameScreen(
    navController: NavController,
    userModel: UserStateViewModel,
){
    val factory = SearchGameViewModelFactory(repository = AuthRepository())
    val searchViewModel: SearchGameViewModel = viewModel(factory = factory)

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar =  {
        MinimalisticAppBar(
            arrowBackClicked = { navController.popBackStack() },
            title = "Search Game"
        )}){ padding ->
        MainContent(Modifier.padding(padding),searchViewModel, navController)
    }
}

@Composable
fun MainContent(
modifier: Modifier = Modifier,
searchViewModel: SearchGameViewModel,
navController: NavController,
){

    val coroutineScope = rememberCoroutineScope()
    var title by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf(false)
    }



    Column (
        modifier = modifier
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
                    searchViewModel.SearchGame(title)
                    result = !result
                }
            }
        ){
            Text("Search Game")
        }
        if(result){
                GameGrid(game = searchViewModel.game, onItemClick = { gameId ->
                    navController.navigate(Screen.GameDetailScreen.addId(gameId))})
        }
    }
}
