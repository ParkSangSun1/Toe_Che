package com.children.toyexchange.domain.usecase

import com.children.toyexchange.data.models.UserSignIn
import com.children.toyexchange.domain.repository.FirebaseRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute(userSignIn: UserSignIn) = fireBaseRepository.saveUserInfo(userSignIn)
}