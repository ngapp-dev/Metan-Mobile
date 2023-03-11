package com.ngapp.metanmobile.presentation.settings

import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SettingsViewState(
    val profileData: Flow<List<ProfileDto>> = emptyFlow()
)

sealed class SettingsEvent {
    object LoadProfile : SettingsEvent()
    data class UpdateProfile(val dto: ProfileDto) : SettingsEvent()
}