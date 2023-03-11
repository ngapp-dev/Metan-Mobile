package com.ngapp.metanmobile.presentation.news.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.news.detail.view.NewsDetailContent
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/news/by/{id}/"
        )
    ]
)
@Composable
fun NewsDetailScreen(
    id: Int,
    viewModel: NewsDetailViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()

    NewsDetailBody(
        pressOnBack = { navigator.navigateUp() },
        viewModel = viewModel
    ) {
        when (uiState) {
            is BaseViewState.Data -> NewsDetailContent(
                viewState = uiState.cast<BaseViewState.Data<NewsDetailViewState>>().value
            )
            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(NewsDetailEvent.LoadDetail(id))
                }
            )
            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(NewsDetailEvent.LoadDetail(id))
    })
}

@Composable
private fun NewsDetailBody(
    pressOnBack: () -> Unit = {},
    viewModel: NewsDetailViewModel,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            MetanMobileToolbarWithNavIconAndShareButton(
                pressOnBack = pressOnBack,
                elevation = 0.dp,
                onShareActionClicked = { viewModel.onTriggerEvent(NewsDetailEvent.ShareNews) }
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun NewsDetailScreenPreview() {
    MetanMobileTheme {
        Surface {
//            NewsDetailBody {
//            }
        }
    }
}