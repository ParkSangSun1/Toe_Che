package com.children.toyexchange.utils

import android.content.Context
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.viewmodel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object MainObject {
    var auth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    lateinit var signInViewModel: SignInViewModel


    //스크롤뷰, 리사이클러뷰 음영 삭제
    fun shadowDelete(sV: NestedScrollView?, rV: RecyclerView?) {
        sV?.overScrollMode = View.OVER_SCROLL_NEVER
        rV?.overScrollMode = View.OVER_SCROLL_NEVER
    }


    //리사이클러뷰
    fun recyclerViewManager(v: RecyclerView, context: Context) {
        v.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}