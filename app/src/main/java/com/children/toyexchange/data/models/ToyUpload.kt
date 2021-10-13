package com.children.toyexchange.data.models

import android.net.Uri

data class ToyUpload(
    val title : String,
    val contents : String,
    val category : String,
    val location : String,
    val photo : String?,
    val date : String
)
