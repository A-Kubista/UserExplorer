package com.kubista.repoexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.Repo

class RepoViewModel:BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoBody = MutableLiveData<String>()

    fun bind(repo: Repo){
        repoTitle.value = repo.name
        repoBody.value = repo.description
    }

    fun getRepoTitle():MutableLiveData<String>{
        return repoTitle
    }

    fun getRepoBody():MutableLiveData<String>{
        return repoBody
    }
}