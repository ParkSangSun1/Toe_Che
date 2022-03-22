package com.children.toyexchange.domain.repository

import com.children.toyexchange.data.models.GetPostFirstImgResponse
import com.children.toyexchange.data.models.Post
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.Task

interface PostRepository {
    fun getStorePost(): Task<QuerySnapshot>

    //fun getStoragePost(uid : String?, title : String?, num: Int) : Task<Uri>

    suspend fun getPostFirstImg(list : ArrayList<Post>) : GetPostFirstImgResponse
}