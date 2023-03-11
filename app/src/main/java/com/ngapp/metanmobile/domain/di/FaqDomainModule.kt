package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import com.ngapp.metanmobile.data.repository.settings.SettingsRepository
import com.ngapp.metanmobile.domain.usecase.faq.GetFaq
import com.ngapp.metanmobile.domain.usecase.faq.GetFaqFlow
import com.ngapp.metanmobile.domain.usecase.profile.GetProfileFlow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class FaqDomainModule {

    @Singleton
    @Provides
    fun provideGetFaqFlow(repository: SettingsRepository): GetFaqFlow {
        return GetFaqFlow(repository)
    }

    @Singleton
    @Provides
    fun provideGetFaq(repository: SettingsRepository): GetFaq {
        return GetFaq(repository)
    }

}