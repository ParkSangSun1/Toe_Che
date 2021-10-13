package com.children.toyexchange.data.repository.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class RemotePostDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseRtdb: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage,
    private val fireStore: FirebaseFirestore
) {
    fun getStorePost() =  fireStore.collection("post").orderBy("orderBy", Query.Direction.DESCENDING).get()

    fun getStoragePost(uid : String, title : String, num: Int) = firebaseStorage.reference.child("toyPostImage/$uid/$title/$num").downloadUrl
}