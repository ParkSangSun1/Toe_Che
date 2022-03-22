package com.children.toyexchange.data.repository

import com.children.toyexchange.data.models.GetPostFirstImgResponse
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.data.repository.datasource.RemotePostDataSource
import com.children.toyexchange.domain.repository.PostRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: RemotePostDataSource
): PostRepository {
    override fun getStorePost():  Task<QuerySnapshot> {
        return postDataSource.getStorePost()
    }

    override suspend fun getPostFirstImg(list: ArrayList<Post>): GetPostFirstImgResponse {
        return postDataSource.getPostFirstImg(list)
    }

/*    override fun getStoragePost(uid: String?, title: String?, num: Int): Task<Uri> {
        return postDataSource.getStoragePost(uid, title, num)
    }*/
}