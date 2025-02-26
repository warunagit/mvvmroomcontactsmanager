package com.cycolabs.livedataviewmodeldatabinding.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cycolabs.livedataviewmodeldatabinding.R
import com.cycolabs.livedataviewmodeldatabinding.databinding.CardItemBinding
import com.cycolabs.livedataviewmodeldatabinding.room.User

class MyRecyclerViewAdapter
    (private val usersList:List<User>,
            private val clickListener: (User)->Unit
            ): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layouInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layouInflater, R.layout.card_item,parent,false)
        return  MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListener)
    }
}

class MyViewHolder(val binding: CardItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(user:User,clickListener: (User) -> Unit){
        binding.tvName.text=user.name
        binding.tvEmail.text=user.email

        binding.listItemLayout.setOnClickListener{
            clickListener(user)
        }
    }
}