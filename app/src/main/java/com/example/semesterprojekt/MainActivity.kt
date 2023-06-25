package com.example.semesterprojekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.semesterprojekt.navigation.Navigation
import com.example.semesterprojekt.ui.theme.SemesterprojektTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemesterprojektTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
    }
}