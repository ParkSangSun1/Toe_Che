package com.children.toyexchange.domain.usecase

import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.SplashRepository
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {
    fun execute(myVersion: String) = splashRepository.checkAppVersion(myVersion)
}