package com.children.toyexchange.view.adapter.recycler_view.chattingfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class ChattingRecyclerAdapter : RecyclerView.Adapter<ChattingRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.chat_recycler_view_item, parent, false)

        return ChattingRecyclerViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ChattingRecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}

class ChattingRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}