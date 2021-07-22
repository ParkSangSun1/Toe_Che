package com.children.toyexchange.view.adapter.recycler_view.searchfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.search_history_recyclerview_item, parent, false)

        return SearchHistoryViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.image)
            .setBackgroundResource(R.drawable.item_radius)
        holder.view.findViewById<ImageView>(R.id.image).clipToOutline = true
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class SearchHistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}