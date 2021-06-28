package com.children.toyexchange.ui.view.mainfragment.exchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentExchangeBinding
import com.children.toyexchange.ui.view.adapter.viewpager.ExchangeListAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ExchangeFragment : Fragment() {

    lateinit var binding : FragmentExchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val intent = Intent(requireContext(), ToyUploadActivity::class.java)
//
//        startActivity(intent)
//        requireActivity().overridePendingTransition(0, 0)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_exchange,container,false)

        val adapter = ExchangeListAdapter(childFragmentManager, lifecycle)
        binding.exchangeViewPager.adapter = adapter

        TabLayoutMediator(binding.exchangeTabLayout, binding.exchangeViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "신청받은 목록"
                }
                1 -> {
                    tab.text = "신청한 목록"
                }
            }
        }.attach()

        return binding.root
    }

}