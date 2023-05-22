package com.example.semesterprojekt.widgets

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable



@Composable
fun AlertDialogs(
    listId: String?,
    show: Boolean,
    clear: Boolean,
    onConfirm: (String?, Boolean) -> Unit,
    onDismiss: () -> Unit){

    if (show){
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                //openDialog.value = false
            },
            title = {
                if (clear){
                    Text("Are you sure you want to clear your list?")
                } else {
                    Text("Are you sure you want to delete your list?")
                }
            },
            text = {
                if (clear){
                    Text("This action will clear the whole List. If you want to continue with this action, please press the confirm Button ")
                } else {
                    Text("This action will delete your list. If you want to continue with this action, please press the confirm button")
                }

            },
            confirmButton = {
                Button(

                    onClick = { onConfirm(listId, clear)
                        //openDialog.value = false
                    }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(

                    onClick = {onDismiss()
                        //openDialog.value = false
                    }) {
                    Text("Dismiss")
                }
            }
        )
    }

}
