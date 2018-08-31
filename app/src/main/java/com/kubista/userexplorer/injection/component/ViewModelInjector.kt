package com.kubista.userexplorer.injection.component

import com.kubista.userexplorer.injection.module.NetworkModule
import com.kubista.userexplorer.ui.user.UserListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified UserListViewModel.
     * @param postListViewModel UserListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: UserListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}