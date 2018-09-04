package com.kubista.userexplorer.model.user

import com.kubista.userexplorer.network.DailymotionUserApi
import com.kubista.userexplorer.network.GitHubUserApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import javax.inject.Inject


class UserRepository @Inject constructor(userDao: UserDao, gitHubUserApi: GitHubUserApi, dailymotionUserApi: DailymotionUserApi) {

    private val userDao = userDao
    private val gitHubUserApi = gitHubUserApi
    private val dailymotionUserApi = dailymotionUserApi


    @Throws(ExecutionException::class, InterruptedException::class)
    fun getUsers(): List<DailymotionUser> {

        val callable = Callable<List<DailymotionUser>> { userDao.getAllFromDailymotion }

        val future = Executors.newSingleThreadExecutor().submit(callable)

        return future.get()
    }

    fun getDailymotionUsers(fresh: Boolean): Observable<List<DailymotionUser>> {
        if (fresh) {
            return dailymotionUserApi.getUsers().concatMap { apiUserList ->
                userDao.clearDailymotionCache()
                userDao.insertAll(*apiUserList.list.toTypedArray())
                Observable.just(apiUserList.list)
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            return Observable.fromCallable { userDao.getAllFromDailymotion }
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

    fun getGitHubUsers(fresh: Boolean): Observable<List<GitHubUser>> {
        if (fresh) {
            return gitHubUserApi.getUsers().concatMap { apiUserList ->
                userDao.clearGithubCache()
                userDao.insertAll(*apiUserList.toTypedArray())
                Observable.just(apiUserList)
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            return Observable.fromCallable { userDao.getAllFromGithub }
                    .concatMap { dbUserList ->
                        if (dbUserList.isEmpty())
                            gitHubUserApi.getUsers().concatMap { apiUserList ->
                                userDao.insertAll(*apiUserList.toTypedArray())
                                Observable.just(apiUserList)
                            }
                        else
                            Observable.just(dbUserList)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}