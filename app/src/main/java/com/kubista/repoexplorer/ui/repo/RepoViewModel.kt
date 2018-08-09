package com.kubista.repoexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.IRepo

class RepoViewModel : BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoDesc = MutableLiveData<String>()
    private val repoOwnerLogin = MutableLiveData<String>()
    private val repoOwnerAvatar = MutableLiveData<String>()

    fun bind(repo: IRepo) {
        repoTitle.value = repo.getRepositoryTitle()
        repoDesc.value = repo.getRepositoryDescription()
        repoOwnerLogin.value = repo.getOwnerName()
        repoOwnerAvatar.value = repo.getOwnerAvatarUrl()
    }

    fun getRepoTitle(): MutableLiveData<String> {
        return repoTitle
    }

    fun getRepoDesc(): MutableLiveData<String> {
        return repoDesc
    }

    fun getRepoOwnerLogin(): MutableLiveData<String> {
        return repoOwnerLogin
    }

    fun getRepoOwnerAvatar(): String {
        return if( !repoOwnerAvatar.value.isNullOrBlank() ) repoOwnerAvatar.value!!  else ""
    }
}