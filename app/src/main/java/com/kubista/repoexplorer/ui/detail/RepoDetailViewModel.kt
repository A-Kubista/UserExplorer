package com.kubista.repoexplorer.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.IRepo

class RepoDetailViewModel : BaseViewModel() {
    private lateinit var repoTitle : String
    private lateinit var repoDesc : String
    private lateinit var repoOwnerLogin : String
    private lateinit var repoOwnerAvatar : String
    private var isBitbucektRepo : Boolean = false

    fun bind(repo: IRepo) {
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
        return if( isBitbucektRepo ) View.VISIBLE else View.INVISIBLE
    }
}