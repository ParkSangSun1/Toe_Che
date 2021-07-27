package com.children.toyexchange.viewmodel

import androidx.lifecycle.ViewModel
import com.children.toyexchange.models.user_signin_model.UserSignIn
import com.children.toyexchange.utils.MainObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

class FireBaseViewModel : ViewModel() {

    lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    init {
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
    }


    fun phoneNumberCheck(snapshot: DataSnapshot){
        //신규사용자인지 기존에 정보가 있는 사용자인지 체크, 만약 null 이면 신규사용자
        if (snapshot.child(auth.uid.toString()).value != null) {
            //기존사용자의 uid값에 있는 정보 불러오기
            val userSignInModel =
                snapshot.child(auth.uid.toString()).getValue(
                    UserSignIn::class.java
                )
            val userPhoto: String = userSignInModel!!.userPhoto.toString()

            //불러온 정보를 viewmodel에 저장
            MainObject.signInViewModel.setUserPhoto(userPhoto)
            userSignInModel.userNickName?.let {
                MainObject.signInViewModel.setUserNickname(it)
            }
            MainObject.signInViewModel.setUserPhoneNumber(userSignInModel.userPhoneNumber)

            //기존사용자인것을 확인하고 처리
            MainObject.signInViewModel.noNewUserNickname =
                userSignInModel.userNickName
            MainObject.signInViewModel.setSignInGoNextTrue()
            MainObject.signInViewModel.noNewUser.value = false
        } else {
            MainObject.signInViewModel.setSignInGoNextTrue()
        }
    }



}