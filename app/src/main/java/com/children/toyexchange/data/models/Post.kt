package com.children.toyexchange.data.models

import android.net.Uri

data class Post(
    val title: String,
    val contents: String,
    val category: String,
    val location: String,
    val photo: String?,
    val date: String,
    val uid: String,
    val orderBy: String,
    val saveName : String
){
    constructor() : this("null","null","null","null", "null","null","null","null", "null")
}