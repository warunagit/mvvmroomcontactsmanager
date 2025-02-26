package com.cycolabs.livedataviewmodeldatabinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cycolabs.livedataviewmodeldatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //create layout
    //add dependencies to gradle
    //create User data class with fields and annotations
    //create User Data Access Object interface DAO for User class
    //create UserDatabase class
    //create UserRepository class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }
}