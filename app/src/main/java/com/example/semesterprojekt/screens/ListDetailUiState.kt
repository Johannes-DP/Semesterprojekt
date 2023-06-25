package com.example.semesterprojekt.screens

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.semesterprojekt.models.ListItemSelectable
import com.example.semesterprojekt.models.Platform

data class ListDetailUiState(
    var platform: List<Platform> = listOf(),
    var selectablePlatformItems: SnapshotStateList<ListItemSelectable> = Platform.values().toList().map(){ platform ->
        ListItemSelectable(title=platform.toString())
    }.toMutableStateList()
)

fun ListDetailUiState.selectPlatform(item: ListItemSelectable): List<Platform>{
    selectablePlatformItems.find {it.title == item.title}?.let {platform ->
        platform.isSelected = !platform.isSelected
        Log.d("Select", platform.title)
        Log.d("Select", platform.isSelected.toString())
    }
    return selectablePlatformItems.filter{ item -> item.isSelected }.map { listItemSelectable ->
        Platform.valueOf(listItemSelectable.title)
    }
}

