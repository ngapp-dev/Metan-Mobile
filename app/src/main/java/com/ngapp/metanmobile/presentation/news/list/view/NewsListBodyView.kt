package com.ngapp.metanmobile.presentation.news.list.view

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.ads.ListBannersAds
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.presentation.news.list.NewsViewModel

@Composable
fun NewsListBodyView(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    sortedNews: List<NewsDto>,
    onDetailClick: (Int) -> Unit = {},
) {
    val searchedText = viewModel.searchQuery

    val newsResult = if (searchedText.isEmpty()) {
        sortedNews
    } else {
        val resultList = mutableListOf<NewsDto>()
        for (station in sortedNews) {
            if (station.title.contains(searchedText, true)) {
                resultList.add(station)
            }
        }
        resultList
    }
    if (newsResult.isNotEmpty()) {
        Card(
            modifier = Modifier
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
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 18.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.title_all_news),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .padding(bottom = 8.dp),
                            style = MetanMobileTypography.h1
                        )
                        Card(
                            modifier = Modifier
                                .wrapContentSize(),
                            elevation = 0.dp,
                            shape = Shapes.medium
                        ) {
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .background(color = Blue)
                                    .clip(Shapes.medium)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.text_new),
                                    style = MetanMobileTypography.h3,
                                    color = White,
                                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp)
                                )
                            }
                        }
                    }
                    newsResult.let { sortedNews ->
                        sortedNews.forEachIndexed { i, news ->
                            Column(modifier = Modifier.fillMaxWidth()) {
                                if (i % 8 == 0 && i != 0) {
                                    ListBannersAds()
                                    MetanMobileDivider(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp
                                        )
                                    )
                                }
                                NewsRow(
                                    news = news,
                                    onDetailClick = { onDetailClick.invoke(news.id.orZero()) }
                                )
                                if (i < sortedNews.size - 1) {
                                    MetanMobileDivider(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        ContentBottomExpanderView()
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
fun NewsListBodyViewPreview() {
    MetanMobileTheme {
        NewsListBodyView(sortedNews = emptyList())
    }
}