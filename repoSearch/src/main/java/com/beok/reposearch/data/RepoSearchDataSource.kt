package com.beok.reposearch.data

import androidx.paging.DataSource
import com.beok.reposearch.domain.entity.ReposEntity
import com.beok.reposearch.presenter.model.RepoSearchResult

interface RepoSearchDataSource {

    fun getRepoList(user: String): RepoSearchResult

    interface Local {
        suspend fun insert(repos: List<ReposEntity>, insertFinished: () -> Unit)

        fun getRepos(user: String): DataSource.Factory<Int, ReposEntity>
    }

    interface Remote {
        suspend fun getRepoList(
            user: String,
            page: Int,
            onSuccess: (repos: List<ReposEntity>) -> Unit,
            onError: (t: Throwable) -> Unit
        )
    }
}