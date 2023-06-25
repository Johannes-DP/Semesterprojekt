package com.example.semesterprojekt.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.data.ListRepositoryImpl
import com.example.semesterprojekt.viewmodels.*
import com.example.semesterprojekt.widgets.*


@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId: String?

) {

    val gameViewModel = GameViewModel(gameId, repository = ListRepositoryImpl())
    val game by gameViewModel.gameState.collectAsState()

    Scaffold(topBar = {
        DetailScreenAppBar(
            arrowBackClicked = { navController.popBackStack() },
            title = " " + game.title,
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.ReviewScreen.addId(game.id)) }) {
                    Row {
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
        }
    }
}

@Composable
fun GameReviews(
    gameViewModel: GameViewModel
) {

    val reviews by gameViewModel.reviewState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        GameName(name = "Reviews", MaterialTheme.typography.h5)
        for (items in reviews) {
            TextField(
                readOnly = true,
                value = items,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}




