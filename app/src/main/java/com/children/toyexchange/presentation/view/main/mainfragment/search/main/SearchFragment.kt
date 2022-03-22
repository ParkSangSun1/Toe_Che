package com.children.toyexchange.presentation.view.main.mainfragment.search.main

import android.content.Intent
import android.view.View
import androidx.fragment.app.activityViewModels
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentSearchBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.view.main.MainViewModel
import com.children.toyexchange.presentation.view.main.mainfragment.search.main.adapter.PostsAdapter
import com.children.toyexchange.presentation.view.main.mainfragment.search.searchbar.SearchRecordActivity
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadActivity
import com.children.toyexchange.presentation.widget.extension.shadowDelete
import com.children.toyexchange.presentation.widget.extension.showShotSnackbar
import com.children.toyexchange.presentation.widget.extension.showVertical
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val uid = FirebaseAuth.getInstance()


    override fun init() {
        binding.fragment = this
        observeViewModel()
        mainViewModel.getStorePost()
    }

    private fun observeViewModel(){
        mainViewModel.nextActionEvent.observe(this){
            when(it){
                1 ->{
                    binding.recentPostsRecyclerView.showVertical(requireContext())
                    binding.recentPostsRecyclerView.adapter = PostsAdapter(mainViewModel, requireContext())
                    binding.recentPostsRecyclerView.shadowDelete()
                }
            }
        }

        mainViewModel.messageEvent.observe(this){
            when(it){
                1 -> this.showShotSnackbar("게시물을 가져오는데 오류가 발생했습니다")
                else -> this.showShotSnackbar("알 수 없는 오류가 발생했습니다")
            }
        }
    }

    fun clickAddBtn(view: View) {
        val intent = Intent(requireContext(), ToyUploadActivity::class.java)
        startActivity(intent)
    }

    fun searchBtnClick(view: View) {
        val intent = Intent(requireContext(), SearchRecordActivity::class.java)
        startActivity(intent)
    }
}