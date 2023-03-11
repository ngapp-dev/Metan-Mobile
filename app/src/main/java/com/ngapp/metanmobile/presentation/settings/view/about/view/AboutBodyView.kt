package com.ngapp.metanmobile.presentation.settings.view.about.view


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.ExtraSmallSpacer
import com.ngapp.metanmobile.app.component.MediumSpacer
import com.ngapp.metanmobile.app.component.SmallSpacer
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.theme.cardBackgroundColor
import com.ngapp.metanmobile.data.model.dto.githubuser.GithubUserDto


@Composable
fun AboutBodyView(
    modifier: Modifier = Modifier,
    githubUser: GithubUserDto
) {

    val uriHandler = LocalUriHandler.current

    Card(
        modifier = modifier
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
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(githubUser.avatarUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.logo_one_solid),
                    error = painterResource(R.drawable.logo_one_solid),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                )
                MediumSpacer()
                Text(
                    text = stringResource(R.string.text_developed_by),
                    style = MetanMobileTypography.h4,
                    textAlign = TextAlign.Center
                )
                ExtraSmallSpacer()
                Text(
                    text = githubUser.name.orEmpty(),
                    style = MetanMobileTypography.h6,
                    textAlign = TextAlign.Center
                )
                SmallSpacer()
                Text(
                    text = stringResource(R.string.text_android_developer),
                    style = MetanMobileTypography.h4,
                    textAlign = TextAlign.Center
                )
                ExtraSmallSpacer()
                ClickableText(
                    text = AnnotatedString(text = githubUser.htmlUrl),
                    style = MetanMobileTypography.h6,
                    onClick = { uriHandler.openUri(githubUser.htmlUrl) }
                )
            }
        }
    }
}
