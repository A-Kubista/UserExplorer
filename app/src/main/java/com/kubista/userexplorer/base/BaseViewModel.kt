package com.kubista.userexplorer.base

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.kubista.userexplorer.injection.component.DaggerViewModelInjector
import com.kubista.userexplorer.injection.component.ViewModelInjector
import com.kubista.userexplorer.injection.module.NetworkModule
import com.kubista.userexplorer.ui.user.UserListViewModel

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
            is UserListViewModel -> injector.inject(this)
        }
    }
}