package com.children.toyexchange.view.mainfragment.exchange.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSetExchangeListBinding
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.view.adapter.recycler_view.exchangefragment.SetExchangeAdapter

//내가 신청한 신청 목록
class SetExchangeListFragment : Fragment() {

    lateinit var binding: FragmentSetExchangeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_set_exchange_list, container, false)


        MainObject.recyclerViewManager(binding.setRecyclerView,requireContext())
        binding.setRecyclerView.adapter = SetExchangeAdapter()

        return binding.root
    }


}