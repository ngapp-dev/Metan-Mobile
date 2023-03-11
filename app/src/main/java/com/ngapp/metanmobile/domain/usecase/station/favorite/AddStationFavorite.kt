package com.ngapp.metanmobile.domain.usecase.station.favorite

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationEntity
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AddStationFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: StationsRepository
) : LocalUseCase<AddStationFavorite.Params, Unit>() {

    data class Params(
        val station: StationDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.station
        repository.saveFavorite(dto.toStationEntity())
        emit(Unit)
    }
}