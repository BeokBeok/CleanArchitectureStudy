package com.beok.repobrowse.data.source

import com.beok.common.Result
import com.beok.repobrowse.data.RepoBrowseService
import com.beok.repobrowse.data.response.mapToEntity
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoBrowseDataSourceImpl(
    private val retrofit: RepoBrowseService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoBrowseDataSource {

    override suspend fun getRepoFileTree(
        userName: String,
        repoName: String,
        detail: String,
        branchName: String
    ): Result<List<RepoFileTreeEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                retrofit.getRepoFileTree(userName, repoName, detail, branchName)
                    .map { repoFileTree -> repoFileTree.mapToEntity() }
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getRepoBranches(userName: String, repoName: String): Result<List<String>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(retrofit.getRepoBranches(userName, repoName)
                    .map { it.name ?: "master" })
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}