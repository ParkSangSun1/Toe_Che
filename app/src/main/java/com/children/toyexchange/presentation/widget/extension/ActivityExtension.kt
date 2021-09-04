package com.children.toyexchange.presentation.widget.extension

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.children.toyexchange.presentation.base.SignInBaseActivity

//Activity Intent
fun AppCompatActivity.startActivityWithFinish(context: Context, activity: Class<*>) {
    startActivity(Intent(context, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    this.finish()
}

//SignInBaseActivity Intent
fun SignInBaseActivity.startActivityWithFinish(context: Context, activity: Class<*>) {
    startActivity(Intent(context, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    this.finish()
}