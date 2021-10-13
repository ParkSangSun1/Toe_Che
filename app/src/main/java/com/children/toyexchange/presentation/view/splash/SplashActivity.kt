package com.children.toyexchange.presentation.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.children.toyexchange.R
import com.children.toyexchange.presentation.di.DataStoreModule
import com.children.toyexchange.presentation.view.main.MainActivity
import com.children.toyexchange.presentation.view.signIn.activity.SignInActivity
import com.children.toyexchange.presentation.widget.extension.startActivityWithFinish
import com.children.toyexchange.presentation.widget.utils.DataStore.DEFAULT_UID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity  : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()
    private val uid = FirebaseAuth.getInstance().uid

    // TODO 앱의 현재 버전을 표시 - 업데이트 할때마다 해당하는 버전으로 값을 변경 (Firebase RTDB도 함께)
    private val myVersion = "1.0.0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observeViewModel()
        checkVersion()
    }

    private fun checkVersion(){
        viewModel.checkMyAppVersion()
            .addOnSuccessListener {
                if(it.value == myVersion) viewModel.checkUserInfo()
                else Toast.makeText(this@SplashActivity,"앱의 버전이 다릅니다! 업데이트 하세요",Toast.LENGTH_LONG).show()
            }
    }

    private fun observeViewModel(){
        viewModel.firstStart.observe(this, Observer {
            if (it) {
                startSignInActivity()
            }else {
                viewModel.getUserInfo()
                    .addListenerForSingleValueEvent(object :
                        ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (uid != null)
                            {
                                viewModel.saveUserInfo(uid,snapshot)
                                startMainActivity()
                            }
                            else {
                                Toast.makeText(this@SplashActivity,"uid값이 올바르지 않습니다",Toast.LENGTH_LONG).show()
                                startSignInActivity()
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@SplashActivity,"로그인 중 예기치 못한 오류가 발생했습니다.",Toast.LENGTH_LONG).show()
                            startSignInActivity()
                        }
                    })

            }
        })
    }

    private fun startMainActivity(){
        this.startActivityWithFinish(this, MainActivity::class.java)
    }

    private fun startSignInActivity(){
        this.startActivityWithFinish(this, SignInActivity::class.java)
    }


}