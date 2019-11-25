package com.beok.reposearch.data.response

import com.google.gson.annotations.SerializedName

data class LicenseResponse(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("spdx_id")
    val spdxId: String? = null,

    @SerializedName("key")
    val key: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("node_id")
    val nodeId: String? = null
)