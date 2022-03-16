package com.children.toyexchange.presentation.view.myToyUpload.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSettingAddressBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel
import com.children.toyexchange.presentation.view.myToyUpload.adapter.ChoicePhotoRecyclerAdapter
import com.children.toyexchange.presentation.view.myToyUpload.adapter.SettingAddressRecyclerAdapter
import com.children.toyexchange.presentation.widget.extension.showHorizontal
import com.children.toyexchange.presentation.widget.extension.showVertical
import com.children.toyexchange.presentation.widget.utils.ApiUrl.KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingAddressFragment : BaseFragment<FragmentSettingAddressBinding>(R.layout.fragment_setting_address) {
    private val toyUploadViewModel by activityViewModels<ToyUploadViewModel>()
    private var checkBack = false


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    private fun observeViewModel() {
        toyUploadViewModel.searchAddressResponse.observe(requireActivity(), Observer {
            Log.d("로그", "searchAddressResponse : ${it?.body()?.documents?.size}")
            if (it?.body()?.documents?.size != 0 && it != null) {
                initRecyclerView()
            }
        })

        toyUploadViewModel.postAddress.observe(requireActivity(), Observer {
            if (it != null){
                if (!checkBack){
                    checkBack = true
                    this.findNavController().popBackStack()
                    toyUploadViewModel.setSearchAddressNull()
                }
            }
        })
    }

    fun searchAddressBtnClick(view: View) {
        toyUploadViewModel.searchAddress(KEY, "similar", 1, 10, binding.query.text.toString())
    }

    fun backBtnClick(view: View){
        this.findNavController().popBackStack()
    }

    private fun initRecyclerView() {
        binding.addressRecyclerView.showVertical(requireContext())
        binding.addressRecyclerView.adapter = SettingAddressRecyclerAdapter(toyUploadViewModel)
    }
}