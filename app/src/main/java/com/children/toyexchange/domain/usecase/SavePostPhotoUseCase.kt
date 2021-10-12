package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class SavePostPhotoUseCase @Inject constructor(
    private val toyUploadRepository: ToyUploadRepository
) {
    fun execute(image: Uri, postTitle : String, uid : String, index : Int) = toyUploadRepository.toyPostImageUpload(image, postTitle, uid, index)
}