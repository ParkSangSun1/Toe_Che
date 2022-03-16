package com.children.toyexchange.domain.usecase

import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class GetToyCategoryUseCase @Inject constructor(
    private val toyUploadRepository: ToyUploadRepository
) {
    fun execute() = toyUploadRepository.getToyCategory()
}