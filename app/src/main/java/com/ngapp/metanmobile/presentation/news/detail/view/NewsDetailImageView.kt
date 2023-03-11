package com.ngapp.metanmobile.presentation.news.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.data.model.dto.news.NewsDto

@Composable
fun NewsDetailImageView(news: NewsDto?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (12).dp)
            .height(150.dp)
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(news?.detailPicture)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.logo_one_solid),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
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
fun NewsDetailDetailImagePreview() {
    MetanMobileTheme {
        NewsDetailImageView(NewsDto.init())
    }
}