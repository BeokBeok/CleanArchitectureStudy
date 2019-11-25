package com.beok.reposearch.di

import androidx.room.Room
import com.beok.reposearch.data.RepoSearchDataSource
import com.beok.reposearch.data.RepoSearchRepository
import com.beok.reposearch.data.source.local.RepoSearchDatabase
import com.beok.reposearch.data.source.local.RepoSearchLocalDataSource
import com.beok.reposearch.data.source.remote.RepoSearchRemoteDataSource
import com.beok.reposearch.data.source.remote.RepoSearchService
import com.beok.reposearch.domain.usecase.UserRepoSearchUsecase
import com.beok.reposearch.presenter.RepoSearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object RepoSearchDI {

    private val databaseModule = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                RepoSearchDatabase::class.java,
                "repos.db"
            ).build()
        }
        single { get<RepoSearchDatabase>().repoSearchDao() }
    }

    private val retrofitModule = module {
        factory { get<Retrofit>().create(RepoSearchService::class.java) }
    }

    private val dataSourceModule = module {
        factory<RepoSearchDataSource> {
            RepoSearchRepository(get(), get())
        }
        factory<RepoSearchDataSource.Remote> {
            RepoSearchRemoteDataSource(get())
        }
        factory<RepoSearchDataSource.Local> {
            RepoSearchLocalDataSource(get())
        }
    }

    private val usecaseModule = module {
        factory { UserRepoSearchUsecase(get()) }
    }

    private val viewModelModule = module {
        viewModel { RepoSearchViewModel(get()) }
    }

    val repoSearchModule = listOf(
        viewModelModule,
        usecaseModule,
        dataSourceModule,
        retrofitModule,
        databaseModule
    )
}