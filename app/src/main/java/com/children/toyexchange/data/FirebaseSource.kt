package com.children.toyexchange.data

import android.net.Uri
import com.children.toyexchange.data.models.user_signin_model.UserSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseRtdb: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
    ) {

    fun checkUserNickName(nickName : String) = firebaseRtdb.reference.child("userNickName").child(nickName)

    fun saveUserProfile(profile : Uri, fileName : String) = firebaseStorage.getReferenceFromUrl("gs://toyexchange-90199.appspot.com").child("images/$fileName").putFile(profile)

    fun callUserInfo() = firebaseRtdb.reference.child("userAccountInfo")

    fun saveUserInfo(userSignIn: UserSignIn) = firebaseRtdb.reference.child("userAccountInfo").child(firebaseAuth.uid.toString()).setValue(userSignIn)

    fun saveUserNickName(userNickName : String) = firebaseRtdb.reference.child("userNickName").child(userNickName).setValue(firebaseAuth.uid.toString())
}