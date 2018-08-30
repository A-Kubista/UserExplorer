package com.kubista.userexplorer.network

import com.kubista.userexplorer.model.Values
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface BitBucketRepoApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("repositories?fields=values.name,values.owner,values.description")
    fun getRepos(): Observable<Values>
}