package com.beok.repobrowse.data.response

import com.google.gson.annotations.SerializedName

data class LinksResponse(

    @SerializedName("git")
    val git: String? = null,

    @SerializedName("self")
    val self: String? = null,

    @SerializedName("html")
    val html: String? = null
)