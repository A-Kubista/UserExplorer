package com.kubista.userexplorer.ui.detail

import android.view.View
import com.kubista.userexplorer.BaseViewModel
import com.kubista.userexplorer.model.User

class RepoDetailViewModel : BaseViewModel() {
    private lateinit var repoTitle: String
    private lateinit var repoDesc: String
    private lateinit var repoOwnerLogin: String
    private lateinit var repoOwnerAvatar: String
    private var isBitbucektRepo: Boolean = false

    fun bind(repo: User) {
        repoTitle = repo.getRepositoryTitle()
        repoDesc = repo.getRepositoryDescription()
        repoOwnerLogin = repo.getOwnerName()
        repoOwnerAvatar = repo.getOwnerAvatarUrl()
        isBitbucektRepo = repo.isBitbucektRepo()
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
        return repoOwnerAvatar
    }

    fun getBitBucketLogoVisibility(): Int {
        return if (isBitbucektRepo) View.VISIBLE else View.INVISIBLE
    }
}