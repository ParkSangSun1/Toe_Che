package com.children.toyexchange.data.repository

import android.net.Uri
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.children.toyexchange.data.repository.datasource.RemoteToyUploadDataSource
import com.children.toyexchange.domain.repository.ToyUploadRepository
import com.google.firebase.database.DatabaseReference
import retrofit2.Response
import javax.inject.Inject

class ToyUploadRepositoryImpl @Inject constructor(
      private val toyUploadDataSource: RemoteToyUploadDataSource
) : ToyUploadRepository{
      override suspend fun searchAddress(
            Authorization: String,
            analyze_type: String,
            page: Int,
            size: Int,
            query: String
      ): Response<SearchAddress> {
          return toyUploadDataSource.searchAddress(Authorization = Authorization, analyze_type = analyze_type, page = page, size = size,query= query)
      }

      override fun getToyCategory(): DatabaseReference = toyUploadDataSource.getToyCategory()

      override suspend fun toyUpload(data : Post, postID :String, photosList : MutableList<Uri>) = toyUploadDataSource.toyUpload(data,postID, photosList)

}