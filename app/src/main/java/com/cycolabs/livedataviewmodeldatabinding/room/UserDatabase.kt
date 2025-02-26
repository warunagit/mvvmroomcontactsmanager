package com.cycolabs.livedataviewmodeldatabinding.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    //abstract <- not to create instances of the class

    abstract val userDAO:UserDAO

    //Singleton<- only one object for connection
    companion object{
        @Volatile
        //volatile <- make fields immediately visible to other threads
        private var INSTANCE: UserDatabase ?= null
        fun getInstance(context: Context):UserDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_db"
                    ).build()
                }
                return instance
            }
        }
    }
}