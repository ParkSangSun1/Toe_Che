package com.children.toyexchange.data.repository

import com.children.toyexchange.data.repository.datasource.RemotePostDataSource
import com.children.toyexchange.domain.repository.PostRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: RemotePostDataSource
): PostRepository {
    override fun getStorePost():  Task<QuerySnapshot> {
        return postDataSource.getStorePost()
    }
}