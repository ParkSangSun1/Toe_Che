package com.children.toyexchange.presentation.view.signIn

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentNickNameBinding
import com.children.toyexchange.presentation.widget.utils.MainObject
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NickNameFragment : Fragment() {

    lateinit var binding: FragmentNickNameBinding
    private var proFileUri: Uri? = null
    private val signInViewModel by viewModels<SignInViewModel>()

    companion object {
        lateinit var progressDialog: ProgressDialog
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nick_name, container, false)
        binding.activity = this


        progressDialog = ProgressDialog(requireContext())


        return binding.root
    }


    //이미지 선택 클릭
    fun clickChoseProfileImage(view: View) {
        //이미지를 선택
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0)
    }


    //이미지 선택후 정보저장후 선택한 이미지 보여주기
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_OK -> {
                proFileUri = data?.data!!
                binding.profile.setImageURI(proFileUri)
                MainObject.signInViewModel.setUserPhoto(proFileUri.toString())


                //MainObject.fireBaseViewModel.uploadFile(proFileUri)
            }
            ImagePicker.RESULT_ERROR -> {
                proFileUri = null
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                proFileUri = null
                Toast.makeText(requireContext(), "작업 취소됨", Toast.LENGTH_SHORT).show()
            }
        }
    }


    //닉네임 edittext 체크
    fun clickCheckNickName(view: View) {
        Log.d("로그", "눌림")

        if (proFileUri != null) {
            if (TextUtils.isEmpty(binding.nickname.text)) {
                Toast.makeText(activity, "닉네임을 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "잠시만 기다려 주세요", Toast.LENGTH_SHORT).show()

                signInViewModel.firebaseCheckNickName(binding.nickname.text.toString())
            }
        } else {
            Toast.makeText(activity, "먼저 프로필 사진을 설정해 주세요", Toast.LENGTH_SHORT).show()

        }

    }

    private fun observeViewModel() {

        //firebase에서 중복 닉네임 체크
        signInViewModel.successCheckNickName.observe(requireActivity(), Observer {
            if (it == 1) {
                Toast.makeText(requireContext(), "사용가능한 닉네임 입니다", Toast.LENGTH_SHORT)
                    .show()
                Log.d("로그", "사용가능한 닉네임 확인 완료")
                signInViewModel.setUserNickname(signInViewModel.nickName.value.toString())
                signInViewModel.setSignInGoNextTrue()

                /*  val intent = Intent(this, MainActivity::class.java)
                  startActivity(intent)
                  overridePendingTransition(R.anim.right_in, R.anim.left_out)
                  finish()*/
            } else if (it == 2) {
                Toast.makeText(requireContext(), "이미 있는 닉네임 입니다", Toast.LENGTH_SHORT)
                    .show()
                signInViewModel.setSignInGoNextFalse()
            } else if (it == 3) {
                Toast.makeText(requireContext(), "예기치 못한 오류", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}