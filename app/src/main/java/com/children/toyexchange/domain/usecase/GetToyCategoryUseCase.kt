package com.children.toyexchange.domain.usecase

import com.children.toyexchange.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetToyCategoryUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute() = fireBaseRepository.getToyCategory()
}