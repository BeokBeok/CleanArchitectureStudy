package com.beok.reposearch.data.source.local

import androidx.paging.DataSource
import com.beok.reposearch.data.RepoSearchDataSource
import com.beok.reposearch.domain.entity.ReposEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchLocalDataSource(
    private val repoSearchDao: RepoSearchDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource.Local {

    override suspend fun insert(repos: List<ReposEntity>, insertFinished: () -> Unit) =
        withContext(ioDispatcher) {
            repoSearchDao.insert(repos)
            insertFinished.invoke()
        }

    override fun getRepos(user: String): DataSource.Factory<Int, ReposEntity> =
        repoSearchDao.getRepos(user)
}