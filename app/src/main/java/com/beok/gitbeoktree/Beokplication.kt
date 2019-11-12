package com.beok.gitbeoktree

import android.app.Application
import com.beok.common.di.getRetrofitBasicModule
import com.beok.fileviewer.di.FileViewerDI
import com.beok.repobrowse.di.RepoBrowseDI
import com.beok.reposearch.di.RepoSearchDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

@Suppress("unused")
class Beokplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Beokplication)
            modules(
                getKoinModules(
                    listOf(
                        RepoSearchDI.repoSearchModule,
                        RepoBrowseDI.repoBrowseModule,
                        FileViewerDI.fileViewModules
                    )
                )
            )
        }
    }

    private fun getKoinModules(modules: List<List<Module>>): List<Module> {
        val koinModules = mutableListOf<Module>()
        koinModules.addAll(modules.flatten())
        koinModules.add(getRetrofitBasicModule("https://api.github.com/"))
        return koinModules
    }
}