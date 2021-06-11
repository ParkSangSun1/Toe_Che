package com.children.toyexchange.UI.MyToyUpload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityToyUploadBinding

class ToyUploadActivity : AppCompatActivity() {
    lateinit var binding : ActivityToyUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToyUploadBinding.inflate(layoutInflater)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.uploadBtn.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }
}