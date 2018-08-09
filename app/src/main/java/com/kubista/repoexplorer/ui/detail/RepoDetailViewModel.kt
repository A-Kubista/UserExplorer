package com.kubista.repoexplorer.ui.detail

import android.arch.lifecycle.MutableLiveData
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.GitHubRepo
import com.kubista.repoexplorer.model.IRepo

class RepoDetailViewModel : BaseViewModel() {
    private lateinit var repoTitle : String
    private lateinit var repoBody : String
    private lateinit var repoOwnerLogin : String
    private lateinit var repoOwnerAvatar : String

    fun bind(repo: IRepo) {
        repoTitle = repo.getRepositoryTitle()
        repoBody = repo.getRepositoryDescription()
        repoOwnerLogin = repo.getOwnerName()
        repoOwnerAvatar = repo.getOwnerAvatarUrl()
    }

    fun getRepoTitle(): String {
        return repoTitle
    }

    fun getRepoBody(): String {
        return repoBody
    }

    fun getRepoOwnerLogin(): String {
        return repoOwnerLogin
    }

    fun getRepoOwnerAvatar(): String {
        return if( !repoOwnerAvatar.isNullOrBlank() ) repoOwnerAvatar!!  else " "
    }
}