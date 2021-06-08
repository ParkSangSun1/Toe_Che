package com.children.toyexchange.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class CustomizedRecommendAdapter:RecyclerView.Adapter<CustomizedRecommendViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomizedRecommendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.customized_recommend_recycler_item,parent,false)

        return CustomizedRecommendViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: CustomizedRecommendViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}

class CustomizedRecommendViewHolder(val view: View) : RecyclerView.ViewHolder(view){
}