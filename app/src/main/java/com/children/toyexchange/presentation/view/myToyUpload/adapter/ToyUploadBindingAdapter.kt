package com.children.toyexchange.presentation.view.myToyUpload.adapter

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.data.models.searchaddress.Document

object ToyUploadBindingAdapter {

    @JvmStatic
    @BindingAdapter("zone_no")
    fun setZoneNo(text: TextView, zoneNo: String?) {
        text.text = zoneNo
    }

    @JvmStatic
    @BindingAdapter("road_address_name")
    fun setRoadAddressName(text: TextView, addressName: String?) {
        text.text = addressName
    }

    @JvmStatic
    @BindingAdapter("address_name")
    fun setAddressName(text: TextView, addressName: String?) {
        text.text = addressName
    }
}