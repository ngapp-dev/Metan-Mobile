package com.ngapp.metanmobile.data.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ngapp.framework.base.app.NetworkConfig
import com.ngapp.framework.network.*
import com.ngapp.framework.network.interceptor.HttpRequestInterceptor
import com.ngapp.metanmobile.data.remote.service.GithubService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "base_url"
private const val GITHUB_URL = "github_url"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    @Named(value = GITHUB_URL)
    fun provideGithubUrl(networkConfig: NetworkConfig): String {
        return networkConfig.githubUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = networkConfig.isDev())
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(): HttpRequestInterceptor {
        return createHttpRequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return createChuckInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        httpRequestInterceptor: HttpRequestInterceptor
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            interceptors = arrayOf(
                httpLoggingInterceptor,
                chuckerInterceptor,
                httpRequestInterceptor
            ),
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideGithubUserService(
        @Named(value = GITHUB_URL) githubUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): GithubService {
        return createRetrofitWithMoshi(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = githubUrl
        )
    }
}