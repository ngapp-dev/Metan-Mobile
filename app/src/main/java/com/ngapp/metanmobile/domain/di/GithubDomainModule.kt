package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import com.ngapp.metanmobile.data.repository.settings.SettingsRepository
import com.ngapp.metanmobile.domain.usecase.githubuser.GetGithubUser
import com.ngapp.metanmobile.domain.usecase.profile.GetProfileFlow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class GithubDomainModule {

    @Singleton
    @Provides
    fun provideGetGithubUser(repository: SettingsRepository): GetGithubUser {
        return GetGithubUser(repository)
    }

}