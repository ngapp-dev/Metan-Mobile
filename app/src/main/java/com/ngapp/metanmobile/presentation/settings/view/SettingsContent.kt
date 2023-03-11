package com.ngapp.metanmobile.presentation.settings.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.jetframework.rememberFlowWithLifecycle
import com.ngapp.metanmobile.presentation.settings.SettingsViewModel
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.SettingsViewState

@Composable
fun SettingsContent(
    viewModel: SettingsViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    viewState: SettingsViewState,
    onUpdateProfile: (ProfileDto?) -> Unit = {},
    themeCheckedState: MutableState<Boolean>,
    onFaqPageClick: () -> Unit,
    onContactsClick: () -> Unit,
    onCalculatorsPageClick: () -> Unit,
    onAppLanguagePageClick: () -> Unit,
    onAboutPageClick: () -> Unit,
    onLegalRegulationsPageClick: () -> Unit
) {
    val profileItem by viewState.profileData.collectAsState(emptyList())
    val profile = if (profileItem.isNotEmpty()) profileItem[profileItem.size - 1] else null

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            SettingsBodyView(
                modifier = Modifier.fillMaxHeight(),
                viewModel = viewModel,
                profile = profile,
                themeCheckedState = themeCheckedState,
                onUpdateProfile = onUpdateProfile,
                onFaqPageClick = onFaqPageClick,
                onContactsClick = onContactsClick,
                onCalculatorsPageClick = onCalculatorsPageClick,
                onAppLanguagePageClick = onAppLanguagePageClick,
                onAboutPageClick = onAboutPageClick,
                onLegalRegulationsPageClick = onLegalRegulationsPageClick
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
