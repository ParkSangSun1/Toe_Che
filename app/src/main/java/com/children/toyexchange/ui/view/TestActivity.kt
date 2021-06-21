package com.children.toyexchange.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityPhoneAuthBinding
import com.children.toyexchange.databinding.ActivityTestBinding
import com.children.toyexchange.ui.utils.MainObject
import com.children.toyexchange.ui.view.signIn.PhoneAuthActivity
import com.children.toyexchange.ui.viewmodel.SignUpViewModel

class TestActivity : BaseActivity() {
    val binding by binding<ActivityTestBinding>(R.layout.activity_test)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.testtxt.text = MainObject.viewModel.getUserNickname()
    }
}