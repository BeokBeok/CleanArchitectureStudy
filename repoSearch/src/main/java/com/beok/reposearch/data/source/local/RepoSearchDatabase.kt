package com.beok.reposearch.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beok.reposearch.domain.entity.ReposEntity

@Database(
    entities = [ReposEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RepoSearchDatabase : RoomDatabase() {

    abstract fun repoSearchDao(): RepoSearchDao
}