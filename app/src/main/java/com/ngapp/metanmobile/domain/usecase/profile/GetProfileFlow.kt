package com.ngapp.metanmobile.domain.usecase.profile

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.data.model.dto.profile.toProfileDtoList
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetProfileFlow @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val profileRepo: ProfileRepository,
) : LocalUseCase<Unit, List<ProfileDto>>() {


    override suspend fun FlowCollector<List<ProfileDto>>.execute(params: Unit) {
        val getProfile = profileRepo.getProfile().toProfileDtoList()
        emit(getProfile)
    }
}