package com.children.toyexchange.data.repository

import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.children.toyexchange.data.repository.datasource.RemoteToyUploadDataSource
import com.children.toyexchange.domain.repository.ToyUploadRepository
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

}