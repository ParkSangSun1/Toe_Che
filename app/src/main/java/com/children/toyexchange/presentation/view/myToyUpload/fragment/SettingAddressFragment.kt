package com.children.toyexchange.presentation.view.myToyUpload.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSettingAddressBinding
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel
import com.children.toyexchange.presentation.view.myToyUpload.adapter.ChoicePhotoRecyclerAdapter
import com.children.toyexchange.presentation.view.myToyUpload.adapter.SettingAddressRecyclerAdapter
import com.children.toyexchange.presentation.widget.extension.showHorizontal
import com.children.toyexchange.presentation.widget.extension.showVertical

class SettingAddressFragment : Fragment() {
    private lateinit var binding : FragmentSettingAddressBinding
    private val toyUploadViewModel by activityViewModels<ToyUploadViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting_address,container,false)
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView(){
        binding.addressRecyclerView.showVertical(requireContext())
        binding.addressRecyclerView.adapter = SettingAddressRecyclerAdapter(toyUploadViewModel)
    }
}