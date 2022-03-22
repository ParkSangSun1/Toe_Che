package com.children.toyexchange.domain.repository

import android.net.Uri
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.google.firebase.database.DatabaseReference
import retrofit2.Response

interface ToyUploadRepository {
    suspend fun searchAddress(Authorization : String, analyze_type: String, page: Int, size:Int, query : String) : Response<SearchAddress>

    fun getToyCategory() : DatabaseReference

    suspend fun toyUpload(data : Post, postID :String, photosList : MutableList<Uri>) : Boolean
}