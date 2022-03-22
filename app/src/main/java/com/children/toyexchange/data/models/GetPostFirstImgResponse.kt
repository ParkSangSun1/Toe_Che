package com.children.toyexchange.data.models

import android.net.Uri

data class GetPostFirstImgResponse(
    val success: Boolean,
    val list: ArrayList<Uri>?
)