package com.ngapp.metanmobile.domain.usecase.station

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.FlowListUseCase
import com.ngapp.metanmobile.app.extension.distanceInKm
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetStationsFlow @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val stationsRepo: StationsRepository,
    internal val locationRepo: LocationRepository
) : FlowListUseCase<Boolean, List<StationDto>>() {


    override suspend fun FlowCollector<List<StationDto>>.execute(params: Boolean) {
        val getStations = stationsRepo.getStationList()
        val getLocations = locationRepo.getLocations(params)
        val currentLocation =
            if (getLocations.isNotEmpty()) getLocations[getLocations.size - 1] else null

        getStations.map { stationDto ->
            val distanceBetween = distanceInKm(
                currentLocation?.latitude ?: 0.0,
                currentLocation?.longitude ?: 0.0,
                stationDto.latitude?.toDouble() ?: 0.0,
                stationDto.longitude?.toDouble() ?: 0.0
            )
            stationDto.distanceBetween = if (currentLocation != null) distanceBetween else 0.0
        }

        emit(getStations)
    }
}