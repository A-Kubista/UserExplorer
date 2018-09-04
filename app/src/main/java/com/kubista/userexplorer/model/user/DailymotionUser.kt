package com.kubista.userexplorer.model.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class DailymotionApiUserList(
        val list: List<DailymotionUser>
)

/**
 * Class which provides a model for mapping Dailymotion User from response
 * @constructor Sets all properties of the user
 * @property username the name of the user
 * @property avatar_360_url the url to the avatar of the user
 */

@Entity
@Parcelize
data class DailymotionUser(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val username: String,
        val avatar_360_url: String
) : User(), Parcelable {
    override fun getParcelable(): Parcelable {
        return this
    }

    override fun getName(): String {
       return username
    }

    override fun getAvatarUrl(): String {
        return avatar_360_url
    }

    override fun getType(): UserType {
        return UserType.DAILYMOTION
    }

}
