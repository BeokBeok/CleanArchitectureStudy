package com.beok.repobrowse.data.source

import com.beok.common.Result
import com.beok.repobrowse.domain.entity.BranchEntity
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity

interface RepoBrowseDataSource {

    suspend fun getRepoFileTree(
        userName: String,
        repoName: String,
        detail: String,
        branchName: String
    ): Result<List<RepoFileTreeEntity>>

    suspend fun getRepoBranches(
        userName: String,
        repoName: String
    ): Result<List<BranchEntity>>
}