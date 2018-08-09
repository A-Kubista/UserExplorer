package com.kubista.repoexplorer.model

import android.arch.persistence.room.PrimaryKey

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property id the unique identifier of the post
 * @property name the title of the post
 * @property description the content of the post
 */

data class GitHubRepo(
        @field:PrimaryKey
        val id: Int,
        val name: String,
        val description: String,
        val owner: GitHubUser
) : IRepo {
    override fun isBitbucektRepo(): Boolean {
        return false
    }

    override fun getOwnerName(): String {
        return owner.login
    }

    override fun getOwnerAvatarUrl(): String {
        return owner.avatar_url
    }

    override fun getRepositoryTitle(): String {
        return name
    }

    override fun getRepositoryDescription(): String {
        return description
    }
}

data class GitHubUser(
        val login: String,
        val avatar_url: String
)