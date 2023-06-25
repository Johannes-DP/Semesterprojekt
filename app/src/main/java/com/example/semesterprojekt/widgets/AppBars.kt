package com.example.semesterprojekt.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.semesterprojekt.screens.Screen

@Composable
fun HomeTopAppBar(
    title: String = "default",
    menuContent: @Composable () -> Unit
){
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                menuContent()
            }
        }
    )
}

@Composable
fun OtherTopAppBar(
    arrowBackClicked: () -> Unit = {},
    title: String = "default",
    menuContent: @Composable () -> Unit
){
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = arrowBackClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, "getBack")

            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                menuContent()
            }
        }
    )
}

@Composable
fun MinimalisticAppBar(
    arrowBackClicked: () -> Unit = {},
    title: String = "default",
){
    TopAppBar(
        title = { Text( title) },
        navigationIcon = {
            IconButton(onClick = arrowBackClicked ) {
                Icon(imageVector = Icons.Default.ArrowBack, "getBack")

            }
        })
}

@Composable
fun DetailScreenAppBar(
    arrowBackClicked: () -> Unit = {},
    title: String = "default",
    menuContent: @Composable () -> Unit
){
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = arrowBackClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, "getBack")

            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                menuContent()
            }
        }
    )
}

@Composable
fun EditTopAppBar(
    arrowBackClicked: () -> Unit = {},
    title: String = "default"
){

    TopAppBar(
        title = { Text("Editing: " + title) },
        navigationIcon = {
            IconButton(onClick = { arrowBackClicked }) {
                Icon(imageVector = Icons.Default.ArrowBack, "getBack")

            }
        },
        actions = {
            IconButton(onClick = { /*TODO: add list saving*/ }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save")
            }
        }
    )
}

@Composable
fun RegistrationTopBar(
    arrowBackClicked: () -> Unit = {},
    title: String ="Default"
){
    TopAppBar(
        title = { Text(title) }
    )
}