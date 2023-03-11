package com.ngapp.metanmobile.presentation.favorites

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.favorites.view.FavoritesContent
import com.ngapp.metanmobile.presentation.favorites.view.FavoritesBottomSheetContent
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.AppbarBottomExpanderView
import com.ngapp.metanmobile.app.component.HandlePermissionsRequest
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.provider.NavigationProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
    stationsBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()

    val permissionsState by viewModel.permissionsState.collectAsState()
    val permissions = remember { PermissionsHandler.permissions }
    val locationPermissionGranted =
        permissionsState.multiplePermissionsState?.allPermissionsGranted ?: false

    HandlePermissionsRequest(permissions, viewModel.permissionsHandler)

    val stationsCoroutineScope = rememberCoroutineScope()
    val selectedStationFavorite = remember { mutableStateOf(StationDto.init()) }

    FavoritesBody(
        modifier, viewModel, stationsBottomSheetState,
        stationsSheetContent = {
            FavoritesBottomSheetContent(
                station = selectedStationFavorite.value,
                onCancel = {
                    stationsCoroutineScope.launch {
                        stationsBottomSheetState.hide()
                    }
                },
                onApprove = {
                    stationsCoroutineScope.launch {
                        viewModel.onTriggerEvent(
                            FavoritesEvent.DeleteStationFavorite(
                                selectedStationFavorite.value.id.orZero()
                            )
                        )
                        stationsBottomSheetState.hide()
                    }
                }
            )
        }
    ) { padding ->
        StationsFavoritesPage(
            stationsCoroutineScope,
            stationsBottomSheetState,
            uiState,
            viewModel,
            padding,
            navigator,
            selectedStationFavorite,
            locationPermissionGranted,
            modifier
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun StationsFavoritesPage(
    stationsCoroutineScope: CoroutineScope = rememberCoroutineScope(),
    stationsBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    uiState: BaseViewState<*>,
    viewModel: FavoritesViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    selectedStationFavorite: MutableState<StationDto>,
    locationPermissionGranted: Boolean,
    modifier: Modifier,
) {
    when (uiState) {
        is BaseViewState.Data -> {
            FavoritesContent(
                viewModel = viewModel,
                paddingValues = paddings,
                viewState = uiState.cast<BaseViewState.Data<FavoritesViewState>>().value,
                selectedStationFavorite = selectedStationFavorite,
                locationPermissionGranted = locationPermissionGranted,
                onDetailItemClick = { code -> navigator.openStationDetail(code) },
                onDeleteItemClick = {
                    stationsCoroutineScope.launch {
                        if (stationsBottomSheetState.isVisible) {
                            stationsBottomSheetState.hide()
                        } else {
                            stationsBottomSheetState.show()
                        }
                    }
                }
            )
        }
        is BaseViewState.Empty -> {
            LottieEmptyView(modifier = modifier)
        }
        is BaseViewState.Error -> LottieErrorView(
            modifier = modifier,
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(FavoritesEvent.LoadStationFavorites)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(FavoritesEvent.LoadStationFavorites)
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FavoritesBody(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel,
    stationsBottomSheetState: ModalBottomSheetState,
    stationsSheetContent: @Composable ColumnScope.() -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    var showSearchMenu by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetContent = stationsSheetContent,
        modifier = modifier,
        sheetState = stationsBottomSheetState,
        sheetContentColor = MetanMobileColors.background,
        sheetShape = RectangleShape,
        content = {
            Scaffold(
                topBar = {
                    if (!showSearchMenu) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            MetanMobileToolbarWithFilterAndSearchButtons(
                                titleResId = R.string.toolbar_favorites_title,
                                elevation = 0.dp,
                                onSearchActionClicked = { showSearchMenu = true },
                                onFilterActionClicked = { viewModel.onTriggerEvent(FavoritesEvent.OpenAlertDialog) }
                            )
                            AppbarBottomExpanderView()
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            MetanMobileToolbarWithFilterAndSearchField(
                                elevation = 0.dp,
                                searchText = viewModel.searchQuery,
                                placeholderText = stringResource(R.string.placeholder_search_favorites),
                                onSearchTextChanged = {
                                    viewModel.onTriggerEvent(
                                        FavoritesEvent.UpdateSearchQuery(
                                            it
                                        )
                                    )
                                },
                                onClearClick = { viewModel.onClearClick() },
                                onNavigateBack = {
                                    viewModel.onClearClick()
                                    showSearchMenu = false
                                },
                                onFilterActionClicked = { viewModel.onTriggerEvent(FavoritesEvent.OpenAlertDialog) }
                            )
                            AppbarBottomExpanderView()
                        }

                    }
                },
                content = { pageContent.invoke(it) }
            )
        }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun FavoritesScreenPreview() {
    MetanMobileTheme {
        Surface {
//            FavoritesBody() {
//
//            }
        }
    }
}