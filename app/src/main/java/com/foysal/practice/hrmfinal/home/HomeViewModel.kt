package com.foysal.practice.hrmfinal.home

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foysal.practice.hrmfinal.database.UserDatabase
import kotlinx.coroutines.*

class HomeViewModel(private val userId: String, role : String,
                    val database: UserDatabase, application: Application) : AndroidViewModel(application) {


//    init {
//        getUserRole()
//    }
    private var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    // Navigating

//    private val _navigateToExceptionEntry = MutableLiveData<Boolean>()
//    val navigateToExceptionEntry : LiveData<Boolean>
//        get() = _navigateToExceptionEntry
//
//    private val _navigateToAuthorize = MutableLiveData<Boolean>()
//    val navigateToAuthorize : LiveData<Boolean>
//        get() = _navigateToAuthorize

    private val _userIdAuth = MutableLiveData<String?>()
    val userIdAuth : LiveData<String?>
        get() = _userIdAuth
    private val _userIdEntry = MutableLiveData<String?>()
    val userIdEntry : LiveData<String?>
        get() = _userIdEntry


    fun navigateToAuth(){
        _userIdAuth.value = userId
    }

    fun navigateToEntry(){
        _userIdEntry.value = userId
    }
    // done navigating

    fun doneNavigateToAuth(){
        _userIdAuth.value = null
    }

    fun doneNavigateToEntry(){
        _userIdEntry.value = null
    }


    // LATER

    private val _userRole = MutableLiveData<String>()
    val userRole : LiveData<String>
        get() = _userRole

    private val admin = MutableLiveData<Boolean>()
    private val employee = MutableLiveData<Boolean>()

    private fun getUserRole(){
        uiScope.launch {
            _userRole.value = database.userDao.role(userId)
            if (_userRole.value == "admin"){
                admin.value = true
                employee.value = true
            } else{
                admin.value = false
                employee.value = true
            }
        }
    }
    fun exceptionEntry(){
    }
//    @BindingAdapter("android:visibility")
//    fun exceptionEntryVisible(view: View){
//        view.visibility = if (userRole.value=="admin") View.VISIBLE else View.INVISIBLE
//    }
//    @BindingAdapter("android:visibility")
//    fun authorizeVisible(view : View) {
//    }
    fun authorize(){
    }
    fun findRole(id : String){
        uiScope.launch {
            withContext(Dispatchers.IO){
                _userRole.value = database.userDao.role(id)
            }
        }
}
    override fun onCleared(){

        super.onCleared()
        viewModelJob.cancel()

    }
}