package com.children.toyexchange.presentation.view.main.mainfragment.exchange.setexchange

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class SetExchangeAdapter : RecyclerView.Adapter<SetExchangeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetExchangeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.exchange_set_fragment_recyclerview_item, parent, false)

        return SetExchangeViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: SetExchangeViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.image)
            .setBackgroundResource(R.drawable.item_radius)
        holder.view.findViewById<ImageView>(R.id.image).clipToOutline = true
    }

    override fun getItemCount(): Int {
        return 15
    }
}

class SetExchangeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}