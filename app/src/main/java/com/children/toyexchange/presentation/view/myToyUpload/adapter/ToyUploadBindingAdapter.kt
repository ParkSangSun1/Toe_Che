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
        Log.d("로그","널인듯? $zoneNo")
        if (zoneNo == null || zoneNo =="") text.text = "해당하는 값이 없습니다"
        else text.text = zoneNo
    }

    @JvmStatic
    @BindingAdapter("road_address_name")
    fun setRoadAddressName(text: TextView, addressName: String?) {
        if (addressName == null) text.text = "해당하는 값이 없습니다"
        else text.text = addressName
    }

    @JvmStatic
    @BindingAdapter("address_name")
    fun setAddressName(text: TextView, addressName: String?) {
        if (addressName == null) text.text = "해당하는 값이 없습니다"
        else text.text = addressName
    }
}