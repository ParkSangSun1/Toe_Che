package com.children.toyexchange.viewmodel

import android.app.ProgressDialog
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.children.toyexchange.models.user_signin_model.UserSignIn
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.views.signIn.NickNameFragment.Companion.progressDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

class FireBaseViewModel : ViewModel() {

    lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase

    //RTDB 저장성공 여부
    val successRtdbSave get() = _successRtdbSave
    private val _successRtdbSave: MutableLiveData<Int> = MutableLiveData<Int>()

    //닉네임 중복체크 여부
    val successCheckNickName get() = _successCheckNickName
    private val _successCheckNickName: MutableLiveData<Int> = MutableLiveData<Int>()

    //nickName 저장
    val nickName get() = _nickName
    private val _nickName: MutableLiveData<String> = MutableLiveData<String>()

    //photo 저장 성공여부
    val successCheckPhoto get() = _successCheckPhoto
    private val _successCheckPhoto: MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        _successCheckNickName.value = 0
        _successCheckNickName.value = 0
    }


    //전화번호 인증 성공 후
    fun phoneNumberCheck(snapshot: DataSnapshot) {
        //신규사용자인지 기존에 정보가 있는 사용자인지 체크, 만약 null 이면 신규사용자
        if (snapshot.child(auth.uid.toString()).value != null) {
            //기존사용자의 uid값에 있는 정보 불러오기
            val userSignInModel =
                snapshot.child(auth.uid.toString()).getValue(
                    UserSignIn::class.java
                )
            val userPhoto: String = userSignInModel!!.userPhoto.toString()

            //불러온 정보를 viewmodel에 저장
            MainObject.signInViewModel.setUserPhoto(userPhoto)
            userSignInModel.userNickName?.let {
                MainObject.signInViewModel.setUserNickname(it)
            }
            MainObject.signInViewModel.setUserPhoneNumber(userSignInModel.userPhoneNumber)

            //기존사용자인것을 확인하고 처리
            MainObject.signInViewModel.noNewUserNickname =
                userSignInModel.userNickName
            MainObject.signInViewModel.setSignInGoNextTrue()
            MainObject.signInViewModel.noNewUser.value = false
        } else {
            MainObject.signInViewModel.setSignInGoNextTrue()
        }
    }


    //파베 rtdb 닉네임 중복 체크
    fun firebaseCheckNickName(nickName: String) {
        Log.d("로그", "firebaseCheck 눌림")
        MainObject.database.reference.child("userNickName")
            .child(nickName).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                        snapshot.getValue(
//                            UserModel::class.java
//                        )

                    _nickName.value = nickName
                    if (snapshot.value == null) {
                        _successCheckNickName.value = 1

                    } else {
                        _successCheckNickName.value = 2

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _successCheckNickName.value = 3

                }
            })
    }



    //firebase storage에 사진 업로드
    fun uploadFile(proFileUri: Uri?) {
        val storage = FirebaseStorage.getInstance()

        val filename = MainObject.auth?.currentUser?.uid.toString() + ".png"
        val storageRef = storage.getReferenceFromUrl("gs://toyexchange-90199.appspot.com")
            .child("images/$filename")

        proFileUri?.let {
            storageRef.putFile(it)
                .addOnSuccessListener {
                    _successCheckPhoto.value = 1
                }
                .addOnFailureListener {
                    _successCheckPhoto.value = 2

                }
                .addOnProgressListener { taskSnapshot ->
                    _successCheckPhoto.value = 3
                }
        }
    }


    //유저 정보 RealTimeDataBase 저장
    fun userInfoRTDBSave(userSignIn: UserSignIn) {
        MainObject.database.reference.child("userAccountInfo")
            .child(MainObject.auth?.uid.toString()).setValue(userSignIn)
            .addOnSuccessListener {

                //유저 닉네임 RealTimeDataBase 저장
                MainObject.signInViewModel.getUserNickname()?.let {
                    MainObject.database.reference.child("userNickName").child(
                        it
                    ).setValue(MainObject.auth?.uid.toString())
                        .addOnSuccessListener {
                            _successRtdbSave.value = 1
                        }
                        .addOnFailureListener {
                            _successRtdbSave.value = 2
                        }
                }
            }
            .addOnFailureListener {
                _successRtdbSave.value = 3
            }
    }


}