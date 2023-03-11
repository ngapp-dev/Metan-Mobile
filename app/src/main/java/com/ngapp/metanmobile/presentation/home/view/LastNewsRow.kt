package com.ngapp.metanmobile.presentation.home.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.formatUnixDataToString
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.data.model.dto.news.NewsDto

@Composable
fun LastNewsRow(
    newsDto: NewsDto,
    onDetailClick: () -> Unit = {},
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(vertical = 4.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(newsDto.previewPicture)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.logo_solid_mono_invert),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(end = 8.dp)
                        .clip(Shapes.medium)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 4.dp)
                ) {

                    Text(
                        text = newsDto.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        style = MetanMobileTypography.h6
                    )
                    Text(
                        text = formatUnixDataToString(newsDto.dateCreated),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Gray400,
                        style = MetanMobileTypography.h4
                    )
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
fun NewsRowPreview() {
    MetanMobileTheme() {
        LastNewsRow(newsDto = NewsDto.init())
    }
}