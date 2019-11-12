package com.beok.repobrowse.domain.usecase

import com.beok.common.Result
import com.beok.common.succeeded
import com.beok.repobrowse.data.RepoBrowseRepository
import com.beok.repobrowse.domain.entity.mapToModel
import com.beok.repobrowse.presenter.model.RepoFileTreeModel
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
    ): Result<List<RepoFileTreeModel>> = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoFileTree(
            userName,
            repoName,
            detail,
            branchName
        ).let {
            if (it.succeeded) {
                Result.Success(
                    (it as Result.Success).data
                        .map { data ->
                            data.mapToModel()
                        }
                )
            } else {
                it as Result.Error
            }
        }
    }

    suspend fun getRepoBranches(
        userName: String,
        repoName: String
    ): Result<List<String>> = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoBranches(
            userName,
            repoName
        ).let {
            if (it.succeeded) {
                Result.Success(
                    (it as Result.Success).data
                        .map { data ->
                            data.mapToModel()
                        }
                )
            } else {
                it as Result.Error
            }
        }
    }
}