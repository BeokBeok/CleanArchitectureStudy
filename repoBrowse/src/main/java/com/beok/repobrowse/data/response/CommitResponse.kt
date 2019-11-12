package com.beok.repobrowse.data.response

import com.google.gson.annotations.SerializedName

data class CommitResponse(

    @SerializedName("sha")
    val sha: String? = null,

    @SerializedName("url")
    val url: String? = null
)