package com.kubista.userexplorer.base

import android.arch.lifecycle.ViewModel
import com.kubista.userexplorer.injection.component.DaggerViewModelInjector
import com.kubista.userexplorer.injection.component.ViewModelInjector
import com.kubista.userexplorer.injection.module.NetworkModule

abstract class BaseViewModel : ViewModel() {
    protected val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        this.inject()
    }

    abstract fun inject()
}
