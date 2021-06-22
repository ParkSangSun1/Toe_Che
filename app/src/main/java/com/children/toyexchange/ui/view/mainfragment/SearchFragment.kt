package com.children.toyexchange.ui.view.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSearchBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.adapter.CategoryAdapter
import com.children.toyexchange.ui.view.adapter.RecentPostsAdapter


class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        recyclerViewManager(binding.recentPostsRecyclerView)
        binding.recentPostsRecyclerView.adapter = RecentPostsAdapter()

        MainObject.shadowDelete(null, binding.recentPostsRecyclerView)

        return binding.root
    }

    //리사이클러뷰
    private fun recyclerViewManager(v: RecyclerView) {
        v.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}