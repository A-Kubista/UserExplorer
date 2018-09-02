package com.kubista.userexplorer.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 *  custom viewModel factory to create instance of generic type
 *  with custom constructor, as BaseViewModel is abstract it
 *  is impossible to create and return its instance, nor instance
 *  of generic type without reflection
 */
@Suppress("UNCHECKED_CAST")
inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
        }