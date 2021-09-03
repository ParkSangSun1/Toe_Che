package com.children.toyexchange.presentation.widget.utils

import com.children.toyexchange.presentation.view.FireBaseViewModel
import com.children.toyexchange.presentation.view.signIn.SignInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object MainObject {
    var auth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    lateinit var signInViewModel: SignInViewModel
    lateinit var fireBaseViewModel : FireBaseViewModel


}