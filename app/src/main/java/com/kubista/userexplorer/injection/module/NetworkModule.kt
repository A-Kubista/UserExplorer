package com.kubista.userexplorer.injection.module

import com.kubista.userexplorer.network.DailymotionUserApi
import com.kubista.userexplorer.network.GitHubUserApi
import com.kubista.userexplorer.utils.BASE_URL_DAILYMOTION
import com.kubista.userexplorer.utils.BASE_URL_GITHUB
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

/**
 * Module which provides getAllFromDailymotion required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the User service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the User service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDailymotionUserApi(@Named("Dailymotion") retrofit: Retrofit): DailymotionUserApi {
        return retrofit.create(DailymotionUserApi::class.java)
    }

    /**
     * Provides the User service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the User service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitHubUserApi(@Named("GitHub") retrofit: Retrofit): GitHubUserApi {
        return retrofit.create(GitHubUserApi::class.java)
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
    @Named("Dailymotion")
    internal fun provideRetrofitInterfaceDailymotion(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_DAILYMOTION)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}