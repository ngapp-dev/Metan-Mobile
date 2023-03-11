package com.ngapp.metanmobile.presentation.news.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.formatUnixDataToString
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import java.util.*

@Composable
fun NewsDetailHeaderView(news: NewsDto) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MetanMobileColors.cardBackgroundColor),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = news.title,
                    modifier = Modifier.padding(top = 12.dp),
                    style = MetanMobileTypography.h2
                )

                NewsDetailDateAndStatusView(news)
                NewsDetailDescriptionView(news)

                MetanMobileDivider()
            }
        }
    }
}

@Composable
private fun NewsDetailDateAndStatusView(news: NewsDto) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = formatUnixDataToString(news.dateCreated),
                modifier = Modifier,
                style = MetanMobileTypography.h4,
                color = Gray400
            )
            NewsDetailStatusNewView(news = news)
        }
    }
}


@Composable
private fun NewsDetailStatusNewView(news: NewsDto) {
    val dateNow = Date()
    val diff = dateNow.time - news.dateCreated
    if ((diff / 86400000) >= 10) {
        NewsDetailStatusNewContentView()
    }
}

@Composable
private fun NewsDetailStatusNewContentView() {
//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .crossfade(true)
//            .build(),
//        error = painterResource(R.drawable.ic_badge_new),
//        contentDescription = null,
//        contentScale = ContentScale.FillHeight,
//        modifier = Modifier
//            .fillMaxHeight()
//    )
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
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 8.dp)

            )
        }
    }
}

@Composable
private fun NewsDetailDescriptionView(news: NewsDto) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = news.description ?: "",
            modifier = Modifier.padding(vertical = 12.dp),
            style = MetanMobileTypography.h4,
        )
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
fun NewsDetailHeaderPreview() {
    MetanMobileTheme {
        Surface(color = MetanMobileColors.background) {
            NewsDetailHeaderView(
                NewsDto.init()
            )
        }
    }
}