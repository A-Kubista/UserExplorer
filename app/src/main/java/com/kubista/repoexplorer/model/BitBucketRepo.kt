package com.kubista.repoexplorer.model

import android.view.Display


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
        val description: String,
        val owner: Owner
) : IRepo {
    override fun isBitbucektRepo(): Boolean {
        return true
    }

    override fun getOwnerName(): String {
        return owner.display_name
    }

    override fun getOwnerAvatarUrl(): String {
        return owner.links.avatar.href
    }

    override fun getRepositoryTitle(): String {
        return name
    }

    override fun getRepositoryDescription(): String {
        return description
    }
}


data class Owner(
        val links: Links,
        val display_name: String
)

data class Links(
        val avatar: Avatar
)

data class Avatar(
        val href: String
)