package com.children.toyexchange.presentation.view.main.exchange

import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentExchangeBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class ExchangeFragment : BaseFragment<FragmentExchangeBinding>(R.layout.fragment_exchange) {


    override fun init() {
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
    }
}