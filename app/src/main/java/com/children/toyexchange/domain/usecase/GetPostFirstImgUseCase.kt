package com.children.toyexchange.domain.usecase

import com.children.toyexchange.data.models.Post
import com.children.toyexchange.domain.repository.PostRepository
import javax.inject.Inject

class GetPostFirstImgUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(list: ArrayList<Post>) = postRepository.getPostFirstImg(list)
}