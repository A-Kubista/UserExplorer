package com.kubista.userexplorer.model.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @get:Query("SELECT * FROM dailymotionuser")
    val getAllFromDailymotion: List<DailymotionUser>

    @get:Query("SELECT * FROM githubuser")
    val getAllFromGithub: List<GitHubUser>

    @Query("SELECT * FROM dailymotionuser WHERE id = :id ")
    fun getDailmotionById(id: Int): DailymotionUser

    @Query("SELECT * FROM githubuser WHERE id = :id ")
    fun getGitHubById(id: Int): GitHubUser

    @Insert
    fun insertAll(vararg users: DailymotionUser)

    @Insert
    fun insertAll(vararg users: GitHubUser)

    @Query("DELETE FROM githubuser")
    fun clearGithubCache()

    @Query("DELETE FROM dailymotionuser")
    fun clearDailymotionCache()
}