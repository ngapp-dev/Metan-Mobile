package com.ngapp.metanmobile.domain.usecase.station

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.extension.orZero
import com.ngapp.framework.network.DataState
import com.ngapp.framework.network.apiCall
import com.ngapp.framework.usecase.DataStateUseCase
import com.ngapp.metanmobile.app.extension.distanceInKm
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetStationDetail @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val stationsRepo: StationsRepository,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val locationRepo: LocationRepository,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val newsRepo: NewsRepository
) : DataStateUseCase<GetStationDetail.Params, StationDto>() {

    data class Params(
        val stationCode: String? = null,
        val locationPermissionGranted: Boolean = false
    )

    override suspend fun FlowCollector<DataState<StationDto>>.execute(params: Params) {
        val getStation =
            stationsRepo.getStation(stationCode = params.stationCode.orEmpty())

        val service = apiCall { getStation }

        val getLocations = locationRepo.getLocations(params.locationPermissionGranted)
        val currentLocation =
            if (getLocations.isNotEmpty()) getLocations[getLocations.size - 1] else null

        service.map { stationDto ->
            val distanceBetween = distanceInKm(
                currentLocation?.latitude ?: 0.0,
                currentLocation?.longitude ?: 0.0,
                stationDto.latitude?.toDouble() ?: 0.0,
                stationDto.longitude?.toDouble() ?: 0.0
            )

            stationDto.distanceBetween = if (currentLocation != null) distanceBetween else 0.0

            val stationFav = stationsRepo.getFavorite(getStation.id.orZero())
            stationDto.isFavorite = stationFav != null

            val newsService = apiCall { newsRepo.getNewsList() }
            if (newsService is DataState.Success) {
                val relatedNews = newsService.result.filter { newsDto -> newsDto.labelNews == getStation.title }
                stationDto.relatedNewsDtoList.addAll(relatedNews)
            }
        }

        emit(service)
    }
}