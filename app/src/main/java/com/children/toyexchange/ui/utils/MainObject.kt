package com.children.toyexchange.ui.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object MainObject {
    var auth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()


}