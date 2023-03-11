package com.ngapp.metanmobile.presentation.news.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.presentation.news.detail.NewsDetailViewState
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.cardBackgroundColor
import com.ngapp.metanmobile.presentation.news.detail.NewsDetailViewModel

@Composable
fun NewsDetailContent(
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel = hiltViewModel(),
    viewState: NewsDetailViewState,
    listState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val newsItem = viewState.news
    viewModel.shareNewsDto = newsItem

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MetanMobileColors.cardBackgroundColor)
    ) {
        LazyColumn(
            state = listState,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxSize()
        ) {
            newsItem?.let { news ->
                item("image") {
                    NewsDetailImageView(news = news)
                }
                item("title") {
                    NewsDetailHeaderView(news = news)
                }
                item("contentInfo") {
                    NewsDetailBodyView(news = news)
                }
            }
        }
    }

}