package com.ngapp.metanmobile.presentation.settings.view.legalregulations.termsandconditions

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/upload/metanmobile/termsandconditions.html",
        )
    ]
)
@Composable
fun TermsAndConditionsScreen(
    modifier: Modifier = Modifier,
    url: String = "https://metan.by/upload/metanmobile/termsandconditions.html",
    navigator: NavigationProvider,
) {
    TermsAndConditionsBody(modifier, navigator) { TermsAndConditionsPage(modifier, url) }
}

@Composable
private fun TermsAndConditionsPage(
    modifier: Modifier,
    url: String
) {
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null

    Card(
        modifier = modifier
            .animateContentSize()
            .padding(top = 5.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        )
    ) {
        AndroidView(
            modifier = modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                            backEnabled = view.canGoBack()
                        }
                    }
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true

                    loadUrl(url)
                    webView = this
                }
            }, update = {
                webView = it
            })

        BackHandler(enabled = backEnabled) {
            webView?.goBack()
        }
    }
}

@Composable
private fun TermsAndConditionsBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MetanMobileToolbarWithNavIcon(
                titleResId = R.string.toolbar_terms_and_conditions_title,
                pressOnBack = { navigator.navigateUp() },
                elevation = 0.dp,
            )
        },
        content = { pageContent.invoke(it) }
    )
}