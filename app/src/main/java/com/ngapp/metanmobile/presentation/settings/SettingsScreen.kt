package com.ngapp.metanmobile.presentation.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.EmptyView
import com.ngapp.metanmobile.app.widget.ErrorView
import com.ngapp.metanmobile.app.widget.LoadingView
import com.ngapp.metanmobile.app.widget.SettingsMetanMobileToolbar
import com.ngapp.metanmobile.presentation.settings.view.SettingsContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()
    val themeCheckedState = remember { mutableStateOf(viewModel.isNightMode()) }

    SettingsBody(modifier, navigator) { padding ->
        SettingsPage(modifier, uiState, themeCheckedState, viewModel, padding, navigator)
    }
}

@Composable
private fun SettingsPage(
    modifier: Modifier,
    uiState: BaseViewState<*>,
    themeCheckedState: MutableState<Boolean>,
    viewModel: SettingsViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
) {
    when (uiState) {
        is BaseViewState.Data ->
            SettingsContent(
                viewModel = viewModel,
                themeCheckedState = themeCheckedState,
                paddingValues = paddings,
                viewState = uiState.cast<BaseViewState.Data<SettingsViewState>>().value,
                onUpdateProfile = { navigator.openUpdateProfile() },
                onContactsClick = { navigator.openContacts() },
                onFaqPageClick = { navigator.openFaq() },
                onCalculatorsPageClick = { navigator.openCalculators() },
                onAppLanguagePageClick = { navigator.openAppLanguage() },
                onAboutPageClick = { navigator.openAbout() },
                onLegalRegulationsPageClick = { navigator.openLegalRegulations() }
            )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(SettingsEvent.LoadProfile)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(SettingsEvent.LoadProfile)
    })
}

@Composable
private fun SettingsBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SettingsMetanMobileToolbar(
                titleResId = R.string.toolbar_settings_title,
                pressOnBack = {
                    navigator.navigateUp()
                },
                elevation = 0.dp,
                onSupportClicked = { navigator.openContacts() }
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun SettingsScreenPreview() {
    MetanMobileTheme {
        Surface {
//            SettingsBody {
//            }
        }
    }
}