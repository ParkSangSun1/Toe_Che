package com.children.toyexchange.ui.view.signIn

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityPhoneAuthBinding
import com.children.toyexchange.ui.MainActivity
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.MainActivity2
import com.children.toyexchange.ui.view.base.BaseActivity
import com.children.toyexchange.ui.view.base.SignInBaseActivity
import com.children.toyexchange.ui.viewmodel.SignUpViewModel


class PhoneAuthActivity : SignInBaseActivity() {
    val binding by binding<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth)
    var flag = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        MainObject.viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignUpViewModel::class.java)

        val transaction = supportFragmentManager.beginTransaction()
        binding.checkPhoneNumber.text = "다음으로"
        transaction.add(R.id.authFrameLayout, NickNameFragment())
        flag = 1
        transaction.commit()


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
            if (flag == 2){
                val intent  = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
                finish()
            }else{
                switchFragment()
                MainObject.viewModel.setSignInGoNextFalse()
            }

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
                    binding.checkPhoneNumber.text = "다음으로"
                    transaction.add(R.id.authFrameLayout, NickNameFragment())
                    flag = 1
                }
                1 -> {
                    binding.checkPhoneNumber.text = "시작하기"
                    transaction.replace(R.id.authFrameLayout, PhoneNumberFragment())
                    flag = 2
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