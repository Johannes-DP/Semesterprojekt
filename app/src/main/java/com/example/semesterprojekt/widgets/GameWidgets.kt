package com.example.semesterprojekt.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.semesterprojekt.R
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList

@Composable
fun GameListGrid(gameList: GameList, onItemClick: (String) -> Unit = {}){

    Card(
        modifier = Modifier
            .clickable {
                onItemClick(gameList.id)
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
                        .padding(15.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = gameList.icon,
                        contentDescription = "Icon"
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Text(text = gameList.count.toString())
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    /*Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Icon"
                    )*/
                Text(text = gameList.title, style = MaterialTheme.typography.h5)
                }
            }
        }
    }

}

@Composable
fun GameGrid(
    game: Game,
    onItemClick: (String) -> Unit = {},
    onDeleteClick: (Game) -> Unit = {}) {

    Card(
        modifier = Modifier
            .clickable { onItemClick(game.id) }
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        border = null,
        elevation = 0.dp
    ) {
        Column() {

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
                        if (game.image != "null"){
                            GameImage(imageUrl = game.image)
                        } else {
                            Image(painter = painterResource(id = R.drawable.default_picture), contentDescription = "No picture found")
                        }
                        DeleteIcon(game, onDeleteClick)


                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GameName(game)
            //Text(text = "Movie Images", style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
fun GameImage(imageUrl: String) {
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
}

@Composable
fun DeleteIcon(game: Game, onDeleteClick: (Game) -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            tint = Color.White,
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Movie",
            modifier = Modifier.clickable { onDeleteClick(game) }
        )
    }
}

@Composable
fun GameName(game: Game){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = game.title, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun GameDetails(game: Game){
    GameImage(game.image)


}