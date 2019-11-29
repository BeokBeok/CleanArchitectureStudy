package com.beok.reposearch.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beok.reposearch.presenter.model.ReposModel

@Entity(tableName = "repos")
data class ReposEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val user: String,
    val fork: Boolean,
    val htmlUrl: String,
    val language: String,
    val stargazersCount: Int,
    val forks: Int,
    val license: String,
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
        license = license,
        updateAt = updateAt,
        defaultBranch = defaultBranch
    )