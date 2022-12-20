package com.foysal.practice.hrmfinal.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert
    fun insert(userData : UserData)

    @Update
    fun update(userData: UserData)

    @Query("SELECT * FROM user_table")
    fun getAllUser() : LiveData<List<UserData>>

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE userId = :userId and userPassword = :userPassword)")
    fun validate(userId : String, userPassword : String) : Boolean

    @Query("SELECT * FROM user_table")
    fun getAllUserList() : List<UserData>

    @Query("SELECT * from user_table where userId=(:userId) and userPassword=(:userPassword)")
    suspend fun login(userId : String, userPassword : String) : UserData?

    @Query("SELECT userRole FROM USER_TABLE WHERE userId=(:userId)")
    suspend fun role(userId: String) : String?

}