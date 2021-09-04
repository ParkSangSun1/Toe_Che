package com.children.toyexchange.domain.usecase

import com.children.toyexchange.domain.repository.FirebaseRepository
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class CallUserInfoUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute() = fireBaseRepository.callUserInfo()
}