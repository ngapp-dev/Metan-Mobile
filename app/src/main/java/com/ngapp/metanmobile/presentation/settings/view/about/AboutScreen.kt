package com.ngapp.metanmobile.presentation.settings.view.about

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.ExtraSmallSpacer
import com.ngapp.metanmobile.app.component.MediumSpacer
import com.ngapp.metanmobile.app.component.SmallSpacer
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.EmptyView
import com.ngapp.metanmobile.app.widget.ErrorView
import com.ngapp.metanmobile.app.widget.LoadingView
import com.ngapp.metanmobile.app.widget.MetanMobileToolbarWithNavIcon
import com.ngapp.metanmobile.presentation.settings.view.about.view.AboutContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AboutScreen(
    viewModel: AboutViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()

    AboutBody(pressOnBack = { navigator.navigateUp() })
    {
        when (uiState) {
            is BaseViewState.Data -> AboutContent(
                data = uiState.cast<BaseViewState.Data<AboutViewState>>().value
            )
            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(AboutEvent.LoadGithubUser)
                }
            )
            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(AboutEvent.LoadGithubUser)
    })

}

@Composable
private fun AboutBody(
    pressOnBack: () -> Unit = {},
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MetanMobileToolbarWithNavIcon(
                R.string.toolbar_about_title,
                pressOnBack = { pressOnBack.invoke() },
                elevation = 0.dp
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun AboutScreenPreview() {
    MetanMobileTheme {
        Surface {
            AboutBody {
            }
        }
    }
}