package com.ngapp.metanmobile.presentation.settings.view.faq

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.settings.view.faq.view.FaqContent
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.EmptyView
import com.ngapp.metanmobile.app.widget.ErrorView
import com.ngapp.metanmobile.app.widget.LoadingView
import com.ngapp.metanmobile.app.widget.MetanMobileToolbarWithNavIcon
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun FaqScreen(
    modifier: Modifier = Modifier,
    viewModel: FaqViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()
    FaqBody(modifier, navigator) { padding ->
        FaqPage(uiState, viewModel, padding, navigator, modifier)
    }
}

@Composable
private fun FaqPage(
    userUiState: BaseViewState<*>,
    viewModel: FaqViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier,
) {
    when (userUiState) {
        is BaseViewState.Data ->
            FaqContent(
                paddingValues = paddings,
                viewState = userUiState.cast<BaseViewState.Data<FaqViewState>>().value
            )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = userUiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(FaqEvent.LoadFaq)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(FaqEvent.LoadFaq)
    })
}

@Composable
private fun FaqBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MetanMobileToolbarWithNavIcon(
                titleResId = R.string.toolbar_faq_title,
                pressOnBack = {
                    navigator.navigateUp()
                },
                elevation = 0.dp,
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun FaqScreenPreview() {
    MetanMobileTheme {
        Surface {
//            NewsDetailBody {
//            }
        }
    }
}