package com.foysal.practice.hrmfinal.authorize

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foysal.practice.hrmfinal.database.ExceptionData
import com.foysal.practice.hrmfinal.database.UserDatabase
import kotlinx.coroutines.*

class AuthorizeViewModel (private val userId : String, val database: UserDatabase, application: Application)
    : AndroidViewModel(application){

    private var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _allException = MutableLiveData<ExceptionData>()

    val allException : LiveData<ExceptionData>
        get() = _allException
    val exceptions = database.exceptionDao.get(userId)

}