package com.children.toyexchange.views.myToyUpload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityToyUploadBinding

class ToyUploadActivity : AppCompatActivity() {
    lateinit var binding : ActivityToyUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_toy_upload)
        binding.activity = this

    }

    fun backBtnClick(view: View){
        finish()

    }

    fun uploadBtnClick(view: View){
        finish()

    }
}