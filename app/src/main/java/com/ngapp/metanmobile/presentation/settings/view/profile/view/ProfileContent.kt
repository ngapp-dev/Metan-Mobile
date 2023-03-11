package com.ngapp.metanmobile.presentation.settings.view.profile.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileViewModel
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileViewState

@Composable
fun ProfileContent(
    viewModel: ProfileViewModel = hiltViewModel(),
    viewState: ProfileViewState,
    paddingValues: PaddingValues,
    onUpdateProfileClick: (ProfileDto) -> Unit = {},
) {
    val profileItem by viewState.profileData.collectAsState(emptyList())
    val profile = if (profileItem.isNotEmpty()) profileItem[profileItem.size - 1] else null

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            ProfileBodyView(
                modifier = Modifier.fillMaxHeight(),
                viewModel = viewModel,
                profile = profile,
                onUpdateProfileClick = onUpdateProfileClick
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
