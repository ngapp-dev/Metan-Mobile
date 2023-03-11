package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@SuppressLint("VisibleForTests")
@Module(
    includes = [
        StationDomainModule::class,
        NewsDomainModule::class,
        ProfileDomainModule::class,
        ContactsDomainModule::class,
        WelcomeModule::class,
        LanguageModule::class,
        PermissionModule::class,
        FaqDomainModule::class,
        GithubDomainModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DomainModule