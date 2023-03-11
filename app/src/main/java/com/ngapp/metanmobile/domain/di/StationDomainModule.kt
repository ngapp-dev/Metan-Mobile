package com.ngapp.metanmobile.domain.di

import android.annotation.SuppressLint
import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.metanmobile.domain.usecase.station.favorite.AddStationFavorite
import com.ngapp.metanmobile.domain.usecase.station.favorite.DeleteStationFavorite
import com.ngapp.metanmobile.domain.usecase.station.favorite.UpdateStationFavorite
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.metanmobile.domain.usecase.station.GetStationDetail
import com.ngapp.metanmobile.domain.usecase.station.GetStations
import com.ngapp.metanmobile.domain.usecase.station.GetStationsFlow
import com.ngapp.metanmobile.domain.usecase.station.favorite.GetStationFavorites
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class StationDomainModule {

    @Singleton
    @Provides
    fun provideGetStations(stationsRepository: StationsRepository, locationRepository: LocationRepository): GetStations {
        return GetStations(stationsRepository, locationRepository)
    }

    @Singleton
    @Provides
    fun provideGetStationsFlow(stationsRepository: StationsRepository, locationRepository: LocationRepository): GetStationsFlow {
        return GetStationsFlow(stationsRepository, locationRepository)
    }

    @Singleton
    @Provides
    fun provideGetStationDetail(stationsRepository: StationsRepository, locationRepository: LocationRepository, newsRepository: NewsRepository): GetStationDetail {
        return GetStationDetail(stationsRepository, locationRepository, newsRepository)
    }

    @Singleton
    @Provides
    fun provideAddStationFavorite(repository: StationsRepository): AddStationFavorite {
        return AddStationFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteStationFavorite(repository: StationsRepository): DeleteStationFavorite {
        return DeleteStationFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideGetStationFavorites(stationsRepository: StationsRepository, locationRepository: LocationRepository): GetStationFavorites {
        return GetStationFavorites(stationsRepository, locationRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateStationFavorite(repository: StationsRepository): UpdateStationFavorite {
        return UpdateStationFavorite(repository)
    }
}