package com.children.toyexchange.UI.Tutorial.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R

class TutorialViewPagerAdapter(private val bgColors: Int) : RecyclerView.Adapter<PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tutorialviewpageritem, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(bgColors, position)
    }

    override fun getItemCount(): Int = bgColors
}

class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView1: TextView = itemView.findViewById(R.id.tutorial_txt_1)
    private val textView2: TextView = itemView.findViewById(R.id.tutorial_txt_1)

    fun bind(@ColorRes bgColor: Int, position: Int) {
        textView1.text = "RecyclerViewAdapter\nPage $position"
        itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))
    }
}