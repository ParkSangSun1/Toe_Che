package com.children.toyexchange.ui.view.signIn

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityPhoneAuthBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.BaseActivity
import com.children.toyexchange.ui.viewmodel.SignUpViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class PhoneAuthActivity : BaseActivity() {
    val binding by binding<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth)
    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignUpViewModel::class.java)
    }

    fun clickCheckNickName(view: View) {
        if (TextUtils.isEmpty(binding.nickname.text)) {
            Toast.makeText(this, "닉네임을 입력해 주세요", Toast.LENGTH_SHORT).show()
        } else {
//            MainObject.database.reference.child("usersNickName")
//                .get().addOnSuccessListener {
//                    it.child(binding.nickname.text.toString()) ->
//                    Log.i("firebase", "Got value ${it.value}")
//                }.addOnFailureListener {
//                    Log.e("firebase", "Error getting data", it)
//                }

            MainObject.database.reference.child("userNickName")
                .child(binding.nickname.text.toString()).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
//                        snapshot.getValue(
//                            UserModel::class.java
//                        )
                        Log.d("firebase","get value ${snapshot.value}")
                        if (snapshot.value == null){
                            Toast.makeText(applicationContext, "사용가능한 닉네임 입니다", Toast.LENGTH_SHORT).show()
                            viewModel.setUserNickname(binding.nickname.text.toString())
                        }else{
                            Toast.makeText(applicationContext, "이미 있는 닉네임 입니다", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

        }
    }

    fun clickCheckPhoneNumber(view: View) {
        if (TextUtils.isEmpty(binding.phoneNumber.text)) {
            Toast.makeText(this, "전화번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
    }
}