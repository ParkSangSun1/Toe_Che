package com.children.toyexchange.presentation.view.myToyUpload

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToyUploadViewModel : ViewModel() {

    //선택한 사진들
    val saveChoicePhoto get() = _saveChoicePhoto
    private val _saveChoicePhoto: MutableLiveData<MutableList<Uri>> =
        MutableLiveData<MutableList<Uri>>()

    //사진 개수 (최대 5장)
    val photoIndex get() = _photoIndex
    private val _photoIndex: MutableLiveData<Int> = MutableLiveData<Int>()

    //게시물 제목
    val postTitle get() = _postTitle
    private val _postTitle: MutableLiveData<String> = MutableLiveData<String>()

    //게시물 내용
    val postContents get() = _postContents
    private val _postContents: MutableLiveData<String> = MutableLiveData<String>()

    init {
        _saveChoicePhoto.value = mutableListOf()
        _photoIndex.value = 0
    }

    fun setSaveChoicePhoto(uri: Uri) {
        _saveChoicePhoto.value?.add(uri)
    }

    //사진 추가 +1
    fun plusPhotoIndex(){
        _photoIndex.value = _photoIndex.value?.plus(1)
    }

    //사진 삭제 -1
    fun minusPhotoIndex(){
        _photoIndex.value = _photoIndex.value?.minus(1)
    }

    fun deleteSaveChoicePhoto(index: Int) {
        _saveChoicePhoto.value?.removeAt(index)
    }

    fun setPostTitle(postTitle: String) {
        _postTitle.value = postTitle
    }

    fun setPostContents(postContents: String) {
        _postContents.value = postContents
    }
}