package com.kubista.userexplorer.model

interface IRepo {
    fun getOwnerName(): String
    fun getOwnerAvatarUrl(): String
    fun getRepositoryTitle(): String
    fun getRepositoryDescription(): String
    fun isBitbucektRepo(): Boolean
}
