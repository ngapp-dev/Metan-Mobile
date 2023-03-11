package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import com.ngapp.metanmobile.data.repository.language.LanguageRepository
import com.ngapp.metanmobile.domain.usecase.language.GetLanguage
import com.ngapp.metanmobile.domain.usecase.language.SaveLanguage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class LanguageModule {

    @Singleton
    @Provides
    fun provideSaveLanguage(repository: LanguageRepository): SaveLanguage {
        return SaveLanguage(repository)
    }

    @Singleton
    @Provides
    fun provideGetLanguage(repository: LanguageRepository): GetLanguage {
        return GetLanguage(repository)
    }
}