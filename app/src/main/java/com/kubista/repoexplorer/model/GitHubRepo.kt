package com.kubista.repoexplorer.model

import android.arch.persistence.room.Entity
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
)

data class GitHubUser(
        val login: String? = null,
        val avatar_url: String? = null
)