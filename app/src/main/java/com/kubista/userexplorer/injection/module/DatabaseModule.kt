package com.kubista.userexplorer.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import com.kubista.userexplorer.model.database.AppDatabase
import com.kubista.userexplorer.model.user.UserDao
import com.kubista.userexplorer.model.user.UserRepository
import com.kubista.userexplorer.network.DailymotionUserApi
import com.kubista.userexplorer.network.GitHubUserApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Module which provides all required dependencies about network
 */

@Module(includes  = [(NetworkModule::class)])
class DatabaseModule(mApplication: Application) {

    private val appDatabase: AppDatabase = Room.databaseBuilder(
            mApplication,
            AppDatabase::class.java,
            "users").build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    internal fun providesUserDao(demoDatabase: AppDatabase): UserDao {
        return demoDatabase.userDao()
    }

    @Singleton
    @Provides
    fun providesUserRepository(userDao: UserDao, gitHubUserApi: GitHubUserApi, dailymotionUserApi: DailymotionUserApi): UserRepository {
        return UserRepository(userDao, gitHubUserApi, dailymotionUserApi)
    }
}