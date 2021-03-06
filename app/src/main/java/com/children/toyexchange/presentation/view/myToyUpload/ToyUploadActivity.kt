package com.children.toyexchange.presentation.view.myToyUpload

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityToyUploadBinding
import com.children.toyexchange.presentation.base.BaseActivity
import com.children.toyexchange.presentation.widget.extension.showShotSnackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ToyUploadActivity : BaseActivity<ActivityToyUploadBinding>(R.layout.activity_toy_upload) {
    private val toyUploadViewModel by viewModels<ToyUploadViewModel>()
    private var waitTime = 0L


    override fun init() {
        initViewModel()
        observeViewModel()
    }
    
    private fun initViewModel(){
        toyUploadViewModel.setSuccessPostUpload(false)
    }

    private fun observeViewModel(){
        toyUploadViewModel.successPostUpload.observe(this, Observer {
            if (it) finish()
        })

        toyUploadViewModel.backBtnEvent.observe(this){
            finish()
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - waitTime >= 1500) {
            waitTime = System.currentTimeMillis()
            binding.layout.showShotSnackbar("버튼을 한번 더 누르면 장난감 올리기가 종료됩니다")
        } else finish()
    }
}