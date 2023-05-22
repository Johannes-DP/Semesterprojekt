package com.example.semesterprojekt.viewmodels

import android.os.Debug
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListDetailViewModel {

    private val _showDialog = MutableStateFlow(false)
    private val _clearBool = MutableStateFlow(true)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()
    val clearBool: StateFlow<Boolean> = _clearBool.asStateFlow()

    fun onClearClicked(){
        _showDialog.value = true
        _clearBool.value = true
    }

    fun onDeleteClicked(){
        _showDialog.value = true
        _clearBool.value = false
    }


    fun onDialogDismiss(){
        _showDialog.value = false
    }

    fun deleteList(listId: String?){
        Log.d("Viewmodel","List Deleted")
        _showDialog.value = false
    }

    fun clearOrDeleteList(listId: String?, clearbool: Boolean){
        if(clearbool) {
            Log.d("viewmodel", "list cleared")
        } else {
            Log.d("viewmodel", "list deleted")
        }

        _showDialog.value = false

    }
}