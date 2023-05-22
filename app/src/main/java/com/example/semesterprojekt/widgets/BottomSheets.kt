package com.example.semesterprojekt.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BottomSheetAddList() {
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(20.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Divider(modifier = Modifier
                .width(80.dp),
                thickness = 6.dp,)
        }

        Text(text = "Add List", style = MaterialTheme.typography.h5)

        Row(modifier = Modifier
            .fillMaxWidth())
        {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "testing",
                onValueChange = {})
        }

    }
}
@Composable
fun BottomSheetAddGame() {
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(20.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Divider(modifier = Modifier
            .width(80.dp),
            thickness = 6.dp,)
        }

        Row{
            Text(text = "Add Game", style = MaterialTheme.typography.h5)
        }


        Row(modifier = Modifier
            .fillMaxWidth())
        {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "testing",
                onValueChange = {})
        }

    }
}
