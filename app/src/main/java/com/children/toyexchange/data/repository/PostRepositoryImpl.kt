package com.children.toyexchange.data.repository

import com.children.toyexchange.data.repository.datasource.RemotePostDataSource
import com.children.toyexchange.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: RemotePostDataSource
): PostRepository {
    override fun getStorePost(): CollectionReference {
        return postDataSource.getStorePost()
    }
}