package com.ngapp.metanmobile.presentation.news.detail.view

import android.content.res.Configuration
import android.net.Uri
import android.webkit.URLUtil
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.ngapp.framework.extension.isNotNullOrBlank
import com.ngapp.jetframework.htmltext.HtmlText
import com.ngapp.metanmobile.app.extension.createInternetIntent
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.theme.cardBackgroundColor
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import timber.log.Timber

@Composable
fun NewsDetailBodyView(
    news: NewsDto
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MetanMobileColors.cardBackgroundColor),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val contentText =
                news.content?.substringBefore("<img") + news.content?.substringAfter(">")
            HtmlText(
                text = contentText,
                modifier = Modifier,
                linkClicked = { url ->
                    if (URLUtil.isValidUrl(url)) context.createInternetIntent(url)
                },
                flags = HtmlCompat.FROM_HTML_MODE_LEGACY,
                style = MetanMobileTypography.h4
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun NewsDetailBodyPreview() {
    MetanMobileTheme {
        Surface(color = MetanMobileColors.background) {
            NewsDetailBodyView(
                NewsDto.init()
            )
        }
    }
}