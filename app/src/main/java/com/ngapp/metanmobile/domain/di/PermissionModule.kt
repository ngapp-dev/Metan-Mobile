package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import com.ngapp.metanmobile.app.component.PermissionsHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class PermissionModule {

    @Singleton
    @Provides
    fun providePermissionHandler(): PermissionsHandler {
        return PermissionsHandler()
    }
}