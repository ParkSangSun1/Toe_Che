package com.children.toyexchange.domain.usecase

import com.children.toyexchange.domain.repository.FirebaseRepository
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class CheckUserNickNameUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute(nickName: String) = fireBaseRepository.checkUserNickName(nickName)
}