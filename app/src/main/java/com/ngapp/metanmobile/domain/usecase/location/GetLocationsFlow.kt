package com.ngapp.metanmobile.domain.usecase.location

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.repository.location.LocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetLocationsFlow @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val locationRepo: LocationRepository,
    @ApplicationContext val context: Context
) : LocalUseCase<Boolean, List<LocationDto>>() {

    override suspend fun FlowCollector<List<LocationDto>>.execute(params: Boolean) {

        val getLocations = locationRepo.getLocations(params)
        emit(getLocations)
    }
}