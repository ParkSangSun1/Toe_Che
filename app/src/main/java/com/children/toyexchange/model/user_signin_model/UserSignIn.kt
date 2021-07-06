package com.children.toyexchange.model.user_signin_model

data class UserSignIn(
    var userPhoto : String?,
    var userNickName : String?,
    var userPhoneNumber : Int?
){
    constructor() : this(null, null, null)
}
