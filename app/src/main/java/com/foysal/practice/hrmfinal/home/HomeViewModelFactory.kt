package com.foysal.practice.hrmfinal.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foysal.practice.hrmfinal.database.UserDatabase

class HomeViewModelFactory(
    private val userId: String , private val role : String,
    private val database: UserDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){

            return HomeViewModel(userId, role, database, application) as T

        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }

}