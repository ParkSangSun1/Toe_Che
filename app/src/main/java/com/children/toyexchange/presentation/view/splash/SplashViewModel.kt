package com.children.toyexchange.presentation.view.splash

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.children.toyexchange.presentation.di.DataStoreModule
import com.children.toyexchange.presentation.view.main.MainActivity
import com.children.toyexchange.presentation.view.signIn.activity.SignInActivity
import com.children.toyexchange.presentation.widget.utils.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStoreModule
): ViewModel() {

    val firstStart : LiveData<Boolean> get() = _firstStart
    private val _firstStart = MutableLiveData<Boolean>()

    fun checkUserInfo(){
        viewModelScope.launch {
            Log.d("로그","dataStore uid : ${dataStore.readUid.first()}")
            if (dataStore.readUid.first() == DataStore.DEFAULT_UID) {
                delay(1000)
                firstStartApp()
            } else {
                delay(1000)
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
}