package com.children.toyexchange.ui.view.myToyUpload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityToyUploadBinding

class ToyUploadActivity : AppCompatActivity() {
    lateinit var binding : ActivityToyUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_toy_upload)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.uploadBtn.setOnClickListener {
            finish()
        }
    }
}