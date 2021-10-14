package com.children.toyexchange.presentation.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.children.toyexchange.data.models.GetStorePost
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.domain.usecase.GetStoragePostUseCase
import com.children.toyexchange.domain.usecase.GetStorePostUseCase
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStorePostUseCase: GetStorePostUseCase,
    private val getStoragePostUseCase: GetStoragePostUseCase
): ViewModel() {
    //게시물 불러온 값
    val getPostResponse: LiveData<QuerySnapshot> get() = _getPostResponse
    private val _getPostResponse = MutableLiveData<QuerySnapshot>()

   // var post : ArrayList<GetStorePost?> = arrayListOf()


    //MainActivity에서 보여줄 게시물 가져오기
    fun getStorePost() = getStorePostUseCase.execute()

    fun setGetPostResponse(set : QuerySnapshot){
        _getPostResponse.value = set
    }

    fun getStoragePost(uid: String?, title: String?, num: Int) = getStoragePostUseCase.execute(uid, title, num)

   /* fun inputArray(){
        val querySnapshot = _getPostResponse.value
        Log.d("로그","어뎁터2 ${querySnapshot?.documents}, ${querySnapshot?.size()}")
        for (snapshot in querySnapshot!!.documents) {
            val item = snapshot.toObject(GetStorePost::class.java)
            post.add(item)
        }

        Log.d("로그","어뎁터 $post")
    }*/

}
