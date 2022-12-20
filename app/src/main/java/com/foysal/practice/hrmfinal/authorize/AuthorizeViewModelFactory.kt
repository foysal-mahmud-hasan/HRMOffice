package com.foysal.practice.hrmfinal.authorize

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foysal.practice.hrmfinal.database.UserDatabase

class AuthorizeViewModelFactory(private val userId : String,
                                private val database: UserDatabase,
                                private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorizeViewModel::class.java)){
            return AuthorizeViewModel(userId, database, application) as T
        }
        throw java.lang.IllegalArgumentException("unknown view model")
    }
}