package com.beok.reposearch.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.beok.common.Result
import com.beok.reposearch.domain.entity.mapToModel
import com.beok.reposearch.presenter.model.ReposModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRepository(
    private val repoSearchLocalDataSource: RepoSearchDataSource.Local,
    private val repoSearchRemoteDataSource: RepoSearchDataSource.Remote,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<LiveData<PagedList<ReposModel>>> =
        withContext(ioDispatcher) {
            val dataSourceFactory = repoSearchLocalDataSource.getRepos(user).map { it.mapToModel() }
            val boundaryCallback = RepoSearchBoundaryCallback(
                user,
                repoSearchLocalDataSource,
                repoSearchRemoteDataSource
            )
            val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

            return@withContext try {
                Result.Success(data)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}