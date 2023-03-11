package com.ngapp.metanmobile.domain.usecase.profile

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.data.model.dto.profile.toProfileEntity
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UpdateProfile @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val profileRepo: ProfileRepository
) : LocalUseCase<UpdateProfile.Params, Unit>() {

    data class Params(
        val profile: ProfileDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val profileDto = params.profile
        profileRepo.updateProfile(profileDto.toProfileEntity())
        emit(Unit)
    }
}