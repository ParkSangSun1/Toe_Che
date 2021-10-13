package com.children.toyexchange.data.models

data class GetStorePost(
    val title : String?,
    val contents : String?,
    val category : String?,
    val location : String?,
    val photo : String?,
    val date : String?,
    val uid : String?
){
    constructor() : this(null,null,null,null,null,null,null)
}
