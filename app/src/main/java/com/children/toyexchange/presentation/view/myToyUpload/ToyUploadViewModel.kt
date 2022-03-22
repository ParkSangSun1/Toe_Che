package com.children.toyexchange.presentation.view.myToyUpload

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.data.models.searchaddress.SearchAddress
import com.children.toyexchange.domain.usecase.GetToyCategoryUseCase
import com.children.toyexchange.domain.usecase.SearchAddressUseCase
import com.children.toyexchange.domain.usecase.ToyUploadUseCase
import com.children.toyexchange.presentation.widget.utils.SingleLiveEvent
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ToyUploadViewModel @Inject constructor(
    private val getToyCategoryUseCase: GetToyCategoryUseCase,
    private val toyUploadUseCase: ToyUploadUseCase,
    private val searchAddressUseCase: SearchAddressUseCase,
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

    //게시물을 올릴때의 시간(postID에 이용)
    val postUploadTime: LiveData<String> get() = _postUploadTime
    private val _postUploadTime = MutableLiveData<String>()

    //뒤로가기 버튼 클릭
    val backBtnEvent: LiveData<Int> get() = _backBtnEvent
    private val _backBtnEvent = SingleLiveEvent<Int>()

    //게시물 올리고 나서 액티비티 종료 이벤트
    val successPostUpload: LiveData<Boolean> get() = _successPostUpload
    private val _successPostUpload = MutableLiveData<Boolean>()


    //게시물 업로드 성공 이벤트
    // 0 = 초기 초기화, 1 = 성공, 2 = 실패, 3 = 사진이 없음
    val toyUploadEvent: LiveData<Int> get() = _toyUploadEvent
    private val _toyUploadEvent = SingleLiveEvent<Int>()

    init {
        _saveChoicePhoto.value = mutableListOf()
        _photoIndex.value = 0
    }


    fun setSuccessPostUpload(set : Boolean){
        _successPostUpload.value = set
    }

    //사진 추가 +1
    fun plusPhotoIndex() {
        _photoIndex.value = _photoIndex.value?.plus(1)
    }

    //사진 삭제 -1
    fun minusPhotoIndex() {
        _photoIndex.value = _photoIndex.value?.minus(1)
    }

    fun setSaveChoicePhoto(uri: Uri) {
        _saveChoicePhoto.value?.add(uri)
    }

    fun deleteSaveChoicePhoto(index: Int) {
        _saveChoicePhoto.value?.removeAt(index)
    }

    fun setPostTitle(postTitle: String) {
        _postTitle.value = postTitle
    }

    fun setSearchAddressNull() {
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
    fun toyUpload(data : Post, postID : String) = viewModelScope.launch {
        //올리는 사진이 0개인지 체크
        if (_saveChoicePhoto.value == null) _toyUploadEvent.postValue(3)
        else{
            if (toyUploadUseCase.execute(data, postID, _saveChoicePhoto.value!!))_toyUploadEvent.postValue(1)
            else _toyUploadEvent.postValue(2)
        }
    }

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

    //뒤로가기 버튼 클릭
    fun setBackBtnEvent() = _backBtnEvent.call()
}