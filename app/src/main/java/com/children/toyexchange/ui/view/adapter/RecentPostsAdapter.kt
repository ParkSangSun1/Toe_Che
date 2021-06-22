package com.children.toyexchange.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class RecentPostsAdapter : RecyclerView.Adapter<RecentPostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.search_fragment_recyclerview_item, parent, false)

        return RecentPostsViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: RecentPostsViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.image)
            .setBackgroundResource(R.drawable.item_radius)
        holder.view.findViewById<ImageView>(R.id.image).clipToOutline = true
    }

    override fun getItemCount(): Int {
        return 15
    }
}

class RecentPostsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}