package com.kubista.userexplorer.ui.user

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.view.View
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.BaseViewModel
import com.kubista.userexplorer.model.user.DailymotionUser
import com.kubista.userexplorer.model.user.User
import com.kubista.userexplorer.model.user.UserDao
import com.kubista.userexplorer.model.user.UserRepository
import com.kubista.userexplorer.network.DailymotionUserApi
import com.kubista.userexplorer.network.GitHubUserApi
import com.kubista.userexplorer.utils.KEY_ERROR_MESSAGE_PARCEL
import com.kubista.userexplorer.utils.NO_INT_VAL
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class UserListViewModel(bundle: Bundle?, userRepository: UserRepository, userDao: UserDao) : BaseViewModel() {
    override fun inject() {
        injector.inject(this)
    }

    @Inject
    lateinit var gitHubUserApi: GitHubUserApi
    @Inject
    lateinit var dailymotionUserApi: DailymotionUserApi

    private lateinit var cachedDailymotionUsers: List<User>

    val userListAdapter: UserListAdapter = UserListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isLoading = ObservableBoolean()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadGitHubUsers()
        loadUsersDailymotion()
    }

    private lateinit var subscriptionGitHubApi: Disposable
    private lateinit var subscriptionDailymotionApi: Disposable
    private val userDao: UserDao = userDao
    private val userRepository: UserRepository = userRepository

    init {
        readSavedInstance(bundle)
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscriptionGitHubApi.isInitialized)
            subscriptionGitHubApi.dispose()
        if (::subscriptionDailymotionApi.isInitialized)
            subscriptionDailymotionApi.dispose()
    }

    fun loadUsers() {
        loadUsersDailymotion()
    }

    private fun loadGitHubUsers() {
        subscriptionGitHubApi = userRepository.getGitHubUsers()
                .doOnSubscribe { onRetrieveUserListStart() }
                .doOnTerminate { onRetrieveUserListFinish() }
                .subscribe(
                        { result -> onRetrieveGitHubUserListSuccess(result) },
                        { onRetrieveUserListError() }
                )
    }

    private fun loadUsersDailymotion() {
        subscriptionDailymotionApi = userRepository.getDailymotionUsers()
                .doOnSubscribe { onRetrieveUserListStart() }
                .doOnTerminate { onRetrieveUserListFinish() }
                .subscribe(
                        { result -> onRetrieveDailymotionUserListSuccess(result) },
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
        val resultList =
                if (::cachedDailymotionUsers.isInitialized)
                    userList + cachedDailymotionUsers
                else userList
        userListAdapter.updateUserList(resultList)
    }

    private fun onRetrieveDailymotionUserListSuccess(userList: List<DailymotionUser>) {
        cachedDailymotionUsers = userList
        loadGitHubUsers()
    }

    private fun onRetrieveUserListError() {
        errorMessage.value = R.string.fetch_error
    }

    fun saveInstance(bundle: Bundle) {
        if (errorMessage.value != null) bundle.putInt(KEY_ERROR_MESSAGE_PARCEL, errorMessage.value!!)
    }

    private fun readSavedInstance(bundle: Bundle?) {
        if (bundle != null) {
            val savedMessage = bundle.getInt(KEY_ERROR_MESSAGE_PARCEL, NO_INT_VAL)
            if (savedMessage != NO_INT_VAL)
                errorMessage.value = savedMessage
        }
    }
}

