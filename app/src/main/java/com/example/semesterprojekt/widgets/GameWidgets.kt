package com.example.semesterprojekt.widgets

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(
    game: Game,
    gameList: GameList,
    onItemClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
    onLongClick: (String) -> Unit = {})
{
    Log.d("gamegrid", game.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .combinedClickable(
                onClick = { onItemClick(game.id) },
                onLongClick = { onLongClick(gameList.title) }),
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
                        GameImage(imageUrl = game.image)
                        //DeleteIcon(game, onDeleteClick)


                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GameName(game.title, MaterialTheme.typography.body2)
            //Text(text = "Movie Images", style = MaterialTheme.typography.h5)
        }
    }
}


@Composable
fun GameGrid(
    game: Game,
    onItemClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
    onLongClick: (String) -> Unit = {})
{
    Log.d("DetailScreenOpening", game.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .clickable {  onItemClick(game.id) },
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
                        GameImage(imageUrl = game.image)
                        //DeleteIcon(game, onDeleteClick)


                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GameName(game.title, MaterialTheme.typography.body2)
            //Text(text = "Movie Images", style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
fun EditGameList(game: Game){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(text = game.title)

        DeleteIcon(game = game, onDeleteClick = { gameId -> Log.d("GameDelete", gameId)})
    }
    Divider()
}
@Composable
fun GameImage(imageUrl: String) {
    if (imageUrl != "null"){
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
            Column() {
                Spacer(modifier = Modifier.height(70.dp))
                Text(text = "No Image Found", style = MaterialTheme.typography.body2)
            }

        }
    }
}

@Composable
fun DeleteIcon(game: Game, onDeleteClick: (String) -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            tint = MaterialTheme.colors.primary,
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Movie",
            modifier = Modifier.clickable { onDeleteClick(game.id) }
        )
    }
}

@Composable
fun GameName(name: String, style: TextStyle){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        //verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, style = style)
    }
}

@Composable
fun GameDetails(game: Game){
    GameDetailRow(category = "Rating: ", argument = game.rating.toString(), category2 = "Amount: ") //argument2 = game.ratingsCount.toString())
    GameDetailRow(category = "Released in: ", game.releaseYear.toString())
    GameDetailRow(category = "Developer: ", game.developer)
    GameDetailRow(category = "Publisher: ", game.publisher)
    GameDetailRow(category = "Platform: ", game.platform.toString())
}

@Composable
fun GameDetailRow(category: String, argument: String,category2: String = "", argument2: String = ""){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = category + argument,style = MaterialTheme.typography.body1)
        Text(text = category2 + argument2,style = MaterialTheme.typography.body1)

    }
}