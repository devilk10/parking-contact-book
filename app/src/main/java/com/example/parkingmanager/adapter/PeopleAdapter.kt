package com.example.parkingmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingmanager.R
import com.example.parkingmanager.database.entity.Person

class PeopleAdapter(private val messages: List<Person>?) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_list_item, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return messages?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.messageTV.text = messages?.get(index)?.name
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messageTV: TextView = itemView.findViewById(R.id.nameHolder) as TextView
    }
}