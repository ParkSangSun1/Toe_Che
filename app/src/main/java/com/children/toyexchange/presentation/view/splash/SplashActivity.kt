package com.children.toyexchange.presentation.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.children.toyexchange.R
import com.children.toyexchange.presentation.di.DataStoreModule
import com.children.toyexchange.presentation.view.main.MainActivity
import com.children.toyexchange.presentation.view.signIn.activity.SignInActivity
import com.children.toyexchange.presentation.widget.utils.DataStore.DEFAULT_UID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity  : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observeViewModel()
        viewModel.checkUserInfo()
    }

    private fun observeViewModel(){
        viewModel.firstStart.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }
}