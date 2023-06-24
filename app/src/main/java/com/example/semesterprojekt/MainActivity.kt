package com.example.semesterprojekt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.semesterprojekt.navigation.Navigation
import com.example.semesterprojekt.ui.theme.SemesterprojektTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
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
        val currentUser = auth.currentUser
        Log.d(null,currentUser.toString())
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SemesterprojektTheme {
        Greeting("Android")
    }
}

/*TODO: ViewModel für ganze App -> UserState speichern
        Spiele aus der Datenbank lesen und anzeigen
        Exceptionhandling Registration und Login überarbeiten
        Navigation fixen
        Seite so umbauen das man eingeloggt sein muss
        Logout
        Dummy Rating einbauen
        Dummy Review einbauen
*/