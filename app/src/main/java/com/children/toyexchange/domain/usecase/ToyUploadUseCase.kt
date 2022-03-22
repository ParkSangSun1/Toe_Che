package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class ToyUploadUseCase @Inject constructor(
    private val toyUploadRepository: ToyUploadRepository
) {
    suspend fun execute(data : Post, postID : String, photosList : MutableList<Uri>)  = toyUploadRepository.toyUpload(data, postID, photosList)
}