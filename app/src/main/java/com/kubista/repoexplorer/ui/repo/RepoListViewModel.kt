package com.kubista.repoexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.GitHubRepo
import com.kubista.repoexplorer.network.GitHubRepoApi

import javax.inject.Inject

class RepoListViewModel:BaseViewModel(){
    @Inject
    lateinit var gitHubRepoApi: GitHubRepoApi
    private lateinit var repoList:List<GitHubRepo>
    val repoListAdapter: RepoListAdapter = RepoListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRepos() }

    private lateinit var subscription: Disposable

    init{
        loadRepos()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadRepos(){
        subscription = gitHubRepoApi.getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveRepoListStart() }
                .doOnTerminate { onRetrieveRepoListFinish() }
                .subscribe(
                        { result -> onRetrieveRepoListSuccess(result) },
                        { onRetrieveRepoListError() }
                )
    }

    private fun onRetrieveRepoListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveRepoListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveRepoListSuccess(postList:List<GitHubRepo>){
        repoListAdapter.updateRepoList(postList)
    }

    private fun onRetrieveRepoListError(){
        errorMessage.value = R.string.fetch_error
    }
}