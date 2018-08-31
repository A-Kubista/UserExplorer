package com.kubista.userexplorer.model

/**
 * Class which provides a model for mapping GitHub User from response
 * @constructor Sets all properties of the user
 * @property login the name of the  user
 * @property avatar_url the url of avatar
 */

data class GitHubUser(
        val login: String,
        val avatar_url: String
) : User() {
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