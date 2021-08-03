package com.children.toyexchange.views.signIn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivitySignInBinding
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.views.base.BaseActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : BaseActivity() {
    val binding by binding<ActivitySignInBinding>(R.layout.activity_sign_in)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this




    }

    fun loginBtn(view: View) {

        loginSuccess()

    }





    private fun loginSuccess() {
        val intent = Intent(this, PhoneAuthActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
        finish()
    }
}