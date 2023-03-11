package com.ngapp.metanmobile.presentation.settings

import androidx.compose.runtime.mutableStateOf
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.domain.usecase.profile.GetProfileFlow
import com.ngapp.metanmobile.domain.usecase.profile.UpdateProfile
import com.ngapp.metanmobile.data.model.dto.language.LanguageDto
import com.ngapp.metanmobile.provider.LanguageProvider
import com.ngapp.metanmobile.provider.ResourceProvider
import com.ngapp.metanmobile.provider.ThemeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getProfile: GetProfileFlow,
    private val updateProfile: UpdateProfile,
    private val themeProvider: ThemeProvider,
    private val languageProvider: LanguageProvider,
    private val resourceProvider: ResourceProvider
) : MviViewModel<BaseViewState<SettingsViewState>, SettingsEvent>() {

    override fun onTriggerEvent(eventType: SettingsEvent) {
        when (eventType) {
            is SettingsEvent.LoadProfile -> onLoadProfile()
            is SettingsEvent.UpdateProfile -> onUpdateProfile(eventType.dto)
        }
    }

    fun isNightMode() = themeProvider.isNightMode()

    fun saveThemeMode(isChecked: Boolean) {
        themeProvider.theme = if (isChecked) {
            ThemeProvider.Theme.DARK
        } else {
            ThemeProvider.Theme.LIGHT
        }
    }

    private var languages = emptyList<LanguageDto>()

    var langs = mutableStateOf<List<LanguageDto>>(emptyList())

    init {
        languages = getLanguages()
        getLanguage()
    }

    fun saveLanguageCode(code: String) {
        languageProvider.saveLanguageCode(code)
    }

    private fun getLanguageCode(): String {
        return languageProvider.getLanguageCode()
    }

    private fun getLanguage() {
        languages.map {
            it.isSelected = it.code == getLanguageCode()
        }
        langs.value = languages
    }

    private fun getLanguages(): List<LanguageDto> {
        return listOf(
            LanguageDto(
                id = 1,
                code = "ru",
                name = resourceProvider.getString(R.string.pref_language_russian),
                isSelected = false
            ),
            LanguageDto(
                id = 2,
                code = "be",
                name = resourceProvider.getString(R.string.pref_language_belarusian),
                isSelected = false
            ),
            LanguageDto(
                id = 3,
                code = "en",
                name = resourceProvider.getString(R.string.pref_language_english),
                isSelected = false
            )
        )
    }

    private fun onUpdateProfile(dto: ProfileDto) = safeLaunch {
        val params = UpdateProfile.Params(dto)
        call(updateProfile(params))
    }

    private fun onLoadProfile() = safeLaunch {
        setState(BaseViewState.Loading)
        val profile = getProfile(Unit)
        setState(BaseViewState.Data(SettingsViewState(profileData = profile)))
    }
}