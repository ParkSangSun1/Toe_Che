package com.children.toyexchange.presentation.view.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.data.models.GetStorePost
import com.children.toyexchange.domain.usecase.GetStoragePostUseCase
import com.children.toyexchange.domain.usecase.GetStorePostUseCase
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStorePostUseCase: GetStorePostUseCase,
    private val getStoragePostUseCase: GetStoragePostUseCase
) : ViewModel() {
    //게시물 불러온 값
    val getPostResponse: LiveData<QuerySnapshot> get() = _getPostResponse
    private val _getPostResponse = MutableLiveData<QuerySnapshot>()

    val getImage: LiveData<ArrayList<GetStorePost?>> get() = _getImage
    private val _getImage = MutableLiveData<ArrayList<GetStorePost?>>()
    var post: ArrayList<GetStorePost?> = arrayListOf()
    private var getAndSaveImage: Array<Uri?> = Array(5, { item->null})
    var imageIndex = 0
    lateinit var saveGetImageArray: Array<Array<Uri?>?>


    //MainActivity에서 보여줄 게시물 가져오기
    fun getStorePost() = getStorePostUseCase.execute()

    fun setGetPostResponse(set: QuerySnapshot) {
        _getPostResponse.value = set
    }

    fun getStoragePost(uid: String?, title: String?, num: Int) =
        getStoragePostUseCase.execute(uid, title, num)

    fun getPostDataProcessing() {
        for (snapshot in _getPostResponse.value!!.documents) {
            val item = snapshot.toObject(GetStorePost::class.java)
            this.post.add(item)
        }
        _getImage.value = this.post
    }

    private fun setAllIndexNull() {
        for (index in 0 until getAndSaveImage.size) {
            getAndSaveImage[index] = null
        }
    }

    private suspend fun callGetStoragePost(uid: String?, outIndex: Int, inIndex: Int) {
            getStoragePost(uid, _getImage.value!![outIndex]?.title, inIndex)
                .addOnCompleteListener {
                    Log.d("로그", "it.result : ${it.result}, outIndex-inIndex : $outIndex-$inIndex")
                    if (it.isSuccessful) {
                        getAndSaveImage[inIndex] = it.result
                        Log.d("로그", "out saveGetImageArray : $saveGetImageArray")
                        Log.d("로그", "out getAndSaveImage : ${getAndSaveImage[inIndex]}")

                        if(!getAndSaveImage.contains(null)) {
                            Log.d("로그", "여기 실행하는데?")
                            saveGetImageArray[outIndex] = getAndSaveImage
                            Log.d("로그", "saveGetImageArray : $saveGetImageArray")
                            setAllIndexNull()
                            Log.d("로그", "getAndSaveImage : $getAndSaveImage")
                        }
                    }
                }
    }

    fun getPostImage(uid: String?) {
        saveGetImageArray = Array(_getImage.value!!.size, {item -> null})
        viewModelScope.launch {
            for (outIndex in 0 until _getImage.value!!.size) {
                for (inIndex in 0 until _getImage.value!![outIndex]?.photo.toString().toInt()) {
                        callGetStoragePost(uid, outIndex, inIndex)
                }
            }
        }
    }
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
