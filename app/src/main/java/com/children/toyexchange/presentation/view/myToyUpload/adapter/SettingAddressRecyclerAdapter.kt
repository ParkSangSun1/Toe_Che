package com.children.toyexchange.presentation.view.myToyUpload.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel
import com.children.toyexchange.presentation.view.myToyUpload.fragment.MainToyUploadFragment

class SettingAddressRecyclerAdapter (private val viewModel : ToyUploadViewModel) : RecyclerView.Adapter<SettingAddressRecyclerAdapter.SettingAddressRecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingAddressRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.fragment_setting_address, parent, false)

        return SettingAddressRecyclerViewHolder(listItem)
    }


    override fun onBindViewHolder(holder: SettingAddressRecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return MainToyUploadFragment.photoIndex
    }

    inner class SettingAddressRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}