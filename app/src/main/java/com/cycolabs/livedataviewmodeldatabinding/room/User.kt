package com.cycolabs.livedataviewmodeldatabinding.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//annotate as an room database entity
@Entity(tableName = "user")
//define entity properties
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "user_name")
    var name:String,

    @ColumnInfo(name = "user_email")
    var email: String
)
//to use custom column names, those should annotate as ->
// @ColumnInfo(name="user_id")

