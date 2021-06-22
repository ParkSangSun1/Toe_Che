package com.children.toyexchange.ui.view.signIn

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentNickNameBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.viewmodel.SignUpViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class NickNameFragment : Fragment() {

    lateinit var binding: FragmentNickNameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nick_name, container, false)
        binding.activity = this


        return binding.root
    }


    //닉네임 edittext 체크
    fun clickCheckNickName(view: View) {
        Log.d("로그","눌림")

        if (TextUtils.isEmpty(binding.nickname.text)) {
            Toast.makeText(activity, "닉네임을 입력해 주세요", Toast.LENGTH_SHORT).show()
        } else {
            firebaseCheckNickName()
        }
    }

    override fun onStop() {
        super.onStop()

    }

    //파베 rtdb 닉네임 중복 체크
    private fun firebaseCheckNickName() {
        Log.d("로그","firebaseCheck 눌림")
        MainObject.database.reference.child("userNickName")
            .child(binding.nickname.text.toString()).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                        snapshot.getValue(
//                            UserModel::class.java
//                        )
                    if (snapshot.value == null) {
                        Toast.makeText(requireContext(), "사용가능한 닉네임 입니다", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("로그","사용가능한 닉네임 확인 완료")
                        MainObject.viewModel.setUserNickname(binding.nickname.text.toString())
                        MainObject.viewModel.setSignInGoNextTrue()

                    } else {
                        Toast.makeText(requireContext(), "이미 있는 닉네임 입니다", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "예기치 못한 오류 $error", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }


}