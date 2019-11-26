package com.beok.reposearch.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.beok.reposearch.presenter.model.ReposModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoSearchBoundaryCallback(
    private val user: String,
    private val repoSearchLocalDataSource: RepoSearchDataSource.Local,
    private val repoSearchRemoteDataSource: RepoSearchDataSource.Remote,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PagedList.BoundaryCallback<ReposModel>() {

    private val _err = MutableLiveData<Throwable>()
    private var lastRequestedPage = 1
    private var isRequestInProgress = false

    val err: LiveData<Throwable> get() = _err

    override fun onZeroItemsLoaded() {
        CoroutineScope(ioDispatcher).launch {
            requestAndSaveData()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: ReposModel) {
        CoroutineScope(ioDispatcher).launch {
            requestAndSaveData()
        }
    }

    private suspend fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        repoSearchRemoteDataSource.getRepoList(
            user,
            lastRequestedPage,
            onSuccess = {
                CoroutineScope(ioDispatcher).launch {
                    repoSearchLocalDataSource.insert(it) {
                        lastRequestedPage++
                        isRequestInProgress = false
                    }
                }
            },
            onError = {
                _err.postValue(it)
                isRequestInProgress = false
            }
        )
    }
}