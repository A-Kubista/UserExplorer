package com.kubista.userexplorer.model.user

import com.kubista.userexplorer.network.DailymotionUserApi
import com.kubista.userexplorer.network.GitHubUserApi
import javax.inject.Inject
import android.arch.lifecycle.LiveData
import android.service.autofill.UserData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors


class UserRepository @Inject constructor(userDao: UserDao, gitHubUserApi: GitHubUserApi, dailymotionUserApi: DailymotionUserApi) {

    private val userDao = userDao
    private val gitHubUserApi = gitHubUserApi
    private val dailymotionUserApi = dailymotionUserApi


    @Throws(ExecutionException::class, InterruptedException::class)
    fun getUsers(): List<DailymotionUser> {

        val callable = Callable<List<DailymotionUser>> { userDao.all }

        val future = Executors.newSingleThreadExecutor().submit(callable)

        return future.get()
    }

    fun getDailymotionUsers(): Observable<List<DailymotionUser>> {
        return Observable.fromCallable { userDao.all }
                .concatMap { dbUserList ->
                    if (dbUserList.isEmpty())
                        dailymotionUserApi.getUsers().concatMap { apiUserList ->
                            userDao.insertAll(*apiUserList.list.toTypedArray())
                            Observable.just(apiUserList.list)
                        }
                    else
                        Observable.just(dbUserList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}