package com.cycolabs.livedataviewmodeldatabinding.ViewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cycolabs.livedataviewmodeldatabinding.room.User
import com.cycolabs.livedataviewmodeldatabinding.room.UserRepository

class UserViewModel(private val repository: UserRepository): ViewModel(),Observable{
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    //To identify the field has changed
    val inputName = MutableLiveData<String>()

    @Bindable
    //To identify the field has changed
    val inputEmail = MutableLiveData<String>()

}