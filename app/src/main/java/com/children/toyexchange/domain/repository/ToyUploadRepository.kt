package com.children.toyexchange.domain.repository

import android.net.Uri
import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.google.firebase.storage.UploadTask
import retrofit2.Response

interface ToyUploadRepository {
    suspend fun searchAddress(Authorization : String, analyze_type: String, page: Int, size:Int, query : String) : Response<SearchAddress>

    fun toyPostImageUpload(image: Uri, postTitle : String, uid : String, index : Int) : UploadTask
}