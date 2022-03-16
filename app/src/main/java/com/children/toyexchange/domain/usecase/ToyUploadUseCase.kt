package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class ToyUploadUseCase @Inject constructor(
    private val toyUploadRepository: ToyUploadRepository
) {
    fun execute(data : ToyUpload, postID : String)  = toyUploadRepository.toyUpload(data, postID)
}