package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class SavePostPhotoUseCase @Inject constructor(
    private val toyUploadRepository: ToyUploadRepository
) {
    fun execute(image: Uri, postID : String) = toyUploadRepository.toyPostImageUpload(image, postID)
}