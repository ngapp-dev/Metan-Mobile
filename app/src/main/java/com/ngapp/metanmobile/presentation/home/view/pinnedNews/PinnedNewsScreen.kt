package com.ngapp.metanmobile.presentation.home.view.pinnedNews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.formatUnixDataToString
import com.ngapp.metanmobile.app.theme.BluePale
import com.ngapp.metanmobile.app.theme.GreenPale
import com.ngapp.metanmobile.app.theme.MetanMobileTypography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PinnedNewsScreen(
    pinnedNewsItems: List<NewsDto>,
    selectItem: (Int) -> Unit = {},
) {
    val pages = pinnedNewsItems
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = pages.count(),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            com.ngapp.metanmobile.presentation.news.list.view.pinnedNews.PagerScreen(
                pinnedNewsPage = pages[position],
                onDetailClick = selectItem
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState,
            activeColor = colorResource(R.color.c_blue)
        )
    }
}

@Composable
fun PagerScreen(
    pinnedNewsPage: NewsDto,
    onDetailClick: (Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = 12.dp,
                horizontal = 18.dp)
            .clip(RoundedCornerShape(size = 12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(BluePale, BluePale, GreenPale)
                )
            )
            .clickable {
                onDetailClick.invoke(pinnedNewsPage.id.orZero())
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = pinnedNewsPage.title.orEmpty(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                style = MetanMobileTypography.h6
            )
            Text(
                text = formatUnixDataToString(pinnedNewsPage.dateCreated),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                style = MetanMobileTypography.subtitle1
            )
        }
    }
}