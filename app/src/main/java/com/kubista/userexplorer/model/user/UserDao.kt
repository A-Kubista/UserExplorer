package com.kubista.userexplorer.model.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @get:Query("SELECT * FROM dailymotionuser")
    val all: List<DailymotionUser>

    @Insert
    fun insertAll(vararg users: DailymotionUser)

    @Query("DELETE FROM dailymotionuser")
    fun clearData()
}