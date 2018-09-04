package com.kubista.userexplorer.network

import com.kubista.userexplorer.model.user.DailymotionApiUserList
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface DailymotionUserApi {
    /**
     * Get the list of users from the API
     */
    @GET("users?fields=avatar_360_url,username")
    fun getUsers(): Observable<DailymotionApiUserList>
}