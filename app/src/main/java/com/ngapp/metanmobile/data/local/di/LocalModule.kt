package com.ngapp.metanmobile.data.local.di

import android.content.Context
import androidx.room.Room
import com.ngapp.metanmobile.BuildConfig
import com.ngapp.metanmobile.data.local.dao.LocationDao
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.local.db.MetanMobileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): MetanMobileDatabase {
        return Room.databaseBuilder(context, MetanMobileDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(appDatabase: MetanMobileDatabase): ProfileDao {
        return appDatabase.profileDao()
    }

    @Provides
    @Singleton
    fun provideStationFavoriteDao(appDatabase: MetanMobileDatabase): StationFavoriteDao {
        return appDatabase.stationFavoriteDao()
    }

    @Provides
    @Singleton
    fun provideLocationDao(appDatabase: MetanMobileDatabase): LocationDao {
        return appDatabase.locationDao()
    }
}