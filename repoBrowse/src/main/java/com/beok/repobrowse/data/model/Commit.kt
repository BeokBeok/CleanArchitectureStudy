package com.beok.repobrowse.data.model

import com.google.gson.annotations.SerializedName

data class Commit(

    @SerializedName("sha")
    val sha: String? = null,

    @SerializedName("url")
    val url: String? = null
)