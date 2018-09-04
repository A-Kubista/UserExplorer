package com.kubista.userexplorer.network

import com.kubista.userexplorer.model.user.GitHubUser
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface GitHubUserApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/users")
    fun getUsers(): Observable<List<GitHubUser>>
}