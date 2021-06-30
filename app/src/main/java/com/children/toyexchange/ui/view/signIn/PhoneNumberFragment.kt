package com.children.toyexchange.ui.view.signIn

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentPhoneNumberBinding
import com.children.toyexchange.ui.model.user_signin_model.UserSignIn
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.TimeUnit

class PhoneNumberFragment : Fragment() {

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var binding: FragmentPhoneNumberBinding
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var checkFirstPhoneNumberAuth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_phone_number, container, false)
        binding.activity = this


        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireContext(), "잘못된 전화번호 또는 이미 인증코드가 전송되었습니다", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                Log.d("로그", "$storedVerificationId , $resendToken")
                checkFirstPhoneNumberAuth = true
            }
        }



        return binding.root
    }

    private fun verifyVerificationcode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        signInWithPhoneAuthCredential(credential)
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        MainObject.auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("로그", "signInWithCredential:success")
                    val user = task.result?.user
                    Toast.makeText(requireContext(), "전화번호 인증이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    MainObject.signInViewModel.setSignInGoNextTrue()
                    MainObject.signInViewModel.setUserPhoneNumber(
                        binding.phoneNumber.text.toString().toInt()
                    )

                    Log.d("로그", "핸드폰 인증후 사용자 uid : ${MainObject.auth?.uid}")
                    MainObject.database.reference.child("userAccountInfo")
                        .addListenerForSingleValueEvent(object :
                            ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                Log.d(
                                    "로그",
                                    "snapshot : ${snapshot.child(MainObject.auth!!.uid.toString())}"
                                )

                                //신규사용자인지 기존에 정보가 있는 사용자인지 체크, 만약 null 이면 신규사용자
                                if (snapshot.child(MainObject.auth?.uid.toString()).value != null) {
                                    //기존사용자의 uid값에 있는 정보 불러오기
                                    val userSignInModel =
                                        snapshot.child(MainObject.auth?.uid.toString()).getValue(
                                            UserSignIn::class.java
                                        )
                                    val userPhoto: String = userSignInModel!!.userPhoto.toString()
                                    //불러온 정보를 viewmodel에 저장
                                    MainObject.signInViewModel.setUserPhoto(userPhoto)
                                    userSignInModel?.userNickName?.let {
                                        MainObject.signInViewModel.setUserNickname(it)
                                    }
                                    MainObject.signInViewModel.setUserPhoneNumber(userSignInModel?.userPhoneNumber)

                                    val intent = Intent(requireContext(), MainActivity::class.java)
                                    startActivity(intent)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }
                        })

                } else {
                    Log.w("로그", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireContext(), "입력한 코드가 올바르지 않습니다", Toast.LENGTH_SHORT)
                            .show()
                        binding.phoneNumberCode.setText("")
                    }
                }
            }
    }

    //인증번호 확인 클릭
    fun clickCheckPhoneAuthCode(view: View) {
        if (checkFirstPhoneNumberAuth) {
            if (TextUtils.isEmpty(binding.phoneNumberCode.text)) {
                Toast.makeText(requireContext(), "인증번호를 입력해 주세요", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "잠시만 기다려 주세요", Toast.LENGTH_SHORT).show()
                var otp = binding.phoneNumberCode.text.toString().trim()
                if (otp.isNotEmpty()) {
                    verifyVerificationcode(otp)
                }
            }

        } else {
            Toast.makeText(requireContext(), "먼저 휴대폰번호를 인증해 주세요", Toast.LENGTH_SHORT).show()
        }


    }


    //핸드폰 번호 인증 클릭
    fun clickPhoneAuthCode(view: View) {

        if (TextUtils.isEmpty(binding.phoneNumber.text)) {
            Toast.makeText(activity, "전화번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "잠시만 기다려 주세요", Toast.LENGTH_SHORT).show()
            val phoneNumber = binding.phoneNumber.text.substring(1)
            Log.d("로그", "핸드폰 번호 $phoneNumber")
            val options = MainObject.auth?.let {
                PhoneAuthOptions.newBuilder(it)
                    .setPhoneNumber("+82 $phoneNumber")
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(requireActivity())
                    .setCallbacks(callbacks)
                    .build()
            }
            if (options != null) {
                PhoneAuthProvider.verifyPhoneNumber(options)
            }


        }


    }


}