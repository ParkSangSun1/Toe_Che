package com.children.toyexchange.presentation.view.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.data.models.GetPostFirstImgResponse
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.domain.usecase.GetPostFirstImgUseCase
import com.children.toyexchange.domain.usecase.GetStorePostUseCase
import com.children.toyexchange.presentation.widget.utils.SingleLiveEvent
import com.children.toyexchange.presentation.widget.utils.Utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStorePostUseCase: GetStorePostUseCase,
    private val getPostFirstImgUseCase: GetPostFirstImgUseCase
) : ViewModel() {
    //게시물 불러온 값
    val getImage: LiveData<ArrayList<Post>> get() = _getImage
    private val _getImage = SingleLiveEvent<ArrayList<Post>>()

    var postList: ArrayList<Post> = arrayListOf()
    var firstImgList: ArrayList<Uri> = arrayListOf()

    //화면에 보이는 메시지 이벤트
    val messageEvent: LiveData<Int> get() = _messageEvent
    private val _messageEvent = SingleLiveEvent<Int>()

    //다음 행동을 뭐 할지 보내는 이벤트
    val nextActionEvent: LiveData<Int> get() = _nextActionEvent
    private val _nextActionEvent = SingleLiveEvent<Int>()


    //SearchFragment 에서 보여줄 게시물 가져오기
    fun getStorePost() = getStorePostUseCase.execute()
        .addOnSuccessListener {
            postList = arrayListOf()
            for (snapshot in it.documents) {
                try {
                    val item = snapshot.toObject(Post::class.java)
                    this.postList.add(item!!)
                } catch (e: Exception) {
                    Log.d(TAG, "에러 : $e")
                    _messageEvent.postValue(1)
                }
            }
            getPostFirstImg()
        }
        .addOnFailureListener {
            Log.d(TAG, "에러 : $it")
            _messageEvent.postValue(1)
        }

    private fun getPostFirstImg() = viewModelScope.launch {
        if (postList.isNullOrEmpty()) _messageEvent.postValue(1)
        else {
            with(getPostFirstImgUseCase.execute(list = postList)) {
                if (this.success) {
                    firstImgList = arrayListOf()
                    firstImgList = list!!
                    _nextActionEvent.postValue(1)
                } else _messageEvent.postValue(1)
            }
        }
    }
}