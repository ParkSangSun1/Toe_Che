package com.children.toyexchange.domain.repository

import android.net.Uri
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface PostRepository {
    fun getStorePost(): Task<QuerySnapshot>

    fun getStoragePost(uid : String?, title : String?, num: Int) : Task<Uri>
}