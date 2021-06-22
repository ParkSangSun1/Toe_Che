package com.children.toyexchange.ui.utils

import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.ui.viewmodel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object MainObject {
    var auth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    lateinit var viewModel: SignUpViewModel


    //스크롤뷰, 리사이클러뷰 음영 삭제
    fun shadowDelete(sV: NestedScrollView?, rV: RecyclerView?) {
        sV?.overScrollMode = View.OVER_SCROLL_NEVER
        rV?.overScrollMode = View.OVER_SCROLL_NEVER
    }
}