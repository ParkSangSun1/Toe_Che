package com.children.toyexchange.domain.usecase

import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class SearchAddressUseCase @Inject constructor(
    private val toyUploadRepository: ToyUploadRepository
) {
    suspend fun execute(Authorization : String, analyze_type: String, page: Int, size:Int, query : String)  = toyUploadRepository.searchAddress(Authorization = Authorization, analyze_type = analyze_type, page = page, size = size,query= query)
}