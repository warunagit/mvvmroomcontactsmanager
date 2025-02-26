package com.cycolabs.livedataviewmodeldatabinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cycolabs.livedataviewmodeldatabinding.viewModel.UserViewModel
import com.cycolabs.livedataviewmodeldatabinding.viewModel.UserViewModelFactory
import com.cycolabs.livedataviewmodeldatabinding.databinding.ActivityMainBinding
import com.cycolabs.livedataviewmodeldatabinding.room.User
import com.cycolabs.livedataviewmodeldatabinding.room.UserDatabase
import com.cycolabs.livedataviewmodeldatabinding.room.UserRepository
import com.cycolabs.livedataviewmodeldatabinding.viewUI.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    //create layout
    //add dependencies to gradle
    //create User data class with fields and annotations
    //create User Data Access Object interface DAO for User class
    //create UserDatabase class
    //create UserRepository class
    //create UserViewModel
    //create UserViewModelFactory
    //create myrecyclerviewadapter
    //add view to MainActivity

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        //pass to binding on the layout
        userViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel

        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it,{selectedItem:User->listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this,"Selected name is ${selectedItem.name}",Toast.LENGTH_LONG).show()
        userViewModel.initUpdateAndDelete(selectedItem)
    }
}