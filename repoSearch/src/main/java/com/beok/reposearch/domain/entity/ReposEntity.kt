package com.beok.reposearch.domain.entity

import com.beok.reposearch.presenter.model.ReposModel

data class ReposEntity(
    val name: String,
    val fork: Boolean,
    val htmlUrl: String,
    val language: String,
    val stargazersCount: Int,
    val forks: Int,
    val license: LicenseEntity,
    val updateAt: String,
    val defaultBranch: String
)

fun ReposEntity.mapToModel(): ReposModel =
    ReposModel(
        name = name,
        fork = fork,
        htmlUrl = htmlUrl,
        language = language,
        stargazersCount = stargazersCount,
        forks = forks,
        license = license.mapToModel(),
        updateAt = updateAt,
        defaultBranch = defaultBranch
    )