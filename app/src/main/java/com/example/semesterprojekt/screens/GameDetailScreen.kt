package com.example.semesterprojekt.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Sleep
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.semesterprojekt.models.GameList
import com.example.semesterprojekt.models.Game

import com.example.semesterprojekt.repository.AuthRepository
import com.example.semesterprojekt.viewmodels.*
import com.example.semesterprojekt.widgets.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait


@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId: String?,
    userModel: UserStateViewModel

) {

    val gameViewModel = GameViewModel(gameId)
    val game by gameViewModel.gameState.collectAsState()

 /*   val factory = DetailViewModelFactory(repository = AuthRepository())
    val detailViewModel: DetailViewModel = viewModel(factory = factory)

    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        detailViewModel.getGameById(gameId)
    }

    var game = detailViewModel.game
*/
    Scaffold(topBar = {
        DetailScreenAppBar(
            arrowBackClicked = { navController.popBackStack() },
            title = " " + game.title,
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.ReviewScreen.addId(game.id))}) {
                    Row{
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Write Review",
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Write Review",
                            modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)
                        )
                    }
                }
            }
        )

    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                elevation = 5.dp,

                ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        GameImage(imageUrl = game.image)
                    }
                }
            }
            GameName(name = game.title, MaterialTheme.typography.h5)
            GameDetails(game = game)
            GameReviews(gameViewModel)
            Log.d("here first?", game.title)
        }
    }
}

@Composable
    fun GameReviews(
        gameViewModel: GameViewModel
    ){

    val reviews by gameViewModel.reviewState.collectAsState()

    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        GameName(name = "Reviews", MaterialTheme.typography.h5)
        for(items in reviews) {
            TextField(
                readOnly = true,
                value = items,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}




