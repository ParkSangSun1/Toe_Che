package com.children.toyexchange.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToyUploadViewModel : ViewModel() {
    //선택한 사진들
    val saveChoicePhoto get() = _saveChoicePhoto
    private val _saveChoicePhoto : MutableLiveData<MutableList<Uri>> = MutableLiveData<MutableList<Uri>>()

    //게시물 제목
    val postTitle get() = _postTitle
    private val _postTitle : MutableLiveData<String> = MutableLiveData<String>()

    //게시물 내용
    val postContents get() = _postContents
    private val _postContents : MutableLiveData<String> = MutableLiveData<String>()

    init {
        _saveChoicePhoto.value = mutableListOf()
    }

    fun setSaveChoicePhoto(uri: Uri){
        _saveChoicePhoto.value?.add(uri)
    }

    fun setPostTitle(postTitle : String){
        _postTitle.value = postTitle
    }

    fun setPostContents(postContents:String){
        _postContents.value = postContents
    }
}