package com.children.toyexchange.views

import android.os.Bundle
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityTestBinding
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.views.base.BaseActivity

class TestActivity : BaseActivity() {
    val binding by binding<ActivityTestBinding>(R.layout.activity_test)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.testtxt.text = MainObject.signInViewModel.getUserNickname()
    }
}