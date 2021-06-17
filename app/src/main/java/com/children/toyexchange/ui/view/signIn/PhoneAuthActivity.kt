package com.children.toyexchange.ui.view.signIn

import android.os.Bundle
import android.view.View
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityPhoneAuthBinding
import com.children.toyexchange.ui.view.BaseActivity


class PhoneAuthActivity : BaseActivity() {
    val binding by binding<ActivityPhoneAuthBinding>(R.layout.activity_sign_in)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this


    }

    fun clickCheckNickName(view: View){
    }
}