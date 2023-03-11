package com.ngapp.metanmobile.data.repository.di

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.ngapp.metanmobile.data.local.dao.LocationDao
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.local.db.MetanMobileDatabase
import com.ngapp.metanmobile.data.remote.service.GithubService
import com.ngapp.metanmobile.data.repository.contacts.ContactsRepository
import com.ngapp.metanmobile.data.repository.language.LanguageRepository
import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.settings.SettingsRepository
import com.ngapp.metanmobile.data.repository.welcome.WelcomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideStationsRepository(
        dao: StationFavoriteDao,
        @ApplicationContext context: Context,
    ) = StationsRepository(
        dao,
        context
    )

    @Singleton
    @Provides
    fun provideNewsRepository(
        @ApplicationContext context: Context,
    ) = NewsRepository(
        context
    )

    @Singleton
    @Provides
    fun provideContactsRepository(
        @ApplicationContext context: Context,
    ) = ContactsRepository(
        context
    )

    @Singleton
    @Provides
    fun provideWelcomeRepository(
        @ApplicationContext context: Context,
    ) = WelcomeRepository(
        context
    )

    @Singleton
    @Provides
    fun provideLanguageRepository(
        @ApplicationContext context: Context,
    ) = LanguageRepository(
        context
    )


    @Singleton
    @Provides
    fun provideProfileRepository(
        database: MetanMobileDatabase
    ) = ProfileRepository(
        database
    )

    @Singleton
    @Provides
    fun provideLocationRepository(
        database: MetanMobileDatabase,
        @ApplicationContext context: Context
    ) = LocationRepository(
        database,
        context
    )

    @Singleton
    @Provides
    fun provideSettingsRepository(
        service: GithubService,
        @ApplicationContext context: Context
    ) = SettingsRepository(
        service,
        context
    )
}