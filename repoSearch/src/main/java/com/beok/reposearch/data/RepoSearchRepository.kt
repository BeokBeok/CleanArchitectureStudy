package com.beok.reposearch.data

import androidx.paging.LivePagedListBuilder
import com.beok.reposearch.domain.entity.mapToModel
import com.beok.reposearch.presenter.model.RepoSearchResult

class RepoSearchRepository(
    private val repoSearchLocalDataSource: RepoSearchDataSource.Local,
    private val repoSearchRemoteDataSource: RepoSearchDataSource.Remote
) : RepoSearchDataSource {

    override fun getRepoList(user: String): RepoSearchResult {
        val dataSourceFactory = repoSearchLocalDataSource.getRepos(user).map { it.mapToModel() }
        val boundaryCallback = RepoSearchBoundaryCallback(
            user,
            repoSearchLocalDataSource,
            repoSearchRemoteDataSource
        )
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return RepoSearchResult(data, boundaryCallback.err)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}