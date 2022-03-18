package com.children.toyexchange.data.repository.datasource

import android.net.Uri
import com.children.toyexchange.data.api.KakaoAddressApi
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.presentation.widget.utils.FirebaseUrl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class RemoteToyUploadDataSource @Inject constructor(
    private val searchAddressApi: KakaoAddressApi,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseRtdb: FirebaseDatabase,
    private val fireStore: FirebaseFirestore
) {
    suspend fun searchAddress(Authorization : String, analyze_type: String, page: Int, size:Int, query : String) = searchAddressApi.searchAddress(Authorization = Authorization,analyze_type = analyze_type, page = page, size = size,query = query)

    fun toyPostImageUpload(image: Uri, postTitle : String, uid : String, index : Int) = firebaseStorage.getReferenceFromUrl(FirebaseUrl.FirebaseStorageUrl).child("toyPostImage/$uid/$postTitle/$index.png").putFile(image)

    fun getToyCategory() = firebaseRtdb.reference.child("toyCategory")

    suspend fun toyUpload(data : ToyUpload, postID :String, photosList : MutableList<Uri>) : Boolean {
        return try {
            for (photoNum in 0 until photosList.size){
                firebaseStorage.getReferenceFromUrl(FirebaseUrl.FirebaseStorageUrl).child("toyPostImage/${data.saveName}/$photoNum.png").putFile(photosList[photoNum]).await()
            }
            fireStore.collection("post").document(postID).set(data).await()
            true
        }catch (e: Exception){
            false
        }
    }
}