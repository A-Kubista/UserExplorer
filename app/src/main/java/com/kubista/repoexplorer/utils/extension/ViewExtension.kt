package com.kubista.repoexplorer.utils.extension

import android.content.ContextWrapper
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by alek on 09/08/2018.
 */

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}