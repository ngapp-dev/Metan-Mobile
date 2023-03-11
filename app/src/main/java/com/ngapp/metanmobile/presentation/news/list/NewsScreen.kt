package com.ngapp.metanmobile.presentation.news.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.news.list.view.NewsContent
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/news/by/"
        )
    ]
)
@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()
    NewsBody(modifier, viewModel) { padding ->
        NewsPage(uiState, viewModel, padding, navigator, modifier)
    }
}

@Composable
private fun NewsPage(
    uiState: BaseViewState<*>,
    viewModel: NewsViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier,
) {
    when (uiState) {
        is BaseViewState.Data -> NewsContent(
            viewModel = viewModel,
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<NewsViewState>>().value,
            selectItem = { id -> navigator.openNewsDetail(id) }
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(NewsEvent.LoadNews)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(NewsEvent.LoadNews)
    })
}

@Composable
private fun NewsBody(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel,
    pageContent: @Composable (PaddingValues) -> Unit,
) {

    var showSearchMenu by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            if (!showSearchMenu) {
                MetanMobileToolbarWithFilterAndSearchButtons(
                    titleResId = R.string.toolbar_pinned_news_title,
                    elevation = 0.dp,
                    onSearchActionClicked = { showSearchMenu = true },
                    onFilterActionClicked = { viewModel.onTriggerEvent(NewsEvent.OpenAlertDialog) }
                )
            } else {
                MetanMobileToolbarWithFilterAndSearchField(
                    elevation = 0.dp,
                    searchText = viewModel.searchQuery,
                    placeholderText = stringResource(R.string.placeholder_search_news),
                    onSearchTextChanged = {
                        viewModel.onTriggerEvent(
                            NewsEvent.UpdateSearchQuery(
                                it
                            )
                        )
                    },
                    onClearClick = { viewModel.onClearClick() },
                    onNavigateBack = {
                        viewModel.onClearClick()
                        showSearchMenu = false
                    },
                    onFilterActionClicked = { viewModel.onTriggerEvent(NewsEvent.OpenAlertDialog) }
                )

            }
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun NewsScreenPreview() {
    MetanMobileTheme {
        Surface {
//            NewsBody() {
//
//            }
        }
    }
}