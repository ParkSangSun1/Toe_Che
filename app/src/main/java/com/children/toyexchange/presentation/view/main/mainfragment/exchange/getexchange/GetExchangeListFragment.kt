package com.children.toyexchange.presentation.view.main.mainfragment.exchange.getexchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentGetExchangeListBinding
import com.children.toyexchange.presentation.widget.extension.showVertical


//내가 받은 신청 목록
class GetExchangeListFragment : Fragment() {

    lateinit var binding : FragmentGetExchangeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_exchange_list, container, false)


        binding.getRecyclerView.showVertical(requireContext())
        binding.getRecyclerView.adapter = GetExchangeAdapter()

        return binding.root
    }


}