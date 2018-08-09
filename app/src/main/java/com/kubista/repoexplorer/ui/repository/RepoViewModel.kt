package com.kubista.repoexplorer.ui.repository

import android.arch.lifecycle.MutableLiveData
import com.kubista.repoexplorer.base.BaseViewModel
import com.kubista.repoexplorer.model.GitHubRepo

/**
 * Created by alek on 09/08/2018.
 */

class RepoViewModel: BaseViewModel() {
    private val repoName = MutableLiveData<String>()
    private val repoDesc = MutableLiveData<String>()

    fun bind(repo: GitHubRepo){
        repoName.value = repo.name
        repoDesc.value = repo.description
    }

    fun getRepoName():MutableLiveData<String>{
        return repoName
    }

    fun getRepoDesc():MutableLiveData<String>{
        return repoDesc
    }
}