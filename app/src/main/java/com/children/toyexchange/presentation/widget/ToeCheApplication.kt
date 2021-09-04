package com.children.toyexchange.presentation.widget

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToeCheApplication : Application() {
    override fun onCreate(){
        super.onCreate()
    }
}