package com.beok.repobrowse.data.source

import com.beok.common.Result
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity

interface RepoBrowseDataSource {

    suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String,
        branch: String?
    ): Result<List<RepoFileTreeEntity>>

    suspend fun getRepoBranches(
        user: String,
        repoName: String
    ): Result<List<String>>
}