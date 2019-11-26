package com.beok.reposearch.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.beok.common.base.BaseViewModel
import com.beok.reposearch.domain.usecase.UserRepoSearchUsecase
import com.beok.reposearch.presenter.model.RepoSearchResult
import com.beok.reposearch.presenter.model.ReposModel

class RepoSearchViewModel(
    private val userRepoSearchUsecase: UserRepoSearchUsecase
) : BaseViewModel() {

    private val _userName = MutableLiveData<String>()
    private val _repoSearchResult: LiveData<RepoSearchResult> =
        Transformations.map(_userName) { userRepoSearchUsecase.invoke(it) }
    private val _isLoading = MutableLiveData<Boolean>(false)

    val userName: LiveData<String> get() = _userName
    val repoList: LiveData<PagedList<ReposModel>> =
        Transformations.switchMap(_repoSearchResult) { it.data }
    val errMsg: LiveData<Throwable> =
        Transformations.switchMap(_repoSearchResult) { it.error }
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun searchUserRepo(user: String) {
        if (_userName.value == user) return // 같은 이름 검색 시, observe 방지
        showProgressBar()
        _userName.postValue(user)
        hideProgressBar()
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