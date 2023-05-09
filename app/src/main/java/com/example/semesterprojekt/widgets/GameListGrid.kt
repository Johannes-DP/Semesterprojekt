package com.example.semesterprojekt.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.semesterprojekt.models.Game
import com.example.semesterprojekt.models.GameList

@Composable
fun GameListGrid(gameList: GameList/*, onItemClick: (String) -> Unit = {}*/){

    Card(
        modifier = Modifier
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