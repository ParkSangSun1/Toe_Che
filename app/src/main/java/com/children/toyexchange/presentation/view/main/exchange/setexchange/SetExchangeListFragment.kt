package com.children.toyexchange.presentation.view.main.exchange.setexchange

import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSetExchangeListBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.widget.extension.showVertical

//내가 신청한 신청 목록
class SetExchangeListFragment : BaseFragment<FragmentSetExchangeListBinding>(R.layout.fragment_set_exchange_list) {


    override fun init() {
        binding.setRecyclerView.showVertical(requireContext())
        binding.setRecyclerView.adapter = SetExchangeAdapter()
    }
}