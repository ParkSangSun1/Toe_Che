package com.children.toyexchange.domain.usecase

import com.children.toyexchange.domain.repository.PostRepository
import javax.inject.Inject

class GetStoragePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    fun execute(uid: String?, title: String?, num: Int) = postRepository.getStoragePost(uid, title, num)
}