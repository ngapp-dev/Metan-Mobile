package com.ngapp.metanmobile.presentation.stations.detail.view

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.SmallSpacer
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.presentation.news.list.view.NewsRow

@Composable
fun StationDetailRelatedNewsView(
    modifier: Modifier = Modifier,
    relatedNews: List<NewsDto>,
    onDetailClick: (Int) -> Unit = {},
) {
    if (relatedNews.isNotEmpty()) {
        Card(
            modifier = Modifier
                .animateContentSize()
                .padding(vertical = 12.dp),
            elevation = 1.dp,
            shape = MetanMobileShapes.large
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MetanMobileShapes.large)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 18.dp)
                ) {
                    Text(
                        text = stringResource(R.string.title_related_news),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp),
                        style = MetanMobileTypography.h1
                    )

                    relatedNews.let { sortedNews ->
                        sortedNews.forEachIndexed { i, news ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column() {
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
                                    } else {
                                        SmallSpacer()
                                    }
                                }
                            }
                        }
                    }
                }
            }
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
fun NewsListBodyViewPreview() {
    MetanMobileTheme {
        StationDetailRelatedNewsView(relatedNews = emptyList())
    }
}