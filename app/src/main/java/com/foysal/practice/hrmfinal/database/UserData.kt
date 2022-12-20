package com.foysal.practice.hrmfinal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserData (

    @PrimaryKey(autoGenerate = false)
    var userId : String ,

    @ColumnInfo(name = "userPassword")
    var userPassword : String,

    @ColumnInfo(name = "userRole")
    var userRole : String

)