package com.example.semesterprojekt.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase




fun createAccount(email: String,password: String){
    val auth = Firebase.auth
    auth.createUserWithEmailAndPassword(email,password)
}
