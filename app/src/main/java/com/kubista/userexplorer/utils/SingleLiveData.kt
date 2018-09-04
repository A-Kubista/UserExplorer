package com.kubista.userexplorer.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread

class SingleLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T?>) {
        super.observe(owner, Observer { data ->
            if (data != null) {
                observer.onChanged(data)
                value = null
            }
        })
    }

    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}