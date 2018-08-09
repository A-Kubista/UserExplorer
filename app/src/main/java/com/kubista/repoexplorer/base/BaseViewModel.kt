package com.kubista.repoexplorer

import android.arch.lifecycle.ViewModel
import com.kubista.repoexplorer.injection.component.DaggerViewModelInjector
import com.kubista.repoexplorer.injection.component.ViewModelInjector
import com.kubista.repoexplorer.injection.module.NetworkModule
import com.kubista.repoexplorer.ui.repo.RepoListViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is RepoListViewModel -> injector.inject(this)
        }
    }
}