package com.foysal.practice.hrmfinal.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foysal.practice.hrmfinal.database.UserDao
import com.foysal.practice.hrmfinal.database.UserDatabase
import kotlinx.coroutines.CoroutineScope

class LoginViewModelFactory(
    private val dataSource: UserDatabase,
    private val application: Application) : ViewModelProvider.Factory{
    @Suppress("Unchecked Cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(dataSource, application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }
}