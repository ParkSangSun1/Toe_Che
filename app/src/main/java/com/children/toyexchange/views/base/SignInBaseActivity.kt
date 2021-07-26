package com.children.toyexchange.views.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.children.toyexchange.viewmodel.SignInViewModel

open class SignInBaseActivity : AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<T> =
        lazy { DataBindingUtil.setContentView<T>(this, resId) }
    private lateinit var viewModel: SignInViewModel

    override fun onBackPressed() {
        Toast.makeText(applicationContext,"뒤로갈수 없습니다", Toast.LENGTH_SHORT).show()
    }
}