package com.kubista.repoexplorer.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


data class Values(
        val values: List<BitBucketRepo>
        )
/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property name the title of the post
 * @property description the content of the post
 */

data class BitBucketRepo(
        val name: String,
        val description: String
) : IRepo {
    override fun getOwnerName(): String {
        return name
    }

    override fun getOwnerAvatarUrl(): String {
        return name
    }

    override fun getRepositoryTitle(): String {
        return name
    }

    override fun getRepositoryDescription(): String {
        return description
    }
}
