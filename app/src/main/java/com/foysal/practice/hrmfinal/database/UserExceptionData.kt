package com.foysal.practice.hrmfinal.database

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class UserExceptionData (

    @Embedded val user : UserData,
    @Relation(

        parentColumn = "userId",
        entityColumn = "userExceptionId"

    )
    val userExceptionList : ExceptionData

)