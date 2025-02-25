package com.cycolabs.livedataviewmodeldatabinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cycolabs.livedataviewmodeldatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //binding.button.setOnClickListener(){}

        //no need to use button listner, due use of live data
        //instead, bind the button with view model inside the layout
        //android:onClick="@{()->viewModel.updateCounter()}

        //setting the observer
        /*binding.myViewModel = viewModel
        viewModel.counter.observe(this, Observer {
            binding.textView.text = it.toString()
        })*/

        binding.lifecycleOwner = this
        //setting viewmodel to view of the layout
        binding.myViewModel = viewModel

        //DataBinding keeps data with LiveData when view changes
        //

        //use those architectures to clean codings
    }
}