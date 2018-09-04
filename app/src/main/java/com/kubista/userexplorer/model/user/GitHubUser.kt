package com.kubista.userexplorer.model.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class which provides a model for mapping GitHub User from response
 * @constructor Sets getAllFromDailymotion properties of the user
 * @property login the name of the  user
 * @property avatar_url the url of avatar
 */

@Entity
@Parcelize
data class GitHubUser(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val login: String,
        val avatar_url: String
) : User(), Parcelable {

    override fun getParcelable(): Parcelable {
        return this
    }

    override fun getName(): String {
        return login
    }

    override fun getAvatarUrl(): String {
        return avatar_url
    }

    override fun getType(): UserType {
        return UserType.GITHUB
    }
}