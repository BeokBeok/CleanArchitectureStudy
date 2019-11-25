package com.beok.reposearch.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.beok.common.Result
import com.beok.reposearch.domain.entity.ReposEntity
import com.beok.reposearch.presenter.model.ReposModel

interface RepoSearchDataSource {

    suspend fun getRepoList(user: String): Result<LiveData<PagedList<ReposModel>>>

    interface Local {
        suspend fun insert(repos: List<ReposEntity>, insertFinished: () -> Unit)

        suspend fun getRepos(user: String): DataSource.Factory<Int, ReposEntity>
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