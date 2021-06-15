package com.children.toyexchange.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.children.toyexchange.R
import com.children.toyexchange.ui.SignUp.SignInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                startActivity(
                    Intent(
                        this,
                        SignInActivity::class.java
                    )
                )
                finish()
            },
            2000
        )
    }
}