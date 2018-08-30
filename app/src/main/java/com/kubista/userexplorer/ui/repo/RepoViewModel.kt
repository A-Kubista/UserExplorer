package com.kubista.userexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.kubista.userexplorer.BaseViewModel
import com.kubista.userexplorer.model.IRepo

class RepoViewModel : BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoDesc = MutableLiveData<String>()
    private val repoOwnerLogin = MutableLiveData<String>()
    private val repoOwnerAvatar = MutableLiveData<String>()
    private val isBitbucektRepo = MutableLiveData<Boolean>()

    fun bind(repo: IRepo) {
        repoTitle.value = repo.getRepositoryTitle()
        repoDesc.value = repo.getRepositoryDescription()
        repoOwnerLogin.value = repo.getOwnerName()
        repoOwnerAvatar.value = repo.getOwnerAvatarUrl()
        isBitbucektRepo.value = repo.isBitbucektRepo()
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
        return if (!repoOwnerAvatar.value.isNullOrBlank()) repoOwnerAvatar.value!! else ""
    }

    fun getBitBucketLogoVisibility(): Int {
        return if (isBitbucektRepo.value!!) View.VISIBLE else View.INVISIBLE
    }
}