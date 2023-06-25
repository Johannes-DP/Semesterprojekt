package com.example.semesterprojekt.screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.semesterprojekt.models.ListItemSelectable
import com.example.semesterprojekt.models.Platform

data class ListDetailUiState(
    val platform: List<Platform> = listOf(),
    var selectablePlatformItems: SnapshotStateList<ListItemSelectable> = Platform.values().toList().map(){ platform ->
        ListItemSelectable(title=platform.toString())
    }.toMutableStateList()
)

fun ListDetailUiState.selectPlatform(item: ListItemSelectable): List<Platform>{
    selectablePlatformItems.find {it.title == item.title}?.let {platform ->
        platform.isSelected = !platform.isSelected
    }
    return selectablePlatformItems.filter{ item -> item.isSelected }.map { listItemSelectable ->
        Platform.valueOf(listItemSelectable.title)
    }
}

