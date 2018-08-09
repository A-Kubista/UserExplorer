package com.kubista.repoexplorer.ui.detail

import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.IRepo

class RepoDetailViewModel : BaseViewModel() {
    private lateinit var repoTitle : String
    private lateinit var repoDesc : String
    private lateinit var repoOwnerLogin : String
    private lateinit var repoOwnerAvatar : String

    fun bind(repo: IRepo) {
        repoTitle = repo.getRepositoryTitle()
        repoDesc = repo.getRepositoryDescription()
        repoOwnerLogin = repo.getOwnerName()
        repoOwnerAvatar = repo.getOwnerAvatarUrl()
    }

    fun getRepoTitle(): String {
        return repoTitle
    }

    fun getRepoDesc(): String {
        return repoDesc
    }

    fun getRepoOwnerLogin(): String {
        return repoOwnerLogin
    }

    fun getRepoOwnerAvatar(): String {
        return if( !repoOwnerAvatar.isNullOrBlank() ) repoOwnerAvatar!!  else " "
    }
}