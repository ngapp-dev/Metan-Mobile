package com.ngapp.metanmobile.presentation.stations.list

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.IndicatorHeight
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.stations.list.view.StationListContent
import com.ngapp.metanmobile.presentation.stations.list.view.StationMapContent
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.HandlePermissionsRequest
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/ecogas-map/"
        )
    ]
)
@Composable
fun StationsScreen(
    modifier: Modifier = Modifier,
    viewModel: StationsViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()
    val permissionsState by viewModel.permissionsState.collectAsState()

    val permissions = remember { PermissionsHandler.permissions  }
    val locationPermissionGranted =
        permissionsState.multiplePermissionsState?.allPermissionsGranted ?: false

    HandlePermissionsRequest(permissions, viewModel.permissionsHandler)

    StationsBody(modifier, viewModel) { padding ->

        Column {
            val tabsName = remember { StationTabs.values().map { it.value } }
            val selectedIndex =
                rememberSaveable { mutableStateOf(StationTabs.LIST.ordinal) }
            TabRow(
                selectedTabIndex = selectedIndex.value,
                backgroundColor = MetanMobileColors.background,
                divider = {},
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = Blue,
                        height = IndicatorHeight,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex.value])
                            .padding(horizontal = 16.dp)
                            .offset(y = (-5).dp)
                    )
                }
            ) {

                tabsName.forEachIndexed { index, stringResourceId ->
                    when (stringResourceId) {
                        StationTabs.LIST.value -> {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                                    .padding(bottom = 5.dp),
                                elevation = 4.dp,
                                shape = RoundedCornerShape(bottomStart = 12.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = MetanMobileColors.cardBackgroundColor)
                                        .clip(RoundedCornerShape(bottomStart = 12.dp))
                                ) {
                                    Tab(
                                        selected = index == selectedIndex.value,
                                        onClick = {
                                            selectedIndex.value = StationTabs.LIST.ordinal
                                        },
                                        text = {
                                            Text(
                                                text = stringResource(id = stringResourceId),
                                                style = MetanMobileTypography.h4,
                                            )
                                        }
                                    )
                                }
                            }
                        }
                        StationTabs.MAP.value -> {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                                    .padding(bottom = 5.dp),
                                elevation = 4.dp,
                                shape = RoundedCornerShape(bottomEnd = 12.dp)
                            )
                            {
                                Box(
                                    contentAlignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = MetanMobileColors.cardBackgroundColor)
                                        .clip(RoundedCornerShape(bottomEnd = 12.dp))
                                ) {
                                    Tab(
                                        selected = index == selectedIndex.value,
                                        onClick = {
                                            selectedIndex.value = StationTabs.MAP.ordinal
                                        },
                                        text = {
                                            Text(
                                                text = stringResource(id = stringResourceId),
                                                style = MetanMobileTypography.h4,
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            when (selectedIndex.value) {
                StationTabs.LIST.ordinal -> {
                    StationsListPage(uiState, viewModel, padding, navigator, locationPermissionGranted, modifier)
                }
                StationTabs.MAP.ordinal -> {
                    StationsMapPage(
                        uiState, viewModel, padding, navigator, locationPermissionGranted, modifier
                    )
                }
            }
        }
    }
}

private enum class StationTabs(@StringRes val value: Int) {
    LIST(R.string.title_station_list),
    MAP(R.string.title_stations_map)
}

@Composable
private fun StationsMapPage(
    uiState: BaseViewState<*>,
    viewModel: StationsViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    locationPermissionGranted: Boolean,
    modifier: Modifier,

) {
    when (uiState) {
        is BaseViewState.Data -> {
            StationMapContent(
                viewModel = viewModel,
                paddingValues = paddings,
                viewState = uiState.cast<BaseViewState.Data<StationsViewState>>().value,
                locationPermissionGranted = locationPermissionGranted,
                selectItem = { code -> navigator.openStationDetail(code) },
            )
        }
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(StationsEvent.LoadStations)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(StationsEvent.LoadStations)
    })
}

@Composable
private fun StationsListPage(
    uiState: BaseViewState<*>,
    viewModel: StationsViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    locationPermissionGranted: Boolean,
    modifier: Modifier
) {
    when (uiState) {
        is BaseViewState.Data -> StationListContent(
            viewModel = viewModel,
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<StationsViewState>>().value,
            locationPermissionGranted = locationPermissionGranted,
            selectItem = { code -> navigator.openStationDetail(code) }
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(StationsEvent.LoadStations)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(StationsEvent.LoadStations)
    })
}

@Composable
private fun StationsBody(
    modifier: Modifier = Modifier,
    viewModel: StationsViewModel,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    var showSearchMenu by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            if (!showSearchMenu) {
                MetanMobileToolbarWithFilterAndSearchButtons(
                    elevation = 0.dp,
                    onSearchActionClicked = { showSearchMenu = true },
                    onFilterActionClicked = { viewModel.onTriggerEvent(StationsEvent.OpenAlertDialog) }
                )
            } else {
                MetanMobileToolbarWithFilterAndSearchField(
                    elevation = 0.dp,
                    searchText = viewModel.searchQuery,
                    placeholderText = stringResource(R.string.placeholder_search_stations),
                    onSearchTextChanged = {
                        viewModel.onTriggerEvent(
                            StationsEvent.UpdateSearchQuery(
                                it
                            )
                        )
                    },
                    onClearClick = { viewModel.onClearClick() },
                    onNavigateBack = {
                        viewModel.onClearClick()
                        showSearchMenu = false
                    },
                    onFilterActionClicked = { viewModel.onTriggerEvent(StationsEvent.OpenAlertDialog) }
                )

            }
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun StationsScreenPreview() {
    MetanMobileTheme {
        Surface {
//            StationsBody() {
//
//            }
        }
    }
}