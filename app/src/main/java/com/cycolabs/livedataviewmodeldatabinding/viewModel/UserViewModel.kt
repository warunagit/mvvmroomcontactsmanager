package com.cycolabs.livedataviewmodeldatabinding.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycolabs.livedataviewmodeldatabinding.room.User
import com.cycolabs.livedataviewmodeldatabinding.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel(),Observable{
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    //To identify the field has changed
    val inputName = MutableLiveData<String?>()

    @Bindable
    //To identify the field has changed
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdaateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init{
        saveOrUpdaateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
    }

    fun saveOrUpdate(){

        if(isUpdateOrDelete){
            //Make update:
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!
            update(userToUpdateOrDelete)
        }else{
            //Insert functionality
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(User(0,name,email))
            inputName.value = null
            inputEmail.value = null
        }


    }

    fun insert(user:User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun update(user:User) = viewModelScope.launch {
        repository.update(user)

        //Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdaateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)

        //Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdaateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun initUpdateAndDelete(user: User) {
        //Resetting the buttons and fields
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = user
        saveOrUpdaateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }
}