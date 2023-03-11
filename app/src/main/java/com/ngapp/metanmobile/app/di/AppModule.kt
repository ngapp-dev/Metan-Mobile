package com.ngapp.metanmobile.app.di

import android.content.Context
import com.ngapp.framework.base.app.AppInitializer
import com.ngapp.framework.base.app.AppInitializerImpl
import com.ngapp.framework.base.app.NetworkConfig
import com.ngapp.framework.base.app.TimberInitializer
import com.ngapp.framework.pref.CacheManager
import com.ngapp.metanmobile.app.MetanMobileNetworkConfig
import com.ngapp.metanmobile.app.MetanMobileApp
import com.ngapp.metanmobile.app.tools.Analytics
import com.ngapp.metanmobile.app.tools.MetanMobileAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(): MetanMobileApp {
        return MetanMobileApp()
    }

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return MetanMobileNetworkConfig()
    }

    @Provides
    @Singleton
    fun provideCacheManager(@ApplicationContext context: Context): CacheManager {
        return CacheManager(context)
    }

    @Provides
    @Singleton
    fun provideTimberInitializer(
        networkConfig: NetworkConfig
    ) = TimberInitializer(networkConfig.isDev())

    @Provides
    @Singleton
    fun provideAppInitializer(
        timberInitializer: TimberInitializer
    ): AppInitializer {
        return AppInitializerImpl(timberInitializer)
    }

    @Provides
    @Singleton
    fun provideMetanMobileAnalytics(@ApplicationContext context: Context): Analytics {
        return MetanMobileAnalytics(context)
    }
}