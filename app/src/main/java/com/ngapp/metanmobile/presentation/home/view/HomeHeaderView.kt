package com.ngapp.metanmobile.presentation.home.view

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.DefaultSpacer
import com.ngapp.metanmobile.app.component.SmallSpacer
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.presentation.home.HomeViewModel
import com.ngapp.metanmobile.presentation.news.list.view.pinnedNews.PinnedNewsScreen

@Composable
fun HomeHeaderView(
    viewModel: HomeViewModel,
    lastNewsItems: List<NewsDto>,
    pinnedNews: List<NewsDto>,
    onShowAllNewsClick: () -> Unit = {},
    selectItem: (Int) -> Unit = {},
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
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .background(color = MetanMobileColors.cardBackgroundColor)
                .animateContentSize()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                PinnedNewsScreen(pinnedNews, selectItem)

                Text(
                    text = stringResource(id = R.string.button_show_all),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                        .clickable { onShowAllNewsClick.invoke() },
                    textAlign = TextAlign.End,
                    style = MetanMobileTypography.h4,
                    color = Gray400
                )
                DefaultSpacer()
                lastNewsItems.forEachIndexed { i, news ->
                    LastNewsRow(
                        newsDto = news,
                        onDetailClick = {
                            selectItem.invoke(news.id.orZero())
                        }
                    )
                    if (i < lastNewsItems.size - 1) {
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
fun HomeHeaderPreview() {
    MetanMobileTheme() {
//        HomeHeaderView(news = NewsDto.init())
    }
}