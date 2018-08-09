package com.kubista.repoexplorer.injection.component

import com.kubista.repoexplorer.injection.module.NetworkModule
import com.kubista.repoexplorer.ui.repo.RepoListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified RepoListViewModel.
     * @param postListViewModel RepoListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: RepoListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}