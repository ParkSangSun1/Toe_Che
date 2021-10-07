package com.children.toyexchange.presentation.view.myToyUpload

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.children.toyexchange.domain.usecase.GetToyCategoryUseCase
import com.children.toyexchange.domain.usecase.SearchAddressUseCase
import com.children.toyexchange.domain.usecase.ToyUploadUseCase
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ToyUploadViewModel @Inject constructor(
    private val getToyCategoryUseCase: GetToyCategoryUseCase,
    private val toyUploadUseCase: ToyUploadUseCase,
    private val searchAddressUseCase: SearchAddressUseCase
) : ViewModel() {


    //선택한 사진들
    val saveChoicePhoto: LiveData<MutableList<Uri>> get() = _saveChoicePhoto
    private val _saveChoicePhoto = MutableLiveData<MutableList<Uri>>()

    //사진 개수 (최대 5장)
    val photoIndex: LiveData<Int> get() = _photoIndex
    private val _photoIndex = MutableLiveData<Int>()

    //게시물 제목
    val postTitle: LiveData<String> get() = _postTitle
    private val _postTitle = MutableLiveData<String>()

    //게시물 내용
    val postContents: LiveData<String> get() = _postContents
    private val _postContents = MutableLiveData<String>()

    //카테고리 가져온 내용
    val getCategory: LiveData<DatabaseReference> get() = _getCategory
    private val _getCategory = MutableLiveData<DatabaseReference>()

    //사용자가 선택한 카테고리
    val userChoiceCategory: LiveData<String> get() = _userChoiceCategory
    private val _userChoiceCategory = MutableLiveData<String>()

    //주소검색 api response
    val searchAddressResponse: LiveData<Response<SearchAddress>?> get() = _searchAddressResponse
    private val _searchAddressResponse = MutableLiveData<Response<SearchAddress>?>()

    //사용자가 선택한 주소
    val postAddress: LiveData<String?> get() = _postAddress
    private val _postAddress = MutableLiveData<String?>()

    init {
        _saveChoicePhoto.value = mutableListOf()
        _photoIndex.value = 0
    }

    fun setSaveChoicePhoto(uri: Uri) {
        _saveChoicePhoto.value?.add(uri)
    }

    //사진 추가 +1
    fun plusPhotoIndex() {
        _photoIndex.value = _photoIndex.value?.plus(1)
    }

    //사진 삭제 -1
    fun minusPhotoIndex() {
        _photoIndex.value = _photoIndex.value?.minus(1)
    }

    fun deleteSaveChoicePhoto(index: Int) {
        _saveChoicePhoto.value?.removeAt(index)
    }

    fun setPostTitle(postTitle: String) {
        _postTitle.value = postTitle
    }

    fun setSearchAddressNull(){
        _searchAddressResponse.value = null
    }
    fun setPostAddress(postAddress: String?) {
        _postAddress.value = postAddress
    }

    fun setPostContents(postContents: String) {
        _postContents.value = postContents
    }

    //저장된 카테고리 불러오기
    fun getToyCategory() {
        _getCategory.value = getToyCategoryUseCase.execute()
    }

    fun setUserChoiceCategory(choice: String) {
        _userChoiceCategory.value = choice
    }

    //게시물 올리기
    fun toyUpload(uid: String, postName: String, data: ToyUpload) =
        toyUploadUseCase.execute(uid, postName, data)


    fun searchAddress(
        Authorization: String,
        analyze_type: String,
        page: Int,
        size: Int,
        query: String
    ) = viewModelScope.launch {
        try {
            searchAddressUseCase.execute(
                Authorization = Authorization,
                analyze_type = analyze_type,
                page = page,
                size = size,
                query = query
            ).let { response ->
                if (response.isSuccessful) {
                    _searchAddressResponse.value = response
                }
            }
        } catch (e: Exception) {

        }
    }
}