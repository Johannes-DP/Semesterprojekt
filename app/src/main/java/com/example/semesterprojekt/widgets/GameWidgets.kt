package com.example.semesterprojekt.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.semesterprojekt.R
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList

@Composable
fun GameListGrid(gameList: GameList, onItemClick: (String) -> Unit = {}) {

    Card(
        modifier = Modifier
            .clickable {
                onItemClick(gameList.title)
            }
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = gameList.title, style = MaterialTheme.typography.h5)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(
    game: Game,
    onItemClick: (String) -> Unit = {},
    onLongClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .combinedClickable(
                onClick = { onItemClick(game.id) },
                onLongClick = { onLongClick(game.id) }),
        border = null,
        elevation = 0.dp
    ) {
        Column {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                elevation = 5.dp,
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        GameImage(imageUrl = game.image)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GameName(game.title, MaterialTheme.typography.body2)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(
    game: Game,
    onItemClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .combinedClickable(
                onClick = { onItemClick(game.id) }),
        border = null,
        elevation = 0.dp
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                elevation = 5.dp,
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        GameImage(imageUrl = game.image)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GameName(game.title, MaterialTheme.typography.body2)
        }
    }
}


@Composable
fun GameSearchGrid(
    game: Game,
    onAddToListClick: (String) -> Unit = {},
    onDetailClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        border = null,
        elevation = 0.dp
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                elevation = 5.dp,
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        GameImage(imageUrl = game.image)
                        AddToListIcon(game = game, onAddToListClick = onAddToListClick)
                        DetailIcon(game = game, onDetailClick = onDetailClick)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GameName(game.title, MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun GameImage(imageUrl: String) {
    if (imageUrl != "null") {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "GameImage",
            loading = {
                CircularProgressIndicator()
            }
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.default_picture),
            contentDescription = "No picture found"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Spacer(modifier = Modifier.height(70.dp))
                Text(text = "No Image Found", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun AddToListIcon(game: Game, onAddToListClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            tint = MaterialTheme.colors.secondary,
            imageVector = Icons.Default.Add,
            contentDescription = "AddGameToList",
            modifier = Modifier
                .clickable { onAddToListClick(game.id) }
                .size(70.dp)
        )
    }
}

@Composable
fun DetailIcon(game: Game, onDetailClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            tint = MaterialTheme.colors.secondary,
            imageVector = Icons.Default.Info,
            contentDescription = "ShowGameDetails",
            modifier = Modifier
                .clickable { onDetailClick(game.id) }
                .size(70.dp)
        )
    }
}


@Composable
fun GameName(name: String, style: TextStyle) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, style = style)
    }
}

@Composable
fun GameDetails(game: Game) {
    GameDetailRow(
        category = "Metascore: ",
        argument = game.rating,
        category2 = "User Rating: ",
        argument2 = game.avgRating.toString()
    )
    GameDetailRow(category = "Released in: ", game.releaseYear)
    GameDetailRow(category = "Developer: ", game.developer)
    GameDetailRow(category = "Publisher: ", game.publisher)
    GameDetailRow(category = "Platform: ", game.platform.toString())
    GameDetailRow(category = "Avg. played Time: ", game.avgHours.toString())

}

@Composable
fun GameDetailRow(
    category: String,
    argument: String,
    category2: String = "",
    argument2: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = category + argument, style = MaterialTheme.typography.body1)
        Text(text = category2 + argument2, style = MaterialTheme.typography.body1)

    }
}