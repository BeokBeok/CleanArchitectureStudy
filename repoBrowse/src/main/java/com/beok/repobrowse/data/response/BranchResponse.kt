package com.beok.repobrowse.data.response

import com.google.gson.annotations.SerializedName

data class BranchResponse(

    @SerializedName("protected")
    val jsonMemberProtected: Boolean? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("commit")
    val commit: CommitResponse? = null
)

fun BranchResponse.mappingToPresenter(): String = name ?: ""