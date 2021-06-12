package com.children.toyexchange.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class JustCameUpAdapter : RecyclerView.Adapter<JustCameUpViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JustCameUpViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.justcameup_recycler_item,parent,false)

        return JustCameUpViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: JustCameUpViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}

class JustCameUpViewHolder(val view: View) : RecyclerView.ViewHolder(view){
}