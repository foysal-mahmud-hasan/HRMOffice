package com.foysal.practice.hrmfinal.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foysal.practice.hrmfinal.database.UserDao
import com.foysal.practice.hrmfinal.database.UserData
import com.foysal.practice.hrmfinal.database.UserDatabase
import kotlinx.coroutines.*

class LoginViewModel (
    val database: UserDatabase,
    application: Application) : AndroidViewModel(application), Observable {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope( Dispatchers.Main + viewModelJob)

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private var _navigateToHome = MutableLiveData<UserData?>()

    val navigateToHome : LiveData<UserData?>
        get() = _navigateToHome



    fun loginButton() {
        if (inputUsername.value == null || inputPassword.value == null) {
            Log.i("USER", "${inputUsername.value}")
        } else {

            uiScope.launch {
                val usersNames = database.userDao.login(inputUsername.value!!, inputPassword.value!!)
                if (usersNames != null) {
                    _navigateToHome.value = usersNames
                } else {
                    Log.i("USER", "BALPAKNAINLI")
                }
            }
        }
    }

    fun doneNavigatingToHome(){

        _navigateToHome.value = null

    }

    override fun onCleared(){

        super.onCleared()
        viewModelJob.cancel()

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}