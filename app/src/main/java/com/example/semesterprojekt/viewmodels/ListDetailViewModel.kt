package com.example.semesterprojekt.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListDetailViewModel {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    fun onClearClicked(){
        _showDialog.value = true
    }

    fun onDialogConfirm(){
        _showDialog.value = false
    }

    fun onDialogDismiss(){
        _showDialog.value = false
    }
}