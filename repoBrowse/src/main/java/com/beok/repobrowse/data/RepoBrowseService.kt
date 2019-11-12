package com.beok.repobrowse.data

import com.beok.repobrowse.data.response.BranchResponse
import com.beok.repobrowse.data.response.RepoFileTreeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoBrowseService {

    @GET("repos/{userName}/{repoName}/contents/{detail}")
    suspend fun getRepoFileTree(
        @Path("userName")
        userName: String,
        @Path("repoName")
        repoName: String,
        @Path("detail")
        detail: String,
        @Query("ref")
        branch: String
    ): List<RepoFileTreeResponse>

    @GET("repos/{userName}/{repoName}/branches")
    suspend fun getRepoBranches(
        @Path("userName")
        userName: String,
        @Path("repoName")
        repoName: String
    ): List<BranchResponse>
}