package com.kubista.repoexplorer.injection.module

import com.kubista.repoexplorer.network.BitBucketRepoApi
import com.kubista.repoexplorer.network.GitHubRepoApi
import com.kubista.repoexplorer.utils.BASE_URL_BITBUCKET
import com.kubista.repoexplorer.utils.BASE_URL_GITHUB
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Repo service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Repo service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideBitbucketRepoApi(@Named("BitBucket") retrofit: Retrofit): BitBucketRepoApi {
        return retrofit.create(BitBucketRepoApi::class.java)
    }

    /**
     * Provides the Repo service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Repo service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitHubRepoApi(@Named("GitHub") retrofit: Retrofit): GitHubRepoApi {
        return retrofit.create(GitHubRepoApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("GitHub")
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_GITHUB)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    @Named("BitBucket")
    internal fun provideRetrofitInterfaceBitBucket(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_BITBUCKET)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}