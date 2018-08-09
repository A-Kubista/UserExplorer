package com.kubista.repoexplorer.network

import com.kubista.repoexplorer.model.BitBucketRepo
import com.kubista.repoexplorer.model.GitHubRepo
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface BitBucketRepoApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/repositories?fields=values.name,values.owner,values.description")
    fun getRepos(): Observable<List<BitBucketRepo>>
}