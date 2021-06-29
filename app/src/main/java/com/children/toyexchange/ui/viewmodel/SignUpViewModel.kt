package com.children.toyexchange.ui.viewmodel

import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private var userNickname : String? = null
    private var userPhoneNumber : Int? = null
    private var userPhoto : Uri? = null

    val checkGoNext get() = _checkGoNext
    private val _checkGoNext: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    init {
        _checkGoNext.value = false
    }

    fun setSignInGoNextTrue(){
        _checkGoNext.value = true
    }

    fun setSignInGoNextFalse(){
        _checkGoNext.value = false
    }
//    fun getSignInNext():Boolean{
//        signInGoNext = checkGoNext.value.toString().toBoolean()
//        return signInGoNext
//    }
//


    //닉네임 기억
    fun setUserNickname(userNickname: String){
        this.userNickname = userNickname
    }
    fun getUserNickname() : String? {
        return this.userNickname
    }


    //프로필 사진 기억
    fun setUserPhoto(userPhoto: Uri?){
        this.userPhoto = userPhoto
    }
    fun getUserPhoto() : Uri? {
        return this.userPhoto
    }


    //핸드폰 번호 기억
    fun setUserPhoneNumber(userPhoneNumber: Int?){
        this.userPhoneNumber= userPhoneNumber
    }
    fun getUserPhoneNumber() : Int? {
        return this.userPhoneNumber
    }
}