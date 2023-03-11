package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import android.content.Context
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.metanmobile.domain.usecase.news.GetNews
import com.ngapp.metanmobile.domain.usecase.news.GetNewsDetail
import com.ngapp.metanmobile.domain.usecase.news.GetPagingNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class NewsDomainModule {

    @Singleton
    @Provides
    fun provideGetNewsList(repository: NewsRepository): GetNews {
        return GetNews(repository)
    }

    @Singleton
    @Provides
    fun provideGetNewsDetail(repository: NewsRepository): GetNewsDetail {
        return GetNewsDetail(repository)
    }
}