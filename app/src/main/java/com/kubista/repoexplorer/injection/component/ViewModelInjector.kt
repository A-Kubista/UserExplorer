package com.kubista.repoexplorer.injection.component

import com.kubista.repoexplorer.injection.module.NetworkModule
import com.kubista.repoexplorer.ui.repository.RepoListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alek on 08/08/2018.
 */

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified RepoListViewModel.
     * @param repoListViewModel RepoListViewModel in which to inject the dependencies
     */
    fun inject(repoListViewModel: RepoListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}