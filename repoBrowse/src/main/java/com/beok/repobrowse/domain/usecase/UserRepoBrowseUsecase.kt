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
        user: String,
        repo: String,
        detail: String = "",
        branch: String?
    ): Result<List<RepoFileTreeEntity>> = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoFileTree(
            user,
            repo,
            detail,
            branch
        )
    }

    suspend fun getRepoBranches(
        user: String,
        repoName: String
    ): Result<List<String>> = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoBranches(
            user,
            repoName
        )
    }
}