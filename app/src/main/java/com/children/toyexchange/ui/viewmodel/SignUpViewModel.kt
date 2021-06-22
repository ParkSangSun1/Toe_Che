package com.children.toyexchange.ui.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private var userNickname : String? = null
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


    fun setUserNickname(userNickname: String){
        this.userNickname = userNickname
    }
    fun getUserNickname() : String? {
        return this.userNickname
    }
}