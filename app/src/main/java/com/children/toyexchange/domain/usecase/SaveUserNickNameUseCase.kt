package com.children.toyexchange.domain.usecase

import com.children.toyexchange.data.models.UserSignIn
import com.children.toyexchange.domain.repository.FirebaseRepository
import javax.inject.Inject

class SaveUserNickNameUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute(userNickName : String) = fireBaseRepository.saveUserNickName(userNickName)
}