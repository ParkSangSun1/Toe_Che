package com.children.toyexchange.data.repository

import android.net.Uri
import com.children.toyexchange.data.models.UserSignIn
import com.children.toyexchange.data.repository.datasource.RemoteFirebaseDataSource
import com.children.toyexchange.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val fireBaseSource: RemoteFirebaseDataSource
) : FirebaseRepository {
    override fun checkUserNickName(nickName: String) = fireBaseSource.checkUserNickName(nickName)

    override fun saveUserProfile(profile: Uri, fileName: String) = fireBaseSource.saveUserProfile(profile, fileName)

    override fun callUserInfo() = fireBaseSource.callUserInfo()

    override fun saveUserInfo(userSignIn: UserSignIn) = fireBaseSource.saveUserInfo(userSignIn)

    override fun saveUserNickName(userNickName: String) = fireBaseSource.saveUserNickName(userNickName)
}