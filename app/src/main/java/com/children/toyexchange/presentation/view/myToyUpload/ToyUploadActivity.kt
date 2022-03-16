package com.children.toyexchange.presentation.view.myToyUpload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.children.toyexchange.R
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.databinding.ActivityToyUploadBinding
import com.children.toyexchange.presentation.base.BaseActivity
import com.children.toyexchange.presentation.widget.extension.showHorizontal
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ToyUploadActivity : BaseActivity<ActivityToyUploadBinding>(R.layout.activity_toy_upload) {
    private val toyUploadViewModel by viewModels<ToyUploadViewModel>()


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
    }
}