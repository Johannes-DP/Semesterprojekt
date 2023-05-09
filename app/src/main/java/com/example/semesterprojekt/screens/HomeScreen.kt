package com.example.semesterprojekt.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semesterprojekt.widgets.HomeTopAppBar

@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Your Lists",
            menuContent = {
                DropdownMenuItem(onClick = { /*TODO Navigate to EditProfileScreen*/ }) {
                    Row {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Profile", modifier = Modifier.padding(4.dp))
                        Text(text = "Edit Profile", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }

                }
                /*TODO Add more Items*/
            }
        )
    }) {padding ->
        MainContent(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController
){
    GameLists(
        modifier = modifier,
        navController = navController
    )
}

@Composable
fun GameLists(
    modifier: Modifier,
    navController: NavController
){

}

