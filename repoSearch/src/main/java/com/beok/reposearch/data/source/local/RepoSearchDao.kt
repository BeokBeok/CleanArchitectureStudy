package com.beok.reposearch.data.source.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beok.reposearch.domain.entity.ReposEntity

@Dao
interface RepoSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repos: List<ReposEntity>)

    @Query("SELECT * FROM repos WHERE user = :user ORDER BY name ASC")
    fun getRepos(user: String): DataSource.Factory<Int, ReposEntity>
}