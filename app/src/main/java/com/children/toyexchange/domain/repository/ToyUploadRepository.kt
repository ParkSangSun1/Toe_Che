package com.children.toyexchange.domain.repository

import com.children.toyexchange.data.models.searchaddress.SearchAddress
import retrofit2.Response

interface ToyUploadRepository {
    suspend fun searchAddress(Authorization : String, analyze_type: String, page: Int, size:Int, query : String) : Response<SearchAddress>
}