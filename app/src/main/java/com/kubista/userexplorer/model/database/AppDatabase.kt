package com.kubista.userexplorer.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kubista.userexplorer.model.user.DailymotionUser
import com.kubista.userexplorer.model.user.GitHubUser
import com.kubista.userexplorer.model.user.UserDao

@Database(entities = arrayOf(DailymotionUser::class, GitHubUser::class ), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}