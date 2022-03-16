package com.children.toyexchange.presentation.view.signIn.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivitySignInBinding
import com.children.toyexchange.presentation.base.BaseActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {


    override fun init() {
        binding.activity = this
    }

    fun loginBtn(view: View) {
        loginSuccess()
    }

    private fun loginSuccess() {
        val intent = Intent(this, PhoneAuthActivity::class.java)
        startActivity(intent)
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
        finish()
    }
}