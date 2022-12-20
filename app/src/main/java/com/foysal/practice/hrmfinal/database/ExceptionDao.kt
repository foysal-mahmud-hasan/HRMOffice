package com.foysal.practice.hrmfinal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ExceptionDao {

    @Insert
    fun insert(exceptionData: ExceptionData)

    @Update
    fun update(exceptionData: ExceptionData)

    @Query("SELECT * FROM exception_table WHERE userExceptionId = :key")
    fun get(key : String) : LiveData<List<ExceptionData>>

}