package com.beok.reposearch.domain.usecase

import com.beok.reposearch.data.RepoSearchDataSource

class UserRepoSearchUsecase(
    private val repoSearchRepository: RepoSearchDataSource
) {

    operator fun invoke(user: String) =
        repoSearchRepository.getRepoList(user)
}