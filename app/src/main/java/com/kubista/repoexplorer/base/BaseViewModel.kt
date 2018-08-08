package com.kubista.repoexplorer.base

/**
 * Created by alek on 08/08/2018.
 */

import android.arch.lifecycle.ViewModel
import com.kubista.repoexplorer.injection.component.ViewModelInjector
import com.kubista.repoexplorer.injection.module.NetworkModule
import com.kubista.repoexplorer.ui.repository.RepoListViewModel

abstract class BaseViewModel: ViewModel(){
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