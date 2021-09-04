package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.domain.repository.FirebaseRepository
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute(profile : Uri, fileName : String)  = fireBaseRepository.saveUserProfile(profile, fileName)
}