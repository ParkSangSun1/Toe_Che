package com.children.toyexchange.presentation.view.main.exchange.getexchange

import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentGetExchangeListBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.widget.extension.showVertical


//내가 받은 신청 목록
class GetExchangeListFragment : BaseFragment<FragmentGetExchangeListBinding>(R.layout.fragment_get_exchange_list) {


    override fun init() {
        binding.getRecyclerView.showVertical(requireContext())
        binding.getRecyclerView.adapter = GetExchangeAdapter()
    }
}