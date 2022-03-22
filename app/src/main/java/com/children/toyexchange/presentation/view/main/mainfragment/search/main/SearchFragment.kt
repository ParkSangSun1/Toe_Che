package com.children.toyexchange.presentation.view.main.mainfragment.search.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSearchBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.view.main.MainViewModel
import com.children.toyexchange.presentation.view.main.mainfragment.search.main.adapter.RecentPostsAdapter
import com.children.toyexchange.presentation.view.main.mainfragment.search.searchbar.SearchRecordActivity
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadActivity
import com.children.toyexchange.presentation.widget.extension.shadowDelete
import com.children.toyexchange.presentation.widget.extension.showVertical
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val uid = FirebaseAuth.getInstance()


    override fun init() {
        binding.fragment = this

        mainViewModel.getStorePost()
            .addOnSuccessListener {
                Log.d("로그","Post 정상 가져오기")
                mainViewModel.setGetPostResponse(it)
                //Log.d("로그","getStorePost : ${it.documents}")
            }
            .addOnFailureListener {
                Log.d("로그","Post 정상 가져오기 X $it")
            }

        observeViewModel()

        binding.addBtn.setOnClickListener {
            clickAddBtn()
        }
    }

    private fun observeViewModel(){
        mainViewModel.getPostResponse.observe(requireActivity(), Observer {
            Log.d("로그","시작됨1")

           // mainViewModel.getPostDataProcessing()
            binding.recentPostsRecyclerView.showVertical(requireContext())
            binding.recentPostsRecyclerView.adapter = RecentPostsAdapter(mainViewModel.getPostResponse,mainViewModel,uid,requireContext())
            binding.recentPostsRecyclerView.shadowDelete()
        })

    /*    mainViewModel.getImage.observe(requireActivity(), Observer {
            Log.d("로그","시작됨2")
            Log.d("로그","mainViewModel.post : $it")
            if(it != null) mainViewModel.getPostImage(uid.uid)
        })*/
    }

    private fun clickAddBtn() {
        val intent = Intent(requireContext(), ToyUploadActivity::class.java)
        startActivity(intent)
    }

    fun searchBtnClick(view: View) {
        val intent = Intent(requireContext(), SearchRecordActivity::class.java)
        startActivity(intent)
    }
}