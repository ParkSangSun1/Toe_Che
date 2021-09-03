package com.children.toyexchange.presentation.widget.extension

import android.content.Context
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//RecyclerView Vertical
fun RecyclerView.showVertical(context: Context){
    this.layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

//RecyclerView Horizontal
fun RecyclerView.showHorizontal(context: Context){
    this.layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

//RecyclerView 음영삭제
fun RecyclerView.shadowDelete(){
    this.overScrollMode = View.OVER_SCROLL_NEVER
}

//NestedScrollView 음영삭제
fun NestedScrollView.shadowDelete(){
    this.overScrollMode = View.OVER_SCROLL_NEVER
}