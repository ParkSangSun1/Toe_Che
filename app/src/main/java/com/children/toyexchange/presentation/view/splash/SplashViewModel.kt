package com.children.toyexchange.presentation.view.splash

import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.data.models.UserSignIn
import com.children.toyexchange.domain.usecase.CallUserInfoUseCase
import com.children.toyexchange.domain.usecase.CheckAppVersionUseCase
import com.children.toyexchange.presentation.di.DataStoreModule
import com.children.toyexchange.presentation.view.main.MainActivity
import com.children.toyexchange.presentation.view.signIn.activity.SignInActivity
import com.children.toyexchange.presentation.widget.utils.DataStore
import com.children.toyexchange.presentation.widget.utils.UserInfo
import com.google.firebase.database.DataSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStoreModule,
    private val callUserInfoUseCase: CallUserInfoUseCase,
    private val checkAppVersionUseCase: CheckAppVersionUseCase
    ): ViewModel() {

    val firstStart : LiveData<Boolean> get() = _firstStart
    private val _firstStart = MutableLiveData<Boolean>()

    fun checkUserInfo(){
        viewModelScope.launch {
            Log.d("로그","dataStore uid : ${dataStore.readUid.first()}")
            if (dataStore.readUid.first() == DataStore.DEFAULT_UID) {
               // delay(1000)
                firstStartApp()
            } else {
               // delay(1000)
                againStartApp()
            }
        }
    }


    private suspend fun firstStartApp() {
        _firstStart.value = true
    }

    private suspend fun againStartApp() {
        _firstStart.value = false
    }

    //유저정보 가져오기
    fun getUserInfo() = callUserInfoUseCase.execute()

    //Object 전역변수에 불러온 사용자 정보 저장
     fun saveUserInfo(uid : String,snapshot: DataSnapshot){
         val userSignInModel =
             snapshot.child(uid).getValue(UserSignIn::class.java)

         UserInfo.apply {
             userNickName = userSignInModel?.userNickName
             userPhoneNumber = userSignInModel?.userPhoneNumber.toString()
             userPhoto = userSignInModel?.userPhoto.toString().toUri()
         }
    }

    fun checkMyAppVersion() = checkAppVersionUseCase.execute()
}