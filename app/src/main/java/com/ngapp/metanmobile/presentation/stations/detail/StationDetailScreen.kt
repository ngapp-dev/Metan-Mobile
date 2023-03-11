package com.ngapp.metanmobile.presentation.stations.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.stations.detail.view.StationDetailContent
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.app.component.HandlePermissionsRequest
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/ecogas-map/{code}/"
        )
    ]
)
@Composable
fun StationDetailScreen(
    modifier: Modifier = Modifier,
    code: String,
    viewModel: StationDetailViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState: BaseViewState<*> by viewModel.uiState.collectAsState()

    val permissionsState by viewModel.permissionsState.collectAsState()

    val permissions = remember { PermissionsHandler.permissions }
    val locationPermissionGranted =
        permissionsState.multiplePermissionsState?.allPermissionsGranted ?: false

    HandlePermissionsRequest(permissions, viewModel.permissionsHandler)

    StationDetailBody(
        pressOnBack = { navigator.navigateUp() },
        viewModel = viewModel
    ) { padding ->
        StationDetailPage(uiState, viewModel, padding, navigator, code, locationPermissionGranted, modifier)
    }

}

@Composable
private fun StationDetailPage(
    uiState: BaseViewState<*>,
    viewModel: StationDetailViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    code: String,
    locationPermissionGranted: Boolean,
    modifier: Modifier,
) {
    when (uiState) {
        is BaseViewState.Data -> StationDetailContent(
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<StationDetailViewState>>().value,
            locationPermissionGranted = locationPermissionGranted,
            selectNewsItem = { id -> navigator.openNewsDetail(id) }
        )
        is BaseViewState.Empty -> EmptyView()
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(StationDetailEvent.LoadDetail(code))
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(StationDetailEvent.LoadDetail(code))
    })
}

@Composable
private fun StationDetailBody(
    pressOnBack: () -> Unit = {},
    viewModel: StationDetailViewModel,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            MetanMobileToolbarWithNavIconAndShareButton(
                pressOnBack = pressOnBack,
                elevation = 0.dp,
                onShareActionClicked = { viewModel.onTriggerEvent(StationDetailEvent.ShareStation) }
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun StationDetailScreenPreview() {
    MetanMobileTheme {
        Surface {
//            StationDetailBody {
//            }
        }
    }
}