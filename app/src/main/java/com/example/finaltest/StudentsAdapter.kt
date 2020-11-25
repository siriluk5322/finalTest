package com.example.finaltest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.std_layout.view.*

class StudentsAdapter (val item : List<Student>, val context : Context): RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val name = view.username
        val internet = view.internet
        val month = view.month
        val price = view.price
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.std_layout,parent, false)
        val myHolder = ViewHolder(view_item)

        return myHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "Name : "+item[position].username
        holder.internet.text="Internet Packet: "+item[position].internet
        holder.month.text="Month : "+item[position].month.toString()
        holder.price.text="Price : "+item[position].price.toString()
    }

    override fun getItemCount(): Int {
        return item.size
    }

}