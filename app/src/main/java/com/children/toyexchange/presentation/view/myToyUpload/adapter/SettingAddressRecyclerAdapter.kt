package com.children.toyexchange.presentation.view.myToyUpload.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.data.models.searchaddress.Document
import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.children.toyexchange.databinding.SettingAddressRecyclerViewItemBinding
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel
import com.children.toyexchange.presentation.view.myToyUpload.fragment.MainToyUploadFragment
import retrofit2.Response

class SettingAddressRecyclerAdapter(
    private val viewModel: ToyUploadViewModel
) : RecyclerView.Adapter<SettingAddressRecyclerAdapter.SettingAddressRecyclerViewHolder>() {
    private val response: Response<SearchAddress> = viewModel.searchAddressResponse.value!!

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingAddressRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SettingAddressRecyclerViewItemBinding>(
            layoutInflater,
            R.layout.setting_address_recycler_view_item,
            parent,
            false
        )

        return SettingAddressRecyclerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SettingAddressRecyclerViewHolder, position: Int) {

        /*res.body()?.documents?.get(position)?.let {
            holder.bind(
                it
            )
        }*/
        Log.d("로그", "onBindViewHolder : ${response.body()!!.documents[position]}")
        holder.bind(response.body()!!.documents[position])
        holder.binding.item.setOnClickListener {
            Log.d(
                "로그",
                "road_address, address null 확인 : ${response.body()!!.documents[position].road_address}, ${response.body()!!.documents[position].address}"
            )
            if (response.body()!!.documents[position].road_address == null) {
                if (response.body()!!.documents[position].road_address?.region_3depth_name == null)
                    viewModel.setPostAddress("${response.body()!!.documents[position].address?.region_1depth_name} ${response.body()!!.documents[position].address?.region_2depth_name} ${response.body()!!.documents[position].address?.region_3depth_h_name}")
                else viewModel.setPostAddress("${response.body()!!.documents[position].address?.region_1depth_name} ${response.body()!!.documents[position].address?.region_2depth_name} ${response.body()!!.documents[position].address?.region_3depth_name}")
            } else if (response.body()!!.documents[position].address == null)
                if (response.body()!!.documents[position].road_address?.region_3depth_name == null)
                    viewModel.setPostAddress("${response.body()!!.documents[position].road_address?.region_1depth_name} ${response.body()!!.documents[position].road_address?.region_2depth_name} ${response.body()!!.documents[position].road_address?.region_3depth_h_name}")
                else viewModel.setPostAddress("${response.body()!!.documents[position].road_address?.region_1depth_name} ${response.body()!!.documents[position].road_address?.region_2depth_name} ${response.body()!!.documents[position].road_address?.region_3depth_name}")
        }
    }

    override fun getItemCount(): Int {
        return response.body()!!.documents.size
    }

    class SettingAddressRecyclerViewHolder(val binding: SettingAddressRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Document) {
            Log.d("로그", "SettingAddressRecyclerViewHolder bind data : $data")
            binding.data = data
            binding.executePendingBindings()
        }
    }
}

