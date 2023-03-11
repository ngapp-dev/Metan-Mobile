package com.ngapp.metanmobile.presentation.settings.view.profile

import androidx.compose.runtime.*
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.domain.usecase.profile.GetProfileFlow
import com.ngapp.metanmobile.domain.usecase.profile.UpdateProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfileFlow,
    private val updateProfile: UpdateProfile,
) : MviViewModel<BaseViewState<ProfileViewState>, ProfileEvent>() {

    var firstName by mutableStateOf<String>("User")
    var lastName by  mutableStateOf<String>("")

    override fun onTriggerEvent(eventType: ProfileEvent) {
        when (eventType) {
            is ProfileEvent.LoadProfile -> onLoadProfile()
            is ProfileEvent.UpdateProfile -> onUpdateProfile(eventType.dto)
            is ProfileEvent.UpdateFirstname -> onUpdateFirstname(eventType.input)
            is ProfileEvent.UpdateLastname -> onUpdateLastname(eventType.input)
        }
    }

//    val userNameHasError: StateFlow<Boolean> =
//        snapshotFlow { username }
//            .mapLatest {
////                signUpRepository.isUsernameAvailable(it)
//            }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = false
//            )
//
//    val userNameHasLocalError by derivedStateOf {
//        signUpRepository.isUsernameCorrect(username)
//    }


    private fun onUpdateFirstname(input: String) {
        firstName = input
    }
    private fun onUpdateLastname(input: String) {
        lastName = input
    }

    private fun onUpdateProfile(dto: ProfileDto) = safeLaunch {
        val params = UpdateProfile.Params(dto)
        call(updateProfile(params))
    }

    private fun onLoadProfile() = safeLaunch {
        setState(BaseViewState.Loading)
        val profile = getProfile(Unit)
        setState(BaseViewState.Data(ProfileViewState(profileData = profile)))
    }
}