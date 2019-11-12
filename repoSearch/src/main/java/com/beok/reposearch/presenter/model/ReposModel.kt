package com.beok.reposearch.presenter.model

data class ReposModel(
    val name: String,
    val fork: Boolean,
    val htmlUrl: String,
    val language: String,
    val stargazersCount: Int,
    val forks: Int,
    val license: String,
    val updateAt: String,
    val defaultBranch: String
)