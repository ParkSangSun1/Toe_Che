package com.children.toyexchange.data.repository.datasource

import android.net.Uri
import android.util.Log
import com.children.toyexchange.data.models.GetPostFirstImgResponse
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.presentation.widget.utils.Utils.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemotePostDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseRtdb: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage,
    private val fireStore: FirebaseFirestore
) {
    fun getStorePost() =  fireStore.collection("post").orderBy("orderBy", Query.Direction.DESCENDING).get()

    //fun getStoragePost(uid : String?, title : String?, num: Int) = firebaseStorage.reference.child("toyPostImage/$uid/$title/${num}.png").downloadUrl

    suspend fun getPostFirstImg(list : ArrayList<Post>) : GetPostFirstImgResponse {
        val uriList = arrayListOf<Uri>()

        return try {
           // a(list, uriList)
            for (i in list){
                uriList.add(firebaseStorage.reference.child("toyPostImage/${i.saveName}/0").downloadUrl.await())
            }
            GetPostFirstImgResponse(true, uriList)
        }catch (e : Exception){
            GetPostFirstImgResponse(false, null)
        }
    }
    private suspend fun a(list : ArrayList<Post>, uriList : ArrayList<Uri>){


    }
}