package com.children.toyexchange.domain.repository

import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface PostRepository {
    fun getStorePost(): Task<QuerySnapshot>
}