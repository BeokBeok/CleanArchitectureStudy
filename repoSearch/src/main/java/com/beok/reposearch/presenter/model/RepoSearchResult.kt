package com.beok.reposearch.presenter.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class RepoSearchResult(
    val data: LiveData<PagedList<ReposModel>>,
    val error: LiveData<Throwable>
)