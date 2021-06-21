package com.children.toyexchange.ui.view

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.children.toyexchange.ui.viewmodel.SignUpViewModel

open class BaseActivity  : AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<T> =
        lazy { DataBindingUtil.setContentView<T>(this, resId) }
    private lateinit var viewModel: SignUpViewModel
}