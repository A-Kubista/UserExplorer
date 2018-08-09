package com.kubista.repoexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.GitHubRepo

class RepoViewModel:BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoBody = MutableLiveData<String>()
    private val repoOwnerLogin = MutableLiveData<String>()
    private val repoOwnerAvatar = MutableLiveData<String>()

    fun bind(repo: GitHubRepo){
        repoTitle.value = repo.name
        repoBody.value = repo.description
        repoOwnerLogin.value = repo.owner.login
        repoOwnerAvatar.value = repo.owner.avatar_url
    }

    fun getRepoTitle():MutableLiveData<String>{
        return repoTitle
    }

    fun getRepoBody():MutableLiveData<String>{
        return repoBody
    }

    fun getRepoOwnerLogin():MutableLiveData<String>{
        return repoOwnerLogin
    }

    fun getRepoOwnerAvatar():MutableLiveData<String>{
        return repoOwnerAvatar
    }
}