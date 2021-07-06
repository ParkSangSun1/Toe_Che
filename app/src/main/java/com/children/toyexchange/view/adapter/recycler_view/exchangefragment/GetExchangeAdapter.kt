package com.children.toyexchange.view.adapter.recycler_view.exchangefragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class GetExchangeAdapter : RecyclerView.Adapter<GetExchangeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetExchangeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.exchange_get_fragment_recyclerview_item, parent, false)

        return GetExchangeViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: GetExchangeViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.image)
            .setBackgroundResource(R.drawable.item_radius)
        holder.view.findViewById<ImageView>(R.id.image).clipToOutline = true
    }

    override fun getItemCount(): Int {
        return 15
    }
}

class GetExchangeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}