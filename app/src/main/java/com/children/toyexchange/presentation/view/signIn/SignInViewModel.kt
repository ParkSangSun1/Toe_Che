package com.children.toyexchange.presentation.view.signIn

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.data.repository.FirebaseRepositoryImpl
import com.children.toyexchange.data.models.UserSignIn
import com.children.toyexchange.domain.usecase.*
import com.children.toyexchange.presentation.di.DataStoreModule
import com.children.toyexchange.presentation.widget.utils.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private var auth: FirebaseAuth,
    private val callUserInfoUseCase: CallUserInfoUseCase,
    private val checkUserNickNameUseCase: CheckUserNickNameUseCase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val saveUserNickNameUseCase: SaveUserNickNameUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val dataStore: DataStoreModule
) : ViewModel() {

    private var userNickname: String? = null
    private var userPhoneNumber: Int? = null
    private var userPhoto: String? = null

    //새로 가입인지 기존 가입자인지 확인하기기 (true = 새로운 가입, false = 기존에 가입했던 사람)
    val newUser: LiveData<Boolean> get() = _newUser
    private val _newUser = MutableLiveData<Boolean>()

    //기존 사용자일 경우 기존의 닉네임
    var noNewUserNickname: String? = null

    //회원가입중 다음으로 버튼을 활성화
    val checkGoNext: LiveData<Boolean> get() = _checkGoNext
    private val _checkGoNext = MutableLiveData<Boolean>()

    //RTDB 저장성공 여부
    val successRtdbSave: LiveData<Int> get() = _successRtdbSave
    private val _successRtdbSave = MutableLiveData<Int>()

    //닉네임 중복체크 여부
    val successCheckNickName: LiveData<Int> get() = _successCheckNickName
    private val _successCheckNickName = MutableLiveData<Int>()

    //nickName 저장
    val nickName: LiveData<String> get() = _nickName
    private val _nickName = MutableLiveData<String>()

    //photo 저장 성공여부
    val successCheckPhoto: LiveData<Int> get() = _successCheckPhoto
    private val _successCheckPhoto = MutableLiveData<Int>()


    init {
        _checkGoNext.value = false
        _newUser.value = true
        auth = FirebaseAuth.getInstance()
    }


    //회원가입 다음 프래그먼트로 넘어갈지
    fun setSignInGoNextTrue() {
        _checkGoNext.value = true
    }

    fun setSignInGoNextFalse() {
        _checkGoNext.value = false
    }


    //닉네임 기억
    fun setUserNickname(userNickname: String) {
        this.userNickname = userNickname
    }

    fun getUserNickname(): String? {
        return this.userNickname
    }


    //프로필 사진 기억
    fun setUserPhoto(userPhoto: String?) {
        this.userPhoto = userPhoto.toString()
    }

    fun getUserPhoto(): String? {
        return this.userPhoto
    }


    //핸드폰 번호 기억
    fun setUserPhoneNumber(userPhoneNumber: Int?) {
        this.userPhoneNumber = userPhoneNumber
    }

    fun getUserPhoneNumber(): Int? {
        return this.userPhoneNumber
    }


    //전화번호 인증후 User 정보 가져오기(신규사용자인지 확인하기 위해)
    fun phoneNumberCheckNextCallUserInfo() = callUserInfoUseCase.execute()


    //전화번호 인중, User 정보를 가져온 후 신규사용자인지 확인
    fun phoneNumberCheck(snapshot: DataSnapshot) {

        //신규사용자인지 기존에 정보가 있는 사용자인지 체크, 만약 null 이면 신규사용자
        if (snapshot.child(auth.uid.toString()).value != null) {
            //기존사용자의 uid값에 있는 정보 불러오기
            val userSignInModel =
                snapshot.child(auth.uid.toString()).getValue(UserSignIn::class.java)
            val userPhoto: String? = userSignInModel!!.userPhoto

            //가져온 사용자의 전체 정보 저장
            UserInfo.apply {
                userNickName = userSignInModel.userNickName
                userPhoneNumber = userSignInModel.userPhoneNumber.toString()
                UserInfo.userPhoto = userSignInModel.userPhoto
            }

            //불러온 정보를 viewmodel에 저장
            setUserPhoto(userPhoto)
            userSignInModel.userNickName?.let {
                setUserNickname(it)
            }
            setUserPhoneNumber(userSignInModel.userPhoneNumber)


            //기존사용자인것을 확인하고 처리
            noNewUserNickname = userSignInModel.userNickName
            setSignInGoNextTrue()
            _newUser.value = false
        } else {
            setSignInGoNextTrue()
        }

    }


    //파베 rtdb 닉네임 중복 체크
    fun firebaseCheckNickName(nickName: String) {
        checkUserNickNameUseCase.execute(nickName).addListenerForSingleValueEvent(object :
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
        val fileName = auth.currentUser?.uid.toString() + ".png"

        if (proFileUri != null) {
            saveUserProfileUseCase.execute(proFileUri, fileName)
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


    //유저 정보 RealTimeDataBase 저장 (userAccountInfo)
    fun userInfoRTDBSave(userSignIn: UserSignIn) {
        saveUserInfoUseCase.execute(userSignIn)
            .addOnSuccessListener {
                //유저 닉네임 RealTimeDataBase 저장 (userNickName)
                getUserNickname()?.let {
                    saveUserNickNameUseCase.execute(it)
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

    fun setUserUid(uid : String) = viewModelScope.launch(Dispatchers.IO) {
        dataStore.setUid(uid)
    }

}