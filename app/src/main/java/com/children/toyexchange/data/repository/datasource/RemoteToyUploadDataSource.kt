package com.children.toyexchange.data.repository.datasource

import com.children.toyexchange.data.api.KakaoAddressApi
import javax.inject.Inject

class RemoteToyUploadDataSource @Inject constructor(
    private val searchAddressApi: KakaoAddressApi
) {
    suspend fun searchAddress(Authorization : String, analyze_type: String, page: Int, size:Int, query : String) = searchAddressApi.searchAddress(Authorization = Authorization,analyze_type = analyze_type, page = page, size = size,query = query)
}