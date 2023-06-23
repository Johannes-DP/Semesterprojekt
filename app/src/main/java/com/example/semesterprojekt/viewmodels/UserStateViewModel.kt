package com.example.semesterprojekt.viewmodels

import androidx.lifecycle.ViewModel
import com.example.semesterprojekt.repository.AuthRepository

class UserStateViewModel(private val repository: AuthRepository): ViewModel(){
     lateinit var user: String

     fun logout(){
          repository.logout()
          user = ""
     }
}