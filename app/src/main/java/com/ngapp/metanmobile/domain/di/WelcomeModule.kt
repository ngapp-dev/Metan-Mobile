package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import com.ngapp.metanmobile.data.repository.welcome.WelcomeRepository
import com.ngapp.metanmobile.domain.usecase.welcome.ReadOnBoarding
import com.ngapp.metanmobile.domain.usecase.welcome.SaveOnBoarding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class WelcomeModule {

    @Singleton
    @Provides
    fun provideSaveOnBoarding(repository: WelcomeRepository): SaveOnBoarding {
        return SaveOnBoarding(repository)
    }

    @Singleton
    @Provides
    fun provideReadOnBoarding(repository: WelcomeRepository): ReadOnBoarding {
        return ReadOnBoarding(repository)
    }
}