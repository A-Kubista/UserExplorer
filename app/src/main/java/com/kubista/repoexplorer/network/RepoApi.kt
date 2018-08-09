package com.kubista.repoexplorer.network

import io.reactivex.Observable
import com.kubista.repoexplorer.model.Repo
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface RepoApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/repositories")
    fun getRepos(): Observable<List<Repo>>
}