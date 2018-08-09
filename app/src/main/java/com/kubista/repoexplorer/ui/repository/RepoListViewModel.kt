package com.kubista.repoexplorer.ui.repository

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.base.BaseViewModel
import com.kubista.repoexplorer.model.GitHubRepo
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
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val repoListAdapter: RepositoryListAdapter = RepositoryListAdapter()

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
                        { result -> onRetrievePostListSuccess(result)  },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(repoList:List<GitHubRepo>){
        repoListAdapter.updatePostList(repoList)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.fetch_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}