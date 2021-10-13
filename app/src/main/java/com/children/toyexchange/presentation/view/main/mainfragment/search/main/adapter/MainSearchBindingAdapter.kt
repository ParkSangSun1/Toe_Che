package com.children.toyexchange.presentation.view.main.mainfragment.search.main.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object MainSearchBindingAdapter {

    @JvmStatic
    @BindingAdapter("title")
    fun setTitle(text: TextView, title: String) {
         text.text = title
    }

    @JvmStatic
    @BindingAdapter("contents")
    fun setContents(text: TextView, contents: String) {
        text.text = contents
    }

    @JvmStatic
    @BindingAdapter("category")
    fun setCategory(text: TextView, category: String) {
        text.text = category
    }

    @JvmStatic
    @BindingAdapter("location")
    fun setLocation(text: TextView, location: String) {
        text.text = location
    }

    @JvmStatic
    @BindingAdapter("photo")
    fun setPhoto(text: TextView, photo: String) {
        text.text = photo
    }

    @JvmStatic
    @BindingAdapter("date")
    fun setDate(text: TextView, date: String) {
        text.text = date
    }
}