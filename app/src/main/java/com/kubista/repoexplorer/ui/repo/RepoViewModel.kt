package com.kubista.repoexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.IRepo

class RepoViewModel : BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoBody = MutableLiveData<String>()
    private val repoOwnerLogin = MutableLiveData<String>()
    private val repoOwnerAvatar = MutableLiveData<String>()

    fun bind(repo: IRepo) {
        repoTitle.value = repo.getRepositoryTitle()
        repoBody.value = repo.getRepositoryDescription()
        repoOwnerLogin.value = repo.getOwnerName()
        repoOwnerAvatar.value = repo.getOwnerAvatarUrl()
    }

    fun getRepoTitle(): MutableLiveData<String> {
        return repoTitle
    }

    fun getRepoBody(): MutableLiveData<String> {
        return repoBody
    }

    fun getRepoOwnerLogin(): MutableLiveData<String> {
        return repoOwnerLogin
    }

    fun getRepoOwnerAvatar(): MutableLiveData<String> {
        return repoOwnerAvatar
    }
}