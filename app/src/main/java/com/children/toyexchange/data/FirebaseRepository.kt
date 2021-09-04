package com.children.toyexchange.data

import android.net.Uri
import com.children.toyexchange.data.models.user_signin_model.UserSignIn
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val fireBaseSource: FirebaseSource) {
    fun checkUserNickName(nickName : String) = fireBaseSource.checkUserNickName(nickName)

    fun saveUserProfile(profile : Uri, fileName : String) =  fireBaseSource.saveUserProfile(profile, fileName)

    fun callUserInfo() = fireBaseSource.callUserInfo()

    fun saveUserInfo(userSignIn: UserSignIn) = fireBaseSource.saveUserInfo(userSignIn)

    fun saveUserNickName(userNickName : String) = fireBaseSource.saveUserNickName(userNickName)
}