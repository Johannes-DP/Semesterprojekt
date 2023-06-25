package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.semesterprojekt.R
import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.AddGameViewModel
import com.example.semesterprojekt.viewmodels.AddGameViewModelFactory
import com.example.semesterprojekt.widgets.MinimalisticAppBar
import com.example.semesterprojekt.widgets.SimpleTextField
import kotlinx.coroutines.launch

@Composable
fun AddGameScreen(
    navController: NavController
) {
    val factory = AddGameViewModelFactory(repository = AuthRepository())
    val addGameModel: AddGameViewModel = viewModel(factory = factory)

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MinimalisticAppBar(
                arrowBackClicked = { navController.popBackStack() },
                title = "Add Game"
            )})
    { padding ->
        MainContent(
            Modifier.padding(padding),
            addGameModel = addGameModel,
            navController = navController
        )}
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    addGameModel: AddGameViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(10.dp)
    ){
        GameBody(
            addGameUiState = addGameModel.addGameUiState,
            onGameValueChange = { newUiState, event -> addGameModel.updateUiState(newUiState, event)},
            onSaveClick = {
                coroutineScope.launch { addGameModel.addGameToFirebase() }
                navController.popBackStack()
            })
    }
}


@Composable
fun GameBody(
    addGameUiState: AddGameUiState,
    onGameValueChange: (AddGameUiState, AddGameUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
        ){
        GameInputForm(addGameUiState = addGameUiState, onGameValueChange = onGameValueChange)
        Button(
            enabled = addGameUiState.actionEnabled,
            onClick = onSaveClick
            ){
            Text(text = "Add Game")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameInputForm(
    addGameUiState: AddGameUiState,
    onGameValueChange: (AddGameUiState, AddGameUiEvent) -> Unit
){
    SimpleTextField(
        value =addGameUiState.title , 
        label = "Enter Title",
        isError = addGameUiState.titleErr,
        errMsg = "Title is required",
        onChange = {input ->
            onGameValueChange(addGameUiState.copy(title = input), AddGameUiEvent.TitleChanged)
        })

    SimpleTextField(
        value = addGameUiState.releaseYear,
        label = "Enter Year",
        isError = addGameUiState.yearErr,
        errMsg = "Year is required" + addGameUiState.yearErr,
        onChange = {input ->
            onGameValueChange(addGameUiState.copy(releaseYear = input), AddGameUiEvent.YearChanged)
        })

    SimpleTextField(
        value =addGameUiState.publisher ,
        label = "Enter Publisher",
        isError = addGameUiState.pubErr,
        errMsg = "publisher is required",
        onChange = {input ->
            onGameValueChange(addGameUiState.copy(publisher = input), AddGameUiEvent.PublisherChanged)
        })

    SimpleTextField(
        value =addGameUiState.developer ,
        label = "Enter Developer",
        isError = addGameUiState.devErr,
        errMsg = "Developer is required",
        onChange = {input ->
            onGameValueChange(addGameUiState.copy(developer = input), AddGameUiEvent.DeveloperChanged)
        })

    LazyHorizontalGrid(
        modifier = Modifier.height(100.dp),
        rows = GridCells.Fixed(3))
    {
        items(addGameUiState.selectablePlatformItems){platformItem ->
            Chip(
                modifier = Modifier.padding(2.dp),
                colors = ChipDefaults.chipColors(
                    backgroundColor = if(platformItem.isSelected)
                        colorResource(id = R.color.purple_200)
                    else
                        colorResource(id = R.color.white)
            ),
                onClick = {
                onGameValueChange(addGameUiState.copy(platform = addGameUiState.selectPlatform(platformItem)), AddGameUiEvent.PlatformChanged)
            }){
                Text(text = platformItem.title)
            }
        }
    }

    if(addGameUiState.platErr){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "Platforms are required",
            fontSize = 14.sp,
            color = MaterialTheme.colors.error
        )
    }

    SimpleTextField(
        value =addGameUiState.image ,
        label = "Enter Image",
        isError = addGameUiState.imgErr,
        errMsg = "Image is required",
        onChange = {input ->
            onGameValueChange(addGameUiState.copy(image = input), AddGameUiEvent.ImageChanged)
        })

    SimpleTextField(
        value =addGameUiState.rating ,
        label = "Enter Rating",
        isError = addGameUiState.ratingErr,
        errMsg = "Metacritic Rating is required",
        onChange = {input ->
            onGameValueChange(addGameUiState.copy(rating = input), AddGameUiEvent.RatingChanged)
        })
}