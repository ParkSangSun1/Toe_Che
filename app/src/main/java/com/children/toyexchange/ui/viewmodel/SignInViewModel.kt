package com.children.toyexchange.ui.viewmodel

import android.text.BoringLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private var userNickname: String? = null
    private var userPhoneNumber: Int? = null
    private var userPhoto: String? = null

    //새로 가입인지 기존 가입자인지 확인하기기 (true = 새로운 가입, false = 기존에 가입했던 사람)
    val noNewUser get() = _noNewUser
    private val _noNewUser : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var noNewUserNickname : String? = null

    val checkGoNext get() = _checkGoNext
    private val _checkGoNext: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    init {
        _checkGoNext.value = false
        _noNewUser.value = true
    }




    //회원가입 다음 프래그먼트로 넘어갈지
    fun setSignInGoNextTrue() {
        _checkGoNext.value = true
    }

    fun setSignInGoNextFalse() {
        _checkGoNext.value = false
    }


    //닉네임 기억
    fun setUserNickname(userNickname: String) {
        this.userNickname = userNickname
    }

    fun getUserNickname(): String? {
        return this.userNickname
    }


    //프로필 사진 기억
    fun setUserPhoto(userPhoto: String?) {
        this.userPhoto = userPhoto.toString()
    }

    fun getUserPhoto(): String? {
        return this.userPhoto
    }


    //핸드폰 번호 기억
    fun setUserPhoneNumber(userPhoneNumber: Int?) {
        this.userPhoneNumber = userPhoneNumber
    }

    fun getUserPhoneNumber(): Int? {
        return this.userPhoneNumber
    }
}