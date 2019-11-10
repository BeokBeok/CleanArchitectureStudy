package com.beok.repobrowse.data.source

import com.beok.common.Result
import com.beok.repobrowse.domain.entity.BranchEntity
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity

interface RepoBrowseDataSource {

    suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String,
        branch: String
    ): Result<List<RepoFileTreeEntity>>

    suspend fun getRepoBranch(
        user: String,
        repoName: String
    ): Result<List<BranchEntity>>
}