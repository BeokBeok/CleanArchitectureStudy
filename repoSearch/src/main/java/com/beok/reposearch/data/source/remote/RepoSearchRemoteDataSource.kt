package com.beok.reposearch.data.source.remote

import android.util.Log
import com.beok.reposearch.data.RepoSearchDataSource
import com.beok.reposearch.data.response.mapToEntity
import com.beok.reposearch.domain.entity.ReposEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRemoteDataSource(
    private val retrofit: RepoSearchService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource.Remote {

    override suspend fun getRepoList(
        user: String,
        page: Int,
        onSuccess: (repos: List<ReposEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) = withContext(ioDispatcher) {
        Log.d("kkk", "RepoSearchRemoteDataSource getRepoList")
        return@withContext try {
            onSuccess(retrofit.getRepoList(user, page)
                .map { repoList -> repoList.mapToEntity() })
        } catch (e: Exception) {
            onError(e)
        }
    }
}