package com.children.toyexchange.data.api

import com.children.toyexchange.data.models.searchaddress.SearchAddress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAddressApi {
    @GET("/v2/local/search/address.json")
    suspend fun searchAddress(
        @Header("Authorization") Authorization: String,
        @Query("analyze_type") analyze_type: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("query") query: String

    ) : Response<SearchAddress>
}