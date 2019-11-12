package com.beok.repobrowse.presenter.model

data class RepoFileTreeModel(
    val name: String,
    val path: String,
    val type: String,
    val downloadUrl: String,
    val depth: Int,
    var expandable: Boolean
)