package com.children.toyexchange.views.signIn

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
import com.children.toyexchange.models.user_signin_model.UserSignIn
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.viewmodel.FireBaseViewModel
import com.children.toyexchange.views.MainActivity
import com.children.toyexchange.views.base.SignInBaseActivity
import com.children.toyexchange.viewmodel.SignInViewModel


class PhoneAuthActivity : SignInBaseActivity() {
    val binding by binding<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth)
    var flag = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        //viewModel 선언
        MainObject.signInViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignInViewModel::class.java)

        MainObject.fireBaseViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FireBaseViewModel::class.java)



        //fragment
        val transaction = supportFragmentManager.beginTransaction()
        binding.checkPhoneNumber.text = "다음으로"
        transaction.add(R.id.authFrameLayout, PhoneNumberFragment())
        flag = 1
        transaction.commit()


        //버튼 라이브데이터
        MainObject.signInViewModel.checkGoNext.observe(this, Observer {
            if (it == true) {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#0080ff"))

            } else {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#D6D4D4"))
            }
        })

        MainObject.signInViewModel.noNewUser.observe(this, Observer {
            Log.d("로그","라이브데이터 noNewUser : $it")
            if (it == false) {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#0080ff"))
                flag=3
                Toast.makeText(this,"이미 가입된 계정이 존재합니다",Toast.LENGTH_SHORT).show()
                binding.checkPhoneNumber.text = "${MainObject.signInViewModel.noNewUserNickname}으(로)시작하기"

            }
        })

    }

    fun clickNextBtn(view: View) {
        Log.d("로그", "UID : ${MainObject.auth?.uid}")
        if (MainObject.signInViewModel.checkGoNext.value == true) {
            if (flag == 2) {
                val userSignIn = UserSignIn(
                    MainObject.signInViewModel.getUserPhoto(),
                    MainObject.signInViewModel.getUserNickname(),
                    MainObject.signInViewModel.getUserPhoneNumber()
                )
                Log.d(
                    "로그", MainObject.signInViewModel.getUserPhoneNumber().toString()
                )

                //유저 정보 RealTimeDataBase 저장
                MainObject.database.reference.child("userAccountInfo")
                    .child(MainObject.auth?.uid.toString()).setValue(userSignIn)
                    .addOnSuccessListener {

                        //유저 닉네임 RealTimeDataBase 저장
                        MainObject.signInViewModel.getUserNickname()?.let {
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


            } else if(flag==1){
                switchFragment()
                MainObject.signInViewModel.setSignInGoNextFalse()
            }else if(flag == 3){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
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
                        .replace(R.id.authFrameLayout, PhoneNumberFragment())
                    flag = 1
                }
                1 -> {
                    binding.checkPhoneNumber.text = "시작하기"
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.authFrameLayout, NickNameFragment())
                    flag = 2
                }


            }
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}