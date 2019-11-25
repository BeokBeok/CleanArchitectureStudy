package com.beok.reposearch.data

import android.util.Log
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

    private var lastRequestedPage = 1
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        Log.d("kkk", "onZeroItemsLoaded")
        CoroutineScope(ioDispatcher).launch {
            requestAndSaveData()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: ReposModel) {
        Log.d("kkk", "onItemAtEndLoaded")
        CoroutineScope(ioDispatcher).launch {
            requestAndSaveData()
        }
    }

    private suspend fun requestAndSaveData() {
        Log.d("kkk", "BoundaryCallback requestAndSaveData")
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
                isRequestInProgress = false
            }
        )
    }
}