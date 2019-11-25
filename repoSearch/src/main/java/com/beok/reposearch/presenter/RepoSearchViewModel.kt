package com.beok.reposearch.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.reposearch.domain.usecase.UserRepoSearchUsecase
import com.beok.reposearch.presenter.model.ReposModel
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val userRepoSearchUsecase: UserRepoSearchUsecase
) : BaseViewModel() {

    private val _repoList = MutableLiveData<PagedList<ReposModel>>()
    private val _errMsg = MutableLiveData<Throwable>()
    private val _isLoading = MutableLiveData<Boolean>(false)
    private val _userName = MutableLiveData<String>("")

    val repoList: LiveData<PagedList<ReposModel>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg
    val isLoading: LiveData<Boolean> get() = _isLoading
    val userName: LiveData<String> get() = _userName

    fun searchUserRepo(user: String) = viewModelScope.launch {
        if (_userName.value == user) return@launch // 같은 이름 검색 시, observe 방지

        showProgressBar()
        val remoteRepos = userRepoSearchUsecase(user)
        hideProgressBar()
        if (!remoteRepos.succeeded) {
            _errMsg.value = (remoteRepos as? Result.Error)?.exception
                ?: IllegalStateException("Data is null")
            return@launch
        }
        _userName.value = user
        _repoList.postValue((remoteRepos as Result.Success).data)
    }

    fun showRepo(
        user: String,
        repo: String,
        defaultBranch: String
    ) = navigate(
        RepoSearchFragmentDirections.actionReposearchToRepobrowse(
            user,
            repo,
            defaultBranch
        )
    )

    private fun showProgressBar() {
        _isLoading.value = true
    }

    private fun hideProgressBar() {
        _isLoading.value = false
    }

}