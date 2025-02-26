package com.cycolabs.livedataviewmodeldatabinding.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO{

    @Insert
    suspend fun insertUser(user: User): Long
    //suspend <- execute a long running operation and wait without closing
    //suspend functions can close and resume

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    //execute sql query
    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user")
    fun getAllUsersInDB(): LiveData<List<User>>
    //live data object which holding and presents list of users
}
