package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.domain.repository.FirebaseRepository
import javax.inject.Inject

class ToyUploadUseCase @Inject constructor(
    private val fireBaseRepository: FirebaseRepository
) {
    fun execute(uid : String, postName : String, data : ToyUpload, postID : String)  = fireBaseRepository.toyUpload(uid, postName, data, postID)
}