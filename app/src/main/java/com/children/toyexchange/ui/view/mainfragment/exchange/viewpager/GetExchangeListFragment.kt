package com.children.toyexchange.ui.view.mainfragment.exchange.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentGetExchangeListBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.adapter.recycler_view.exchangefragment.GetExchangeAdapter
import com.children.toyexchange.ui.view.adapter.recycler_view.exchangefragment.SetExchangeAdapter
import com.children.toyexchange.ui.view.adapter.recycler_view.searchfragment.RecentPostsAdapter


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


        MainObject.recyclerViewManager(binding.getRecyclerView,requireContext())
        binding.getRecyclerView.adapter = GetExchangeAdapter()

        return binding.root
    }


}