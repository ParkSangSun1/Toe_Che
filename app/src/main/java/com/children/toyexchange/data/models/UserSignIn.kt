package com.children.toyexchange.data.models

data class UserSignIn(
    var userPhoto : String?,
    var userNickName : String?,
    var userPhoneNumber : Int?
){
    constructor() : this(null, null, null)
}