package com.kubista.userexplorer.ui.user

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.view.View
import com.kubista.userexplorer.base.BaseViewModel
import com.kubista.userexplorer.R
import com.kubista.userexplorer.model.User
import com.kubista.userexplorer.network.DailymotionUserApi
import com.kubista.userexplorer.network.GitHubUserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UserListViewModel : BaseViewModel() {
    @Inject
    lateinit var gitHubUserApi: GitHubUserApi
    @Inject
    lateinit var dailymotionUserApi: DailymotionUserApi

    private lateinit var cachedDailymotionUsers: List<User>

    val userListAdapter: UserListAdapter = UserListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isLoading = ObservableBoolean()
    var sortEnabled = ObservableBoolean()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadGitHubUsers()
        loadUsersDailymotion()
    }

    private lateinit var subscriptionGitHubApi: Disposable
    private lateinit var subscriptionDailymotionApi: Disposable

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptionGitHubApi.dispose()
        subscriptionDailymotionApi.dispose()
    }

    fun loadUsers() {
        loadUsersDailymotion()
    }

    private fun loadGitHubUsers() {
        subscriptionGitHubApi = gitHubUserApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveUserListStart() }
                .doOnTerminate { onRetrieveUserListFinish() }
                .subscribe(
                        { result -> onRetrieveGitHubUserListSuccess(result) },
                        { onRetrieveUserListError() }
                )
    }

    private fun loadUsersDailymotion() {
        subscriptionDailymotionApi = dailymotionUserApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveUserListStart() }
                .doOnTerminate { onRetrieveUserListFinish() }
                .subscribe(
                        { result -> onRetrieveDailymotionUserListSuccess(result.list) },
                        { onRetrieveUserListError() }
                )
    }

    private fun onRetrieveUserListStart() {
        isLoading.set(true)
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveUserListFinish() {
        loadingVisibility.value = View.GONE
        isLoading.set(false)
    }

    private fun onRetrieveGitHubUserListSuccess(userList: List<User>) {
        val resultList = if (::cachedDailymotionUsers.isInitialized) userList + cachedDailymotionUsers else userList
        userListAdapter.updateUserList(resultList)
    }

    private fun onRetrieveDailymotionUserListSuccess(userList: List<User>) {
        cachedDailymotionUsers = userList
        loadGitHubUsers()
    }

    private fun onRetrieveUserListError() {
        errorMessage.value = R.string.fetch_error
    }

    fun toggleSort(value: Boolean) {
        sortEnabled.set(!sortEnabled.get())
        userListAdapter.toggleSort(sortEnabled.get())
    }
}

