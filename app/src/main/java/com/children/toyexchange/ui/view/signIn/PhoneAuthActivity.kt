package com.children.toyexchange.ui.view.signIn

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityPhoneAuthBinding
import com.children.toyexchange.ui.model.user_signin_model.UserSignIn
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.MainActivity
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


        //버튼 라이브데이터
        MainObject.viewModel.checkGoNext.observe(this, Observer {
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
        Log.d("로그","UID : ${MainObject.auth?.uid}")
        if (MainObject.viewModel.checkGoNext.value == true) {
            if (flag == 2) {
                val userSignIn = UserSignIn(
                    MainObject.viewModel.getUserPhoto(),
                    MainObject.viewModel.getUserNickname(),
                    MainObject.viewModel.getUserPhoneNumber()
                )
                Log.d(
                    "로그", MainObject.viewModel.getUserPhoneNumber().toString()
                )

                //유저 정보 RealTimeDataBase 저장
                MainObject.database.reference.child("userAccountInfo")
                    .child(MainObject.auth?.uid.toString()).setValue(userSignIn)
                    .addOnSuccessListener {

                        //유저 닉네임 RealTimeDataBase 저장
                        MainObject.viewModel.getUserNickname()?.let {
                            MainObject.database.reference.child("userNickName").child(
                                it
                            ).setValue(MainObject.auth?.uid.toString())
                                .addOnSuccessListener {
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out)
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }


            } else {
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
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.authFrameLayout, NickNameFragment())
                    flag = 1
                }
                1 -> {
                    binding.checkPhoneNumber.text = "시작하기"
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .replace(R.id.authFrameLayout, PhoneNumberFragment())
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