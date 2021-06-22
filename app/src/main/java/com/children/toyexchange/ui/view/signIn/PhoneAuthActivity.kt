package com.children.toyexchange.ui.view.signIn

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityPhoneAuthBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.utils.MainObject.auth
import com.children.toyexchange.ui.view.BaseActivity
import com.children.toyexchange.ui.view.TestActivity
import com.children.toyexchange.ui.viewmodel.SignUpViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import kotlin.math.log


class PhoneAuthActivity : BaseActivity() {
    val binding by binding<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth)
    var flag = 0


    override fun onStart() {
        super.onStart()
        val transaction = supportFragmentManager.beginTransaction()
        binding.checkPhoneNumber.text = "프로필 설정하기"
        transaction.add(R.id.authFrameLayout, NickNameFragment())
        flag = 1
        transaction.commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        MainObject.viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignUpViewModel::class.java)

        MainObject.viewModel.checkGoNext.observe(this, Observer {
            Log.d("로그", "라이브데이터 출력 $it")
            if (it == true) {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#0080ff"))

            } else {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#D6D4D4"))
            }
        })

//        val checkObserver = Observer<Boolean> {
//            if(it){
//                binding.nextBtn.setBackgroundColor(Color.parseColor("#ffffff"))
//            }else{
//                binding.nextBtn.setBackgroundColor(Color.parseColor("#000000"))
//
//            }
//        }

//        MainObject.viewModel.checkGoNext.observe(this, checkObserver)

    }

    fun clickNextBtn(view: View) {
        if (MainObject.viewModel.checkGoNext.value == true) {
            switchFragment()
            MainObject.viewModel.setSignInGoNextFalse()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        --flag
    }


    private fun switchFragment() {
        if (flag in 0..2) {
            val transaction = supportFragmentManager.beginTransaction()

            when (flag) {
                0 -> {
                    binding.checkPhoneNumber.text = "프로필 설정하기"
                    transaction.add(R.id.authFrameLayout, NickNameFragment())
                    flag = 1
                }
                1 -> {
                    binding.checkPhoneNumber.text = "인증번호 받기"
                    transaction.replace(R.id.authFrameLayout, PhoneNumberFragment())
                    flag = 2
                }
                2 -> {
                    binding.checkPhoneNumber.text = "인증하기"
                    transaction.replace(R.id.authFrameLayout, PhoneNumberCodeFragment())
                    flag = 3


                }
//            3 -> {
//                transaction.replace(R.id.frameLayout, FragmentA())
//                flag = 1
//            }
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }


}