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


        //원하는 조건이 만족 되면 활성화 되는 버튼
        MainObject.signInViewModel.checkGoNext.observe(this, Observer {
            if (it == true) {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#0080ff"))

            } else {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#D6D4D4"))
            }
        })


        //firebase에서 중복 닉네임 체크
        MainObject.fireBaseViewModel.successCheckNickName.observe(this, Observer {
            if (it == 1) {
                Toast.makeText(this, "사용가능한 닉네임 입니다", Toast.LENGTH_SHORT)
                    .show()
                Log.d("로그", "사용가능한 닉네임 확인 완료")
                MainObject.signInViewModel.setUserNickname(MainObject.fireBaseViewModel.nickName.value.toString())
                MainObject.signInViewModel.setSignInGoNextTrue()

              /*  val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
                finish()*/
            } else if (it == 2) {
                Toast.makeText(this, "이미 있는 닉네임 입니다", Toast.LENGTH_SHORT)
                    .show()
            } else if (it == 3) {
                Toast.makeText(this, "예기치 못한 오류", Toast.LENGTH_SHORT)
                    .show()
            }
        })


        //회원가입 마지막 끝낸후 화면 전환
        MainObject.fireBaseViewModel.successRtdbSave.observe(this, Observer {
            if (it == 1) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
                finish()
            } else if (it == 2) {
                Toast.makeText(this, "회원정보 저장에 실패했습니다", Toast.LENGTH_SHORT).show()
            } else if (it == 3) {
                Toast.makeText(this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        })


        //photo 사진 저장성공 여부
   /*     MainObject.fireBaseViewModel.successCheckPhoto.observe(this, Observer {
            if (it == 1) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
                finish()
            } else if (it == 2) {
                Toast.makeText(this, "회원정보 저장에 실패했습니다", Toast.LENGTH_SHORT).show()
            } else if (it == 3) {
            }
        })*/


        MainObject.signInViewModel.noNewUser.observe(this, Observer {
            Log.d("로그", "라이브데이터 noNewUser : $it")
            if (it == false) {
                binding.nextBtn.setBackgroundColor(Color.parseColor("#0080ff"))
                flag = 3
                Toast.makeText(this, "이미 가입된 계정이 존재합니다", Toast.LENGTH_SHORT).show()
                binding.checkPhoneNumber.text =
                    "${MainObject.signInViewModel.noNewUserNickname}으(로)시작하기"
            }
        })

    }

    //조건이 충족되 활성화된 버튼 클릭했을때
    fun clickNextBtn(view: View) {
        Log.d("로그", "UID : ${MainObject.auth?.uid}")
        if (MainObject.signInViewModel.checkGoNext.value == true) {
            when (flag) {
                //PhoneNumber -> NickName
                1 -> {
                    Log.d(
                        "로그", "flag1"
                    )
                    switchFragment()
                    MainObject.signInViewModel.setSignInGoNextFalse()
                }
                //NickName -> RTDB에 성공적으로 저장후 MainActivity로 넘어가기
                2 -> {
                    Log.d(
                        "로그", "flag2"
                    )
                    val userSignIn = UserSignIn(
                        MainObject.signInViewModel.getUserPhoto(),
                        MainObject.signInViewModel.getUserNickname(),
                        MainObject.signInViewModel.getUserPhoneNumber()
                    )

                    //유저 정보 RealTimeDataBase 저장
                    MainObject.fireBaseViewModel.userInfoRTDBSave(userSignIn)

                }
                //계정이 있을경우
                3 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "예기치 못한 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                }
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