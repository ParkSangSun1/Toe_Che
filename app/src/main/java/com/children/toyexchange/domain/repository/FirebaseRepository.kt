package com.children.toyexchange.domain.repository

import android.net.Uri
import com.children.toyexchange.data.models.UserSignIn
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.UploadTask

interface FirebaseRepository {
    fun checkUserNickName(nickName : String) : DatabaseReference
    fun saveUserProfile(profile : Uri, fileName : String) : UploadTask
    fun callUserInfo() : DatabaseReference
    fun saveUserInfo(userSignIn: UserSignIn) : Task<Void>
    fun saveUserNickName(userNickName : String) : Task<Void>
    fun getToyCategory() : DatabaseReference
}