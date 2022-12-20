package com.foysal.practice.hrmfinal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface UserExceptionDao {

    @Transaction
    @Query("SELECT * FROM user_table ")
    fun getUserExceptions() : LiveData<List<UserExceptionData>>

}