package com.foysal.practice.hrmfinal.entry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foysal.practice.hrmfinal.database.UserDatabase
import com.foysal.practice.hrmfinal.home.HomeViewModel

class EntryViewModelFactory ( private val userId : String,
                              private val role : String,
                              private val database : UserDatabase,
                              private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EntryViewModel::class.java)){
            return EntryViewModel(userId,role, database, application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }
}