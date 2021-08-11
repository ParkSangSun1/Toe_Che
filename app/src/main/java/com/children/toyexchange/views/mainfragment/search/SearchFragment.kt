package com.children.toyexchange.views.mainfragment.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSearchBinding
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.views.adapter.recycler_view.searchfragment.RecentPostsAdapter
import com.children.toyexchange.views.mytoyupload.ToyUploadActivity


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
        binding.activity = this
        MainObject.recyclerViewVerticalManager(binding.recentPostsRecyclerView, requireContext())
        binding.recentPostsRecyclerView.adapter = RecentPostsAdapter()
        binding.addBtn.setOnClickListener {
            clickAddBtn()
        }
        MainObject.shadowDelete(null, binding.recentPostsRecyclerView)


        return binding.root
    }

    private fun clickAddBtn() {
        val intent = Intent(requireContext(), ToyUploadActivity::class.java)
        startActivity(intent)
    }

    fun searchBtnClick(view: View){
        val intent = Intent(requireContext(), SearchRecordActivity::class.java)
        startActivity(intent)
    }
}