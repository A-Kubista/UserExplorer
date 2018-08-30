package com.kubista.userexplorer.network

import com.kubista.userexplorer.model.GitHubRepo
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface GitHubRepoApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/repositories")
    fun getRepos(): Observable<List<GitHubRepo>>
}