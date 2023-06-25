package com.example.semesterprojekt.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.R
import com.example.semesterprojekt.models.Validator
import com.example.semesterprojekt.viewmodels.GameViewModel
import com.example.semesterprojekt.widgets.MinimalisticAppBar
import com.example.semesterprojekt.widgets.SimpleTextField
import kotlinx.coroutines.launch

@Composable
fun ReviewScreen(
    navController: NavController,
    gameId: String?
){
    val scaffoldState = rememberScaffoldState()
    val gameViewModel = GameViewModel(gameId)
    val game by gameViewModel.gameState.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar =  {
            MinimalisticAppBar(
                arrowBackClicked = { navController.popBackStack() },
                title = "Review " + game.title
            )
        }){ padding ->
        MainContent(Modifier.padding(padding),gameViewModel, navController)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel,
    navController: NavController
){
    val coroutineScope = rememberCoroutineScope()

    var starCount by remember {
        mutableStateOf(1)
    }

    var review by remember {
        mutableStateOf("")
    }

    var hours by remember {
        mutableStateOf("")
    }

    var counter = 1

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        LazyHorizontalGrid(
            modifier = Modifier.height(33.dp),
            rows = GridCells.Fixed(1))
        {
            items(5){stars ->
                Chip(
                    modifier = Modifier.padding(2.dp),
                    colors = ChipDefaults.chipColors(
                        backgroundColor = if(starCount >= stars+1){
                            MaterialTheme.colors.secondary
                        }
                        else
                            colorResource(id = R.color.white)
                    ),
                    onClick = {
                        starCount = stars+1
                    }){
                    Icon(imageVector = Icons.Default.Star,
                        contentDescription = "Stars")
                    counter += 1
                }
            }
        }

        SimpleTextField(
            value = review,
            label = "Review" ,
            isError = false,
            onChange = {review = it},
            singleLine = false
        )

        SimpleTextField(
            value = hours,
            label = "Played Time",
            isError = !Validator.validateHours(hours),
            onChange = {hours = it},
            keyboardType = KeyboardType.Number
        )

        Button(onClick = {
            if(Validator.validateHours(hours)){
                Log.d("Stars", starCount.toString())
                coroutineScope.launch {
                    gameViewModel.savaData(starCount.toDouble(),review,hours.toDouble(), gameViewModel.game.id)
                    navController.popBackStack()
                }
            }
        }) {
            Text(
                text = "Save Review"
            )
        }
    }

}