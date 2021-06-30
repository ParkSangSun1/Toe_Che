package com.children.toyexchange.ui.model.user_signin_model

import android.net.Uri

data class UserSignIn(
    var userPhoto : String?,
    var userNickName : String?,
    var userPhoneNumber : Int?
){
    constructor() : this(null, null, null)
}
