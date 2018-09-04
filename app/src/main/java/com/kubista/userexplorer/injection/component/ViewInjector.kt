package com.kubista.userexplorer.injection.component

import android.app.Application
import com.kubista.userexplorer.injection.module.AppModule
import com.kubista.userexplorer.injection.module.DatabaseModule
import com.kubista.userexplorer.model.user.UserDao
import com.kubista.userexplorer.ui.detail.UserDetailActivity
import com.kubista.userexplorer.ui.user.UserListActivity
import com.kubista.userexplorer.ui.user.UserListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(AppModule::class),(DatabaseModule::class)])
interface ViewInjector {
    /**
     * Injects required dependencies into the specified UserListViewModel.
     * @param postListViewModel UserListViewModel in which to inject the dependencies
     */
    fun inject(mApplication: Application)
    fun inject(userDao: UserDao)
    fun inject(userListFragment: UserListFragment)
    fun inject(userListActivity: UserListActivity)
    fun inject(userDetailActivity: UserDetailActivity)

    @Component.Builder
    interface Builder {
        fun build(): ViewInjector
        fun appModule(appModule: AppModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
    }
}