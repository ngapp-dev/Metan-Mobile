package com.ngapp.metanmobile.presentation.settings.view.profile

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.view.profile.view.ProfileBottomSheetContent
import com.ngapp.metanmobile.presentation.settings.view.profile.view.ProfileContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Destination
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()

    val profileBottomSheetState: ModalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val profileCoroutineScope = rememberCoroutineScope()
    val selectedProfile = remember { mutableStateOf<ProfileDto?>(ProfileDto.init()) }

    ProfileBody(modifier, navigator, profileCoroutineScope, profileBottomSheetState,
        profileSheetContent = {
            selectedProfile.value?.let {
                ProfileBottomSheetContent(
                    profile = it,
                    onCancel = {
                        profileCoroutineScope.launch {
                            profileBottomSheetState.hide()
                        }
                    },
                    onApprove = {
                        profileCoroutineScope.launch {
                            viewModel.onTriggerEvent(ProfileEvent.UpdateProfile(ProfileDto.init()))
                            profileBottomSheetState.hide()
                            navigator.navigateUp()
                        }
                    }
                )
            }
        }
    ) { padding ->
        ProfilePage(modifier, uiState, viewModel, padding)
    }
}

@Composable
private fun ProfilePage(
    modifier: Modifier,
    uiState: BaseViewState<*>,
    viewModel: ProfileViewModel,
    paddings: PaddingValues
) {
    val context = LocalContext.current
    when (uiState) {
        is BaseViewState.Data ->
            ProfileContent(
                viewState = uiState.cast<BaseViewState.Data<ProfileViewState>>().value,
                paddingValues = paddings,
                onUpdateProfileClick = {
                    viewModel.onTriggerEvent(ProfileEvent.UpdateProfile(it))
                    Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT).show()
                },
            )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = { viewModel.onTriggerEvent(ProfileEvent.LoadProfile) }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(ProfileEvent.LoadProfile)
    })
}

@Composable
private fun ProfileBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    profileCoroutineScope: CoroutineScope,
    profileBottomSheetState: ModalBottomSheetState,
    profileSheetContent: @Composable ColumnScope.() -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    ModalBottomSheetLayout(
        sheetContent = profileSheetContent,
        modifier = modifier,
        sheetState = profileBottomSheetState,
        sheetContentColor = MetanMobileColors.background,
        sheetShape = RectangleShape,
        content = {
            Scaffold(
                modifier = modifier,
                topBar = {
                    MetanMobileToolbarWithNavIconAndRemoveButton(
                        titleResId = R.string.toolbar_profile_title,
                        pressOnBack = { navigator.navigateUp() },
                        elevation = 0.dp,
                        onRemoveActionClicked = {
                            profileCoroutineScope.launch {
                                if (profileBottomSheetState.isVisible) {
                                    profileBottomSheetState.hide()
                                } else {
                                    profileBottomSheetState.show()
                                }
                            }
                        }
                    )
                },
                content = { pageContent.invoke(it) }
            )
        }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun ProfileScreenPreview() {
    MetanMobileTheme {
        Surface {
//            ProfileBody {
//            }
        }
    }
}