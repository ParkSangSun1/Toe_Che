package com.children.toyexchange.domain.repository

import com.google.firebase.firestore.CollectionReference

interface PostRepository {
    fun getStorePost(): CollectionReference
}