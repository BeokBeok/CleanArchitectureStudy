package com.beok.repobrowse.domain.usecase

import com.beok.common.Result
import com.beok.repobrowse.data.RepoBrowseRepository
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoBrowseUsecase(
    private val repoBrowseRepository: RepoBrowseRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getRepoFileTree(
        userName: String,
        repoName: String,
        detail: String = "",
        branchName: String
    ): Result<List<RepoFileTreeEntity>> = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoFileTree(
            userName,
            repoName,
            detail,
            branchName
        )
    }

    suspend fun getRepoBranches(
        userName: String,
        repoName: String
    ): Result<List<String>> = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoBranches(
            userName,
            repoName
        )
    }
}