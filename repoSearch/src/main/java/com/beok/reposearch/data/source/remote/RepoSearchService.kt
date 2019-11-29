package com.beok.reposearch.data.source.remote

import com.beok.reposearch.data.response.ReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoSearchService {

    @GET("users/{user}/repos")
    suspend fun getRepoList(
        @Path("user")
        user: String,
        @Query("page")
        page: Int,
        @Query("per_page")
        per_page: Int = 50
    ): List<ReposResponse>

}