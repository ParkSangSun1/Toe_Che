package com.children.toyexchange.data.repository.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class RemoteSplashDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseRtdb: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage,
    private val fireStore: FirebaseFirestore
){
    fun checkAppVersion(myVersion : String) = firebaseRtdb.reference.child("appVersion").get()
}