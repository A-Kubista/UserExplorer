package com.kubista.repoexplorer.ui.repository

import com.kubista.repoexplorer.base.BaseViewModel
import com.kubista.repoexplorer.network.GitHubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by alek on 08/08/2018.
 */
class RepoListViewModel : BaseViewModel() {
    @Inject
    lateinit var gitHubApi: GitHubApi

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = gitHubApi.getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { onRetrievePostListSuccess() },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){

    }

    private fun onRetrievePostListFinish(){

    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }
}