package com.children.toyexchange.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToyUploadViewModel : ViewModel() {
    val saveChoicePhoto get() = _saveChoicePhoto
    private val _saveChoicePhoto : MutableLiveData<MutableList<Uri>> = MutableLiveData<MutableList<Uri>>()


    init {
        _saveChoicePhoto.value = mutableListOf()
    }

    fun savePhoto(uri: Uri){
        _saveChoicePhoto.value?.add(uri)
    }
}