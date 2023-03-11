package com.ngapp.metanmobile.presentation.news.list.view

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.presentation.news.list.view.pinnedNews.PinnedNewsScreen

@Composable
fun PinnedNewsHeaderView(
    modifier: Modifier = Modifier,
    pinnedNews: List<NewsDto>,
    selectItem: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(bottom = 5.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(
            bottomStart = 12.dp,
            bottomEnd = 12.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MetanMobileColors.cardBackgroundColor)
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                PinnedNewsScreen(pinnedNews, selectItem)
            }
        }
    }
}