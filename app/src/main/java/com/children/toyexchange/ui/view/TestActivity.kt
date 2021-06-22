package com.children.toyexchange.ui.view

import android.os.Bundle
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityTestBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.base.BaseActivity

class TestActivity : BaseActivity() {
    val binding by binding<ActivityTestBinding>(R.layout.activity_test)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.testtxt.text = MainObject.viewModel.getUserNickname()
    }
}