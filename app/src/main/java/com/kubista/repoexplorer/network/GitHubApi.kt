package com.kubista.repoexplorer.network

import com.kubista.repoexplorer.model.GitHubRepo
import retrofit2.http.GET
import io.reactivex.Observable

/**
 * Created by alek on 08/08/2018.
 */

/**
 * The interface which provides methods to get result of webservices
 */
interface GitHubApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/repositories")
    fun getRepositories(): Observable<List<GitHubRepo>>
}