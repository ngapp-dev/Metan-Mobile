package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import android.content.Context
import com.ngapp.metanmobile.data.repository.contacts.ContactsRepository
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.metanmobile.domain.usecase.contacts.GetContacts
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
class ContactsDomainModule {

    @Singleton
    @Provides
    fun provideGetContacts(repository: ContactsRepository): GetContacts {
        return GetContacts(repository)
    }
}