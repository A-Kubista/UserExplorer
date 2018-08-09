package com.kubista.repoexplorer.model

interface IRepo {
    fun getOwnerName(): String
    fun getOwnerAvatarUrl(): String
    fun getRepositoryTitle(): String
    fun getRepositoryDescription(): String
}
