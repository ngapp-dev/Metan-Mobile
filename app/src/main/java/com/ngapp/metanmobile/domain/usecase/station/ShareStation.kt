package com.ngapp.metanmobile.domain.usecase.station

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class ShareStation @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val stationRepo: StationsRepository
) : LocalUseCase<ShareStation.Params, Unit>() {

    data class Params(
        val stationDto: StationDto?
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        stationRepo.shareStation(params.stationDto)
        emit(Unit)
    }
}