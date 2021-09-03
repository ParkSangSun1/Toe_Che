package com.children.toyexchange.presentation.view.main.mainfragment.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSearchBinding
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadActivity
import com.children.toyexchange.presentation.widget.extension.shadowDelete
import com.children.toyexchange.presentation.widget.extension.showVertical


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
        binding.recentPostsRecyclerView.showVertical(requireContext())
        binding.recentPostsRecyclerView.adapter = RecentPostsAdapter()
        binding.addBtn.setOnClickListener {
            clickAddBtn()
        }
        binding.recentPostsRecyclerView.shadowDelete()

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