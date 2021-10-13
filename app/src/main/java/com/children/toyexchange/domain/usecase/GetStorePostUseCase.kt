package com.children.toyexchange.domain.usecase

import android.net.Uri
import com.children.toyexchange.domain.repository.PostRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import javax.inject.Inject

class GetStorePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    fun execute() = postRepository.getStorePost()
}