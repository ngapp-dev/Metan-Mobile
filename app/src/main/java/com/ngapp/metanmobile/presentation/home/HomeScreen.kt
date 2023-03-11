package com.ngapp.metanmobile.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.app.component.HandlePermissionsRequest
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.presentation.home.view.HomeContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/"
        )
    ]
)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()
    val permissionsState by viewModel.permissionsState.collectAsState()

    val permissions = remember { PermissionsHandler.permissions }
    val locationPermissionGranted =
        permissionsState.multiplePermissionsState?.allPermissionsGranted ?: false

    HandlePermissionsRequest(permissions, viewModel.permissionsHandler)

    HomeBody(modifier, navigator, viewModel, locationPermissionGranted) { padding ->
        HomePage(uiState, viewModel, padding, navigator, modifier, locationPermissionGranted)
    }
}

@Composable
private fun HomePage(
    uiState: BaseViewState<*>,
    viewModel: HomeViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier,
    locationPermissionGranted: Boolean,
) {
    when (uiState) {
        is BaseViewState.Data -> HomeContent(
            viewModel = viewModel,
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<HomeViewState>>().value,
            onShowAllNewsClick = { navigator.openNews() },
            onSeeAllNewsClick = { navigator.openFaq() },
            selectItem = { code -> navigator.openNewsDetail(code) },
            onStationDetailClick = { code -> navigator.openStationDetail(code) },
            locationPermissionGranted = locationPermissionGranted
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(HomeEvent.LoadHome)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(HomeEvent.LoadHome)
    })
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    viewModel: HomeViewModel,
    locationPermissionGranted: Boolean,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeMetanMobileToolbar(
                elevation = 0.dp,
                onUserClicked = { navigator.openCabinet() },
                onSettingsClicked = { navigator.openSettings() }
            )
        },
        content = {
            if (!locationPermissionGranted) {
                viewModel.onTriggerEvent(HomeEvent.PermissionRequired)
            }
            pageContent.invoke(it)
        }
    )
}