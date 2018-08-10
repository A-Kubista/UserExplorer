package com.kubista.repoexplorer.ui.repo

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.BaseViewModel
import com.kubista.repoexplorer.model.BitBucketRepo
import com.kubista.repoexplorer.model.GitHubRepo
import com.kubista.repoexplorer.model.IRepo
import com.kubista.repoexplorer.network.BitBucketRepoApi
import com.kubista.repoexplorer.network.GitHubRepoApi

import javax.inject.Inject
import android.databinding.ObservableBoolean
import io.reactivex.Observable


class RepoListViewModel:BaseViewModel(){
    @Inject
    lateinit var gitHubRepoApi: GitHubRepoApi
    @Inject
    lateinit var bitBucketRepoApi: BitBucketRepoApi

    private lateinit var cachedBitbucketRepos:List<IRepo>

    val repoListAdapter: RepoListAdapter = RepoListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isLoading = ObservableBoolean()
    var sortEnabled = ObservableBoolean()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadGitHubRepos()
        loadReposBitBucket()
    }

    private lateinit var subscriptionGitHubApi: Disposable
    private lateinit var subscriptionBitBucketApi: Disposable

    init{
        loadRepos()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptionGitHubApi.dispose()
        subscriptionBitBucketApi.dispose()
    }

    fun loadRepos(){
        loadReposBitBucket()
    }

    private fun loadGitHubRepos(){
        subscriptionGitHubApi = gitHubRepoApi.getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveRepoListStart() }
                .doOnTerminate { onRetrieveRepoListFinish() }
                .subscribe(
                        { result -> onRetrieveGitHubRepoListSuccess(result) },
                        { onRetrieveRepoListError() }
                )
    }

    private fun loadReposBitBucket(){
        subscriptionBitBucketApi = bitBucketRepoApi.getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveRepoListStart() }
                .doOnTerminate { onRetrieveRepoListFinish() }
                .subscribe(
                        { result -> onRetrieveBitBucketRepoListSuccess(result.values) },
                        { onRetrieveRepoListError() }
                )
    }

    private fun onRetrieveRepoListStart(){
        isLoading.set(true)
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveRepoListFinish(){
        loadingVisibility.value = View.GONE
        isLoading.set(false)
    }

    private fun onRetrieveGitHubRepoListSuccess(repoList:List<IRepo>){
        val resultList = if(::cachedBitbucketRepos.isInitialized)  repoList + cachedBitbucketRepos else repoList
        repoListAdapter.updateRepoList(resultList)
    }

    private fun onRetrieveBitBucketRepoListSuccess(repoList:List<IRepo>){
        cachedBitbucketRepos = repoList
        loadGitHubRepos()
    }

    private fun onRetrieveRepoListError(){
        errorMessage.value = R.string.fetch_error
    }

    fun toggleSort( value : Boolean){
        sortEnabled.set(  !sortEnabled.get() )
        repoListAdapter.toggleSort(sortEnabled.get())
    }
}

