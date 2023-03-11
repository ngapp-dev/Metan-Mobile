package com.ngapp.metanmobile.presentation.settings.view.profile

import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfileViewState(
    val profileData: Flow<List<ProfileDto>> = emptyFlow()
)

sealed class ProfileEvent {
    object LoadProfile : ProfileEvent()
    data class UpdateProfile(val dto: ProfileDto) : ProfileEvent()
    data class UpdateFirstname(val input: String) : ProfileEvent()
    data class UpdateLastname(val input: String) : ProfileEvent()
}