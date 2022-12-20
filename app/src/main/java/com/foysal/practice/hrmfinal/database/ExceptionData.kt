package com.foysal.practice.hrmfinal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exception_table")
data class ExceptionData (

    @ColumnInfo(name = "userName")
    var userName : String,

    @ColumnInfo(name = "userExceptionId")
    var userExceptionId : String,

    @ColumnInfo(name = "userDate")
    var userDate : String,

    @ColumnInfo(name = "userExceptionType")
    var userExceptionType : String,

    @ColumnInfo(name = "userRemark")
    var userRemark : String,

    @ColumnInfo(name = "userStatus")
    var userStatus : String


){
    @PrimaryKey(autoGenerate = true)
    var exceptionId : Int = 0
}