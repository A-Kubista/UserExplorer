package com.kubista.userexplorer

import android.arch.lifecycle.ViewModel
import com.kubista.userexplorer.injection.component.DaggerViewModelInjector
import com.kubista.userexplorer.injection.component.ViewModelInjector
import com.kubista.userexplorer.injection.module.NetworkModule
import com.kubista.userexplorer.ui.repo.RepoListViewModel

abstract class BaseViewModel : ViewModel() {
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