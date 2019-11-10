package com.beok.repobrowse.data.model

import com.google.gson.annotations.SerializedName

data class Branch(

    @SerializedName("protected")
    val jsonMemberProtected: Boolean? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("commit")
    val commit: Commit? = null
)

fun Branch.mappingToPresenter(): String = name ?: ""