package com.children.toyexchange.ui.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private var userNickname : String? = null

    fun setUserNickname(userNickname: String){
        this.userNickname = userNickname
    }
    fun getUserNickname() : String? {
        return this.userNickname
    }
}