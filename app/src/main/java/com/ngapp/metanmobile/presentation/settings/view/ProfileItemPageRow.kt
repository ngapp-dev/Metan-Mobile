package com.ngapp.metanmobile.presentation.settings.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.Gray400
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileItemPageRow(
    modifier: Modifier = Modifier,
    profile: ProfileDto?,
    onProfileItemClick: (ProfileDto?) -> Unit = {},
) {
    Card(
        onClick = { onProfileItemClick.invoke(profile) },
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profile?.profileImage)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.logo_solid_mono_invert),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "${profile?.firstName ?: "User"} ${profile?.lastName ?: ""}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
                Text(
                    text = stringResource(R.string.text_view_profile),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h6
                )
            }
            Icon(
                painter = rememberVectorPainter(Icons.Default.KeyboardArrowRight),
                contentDescription = null,
                tint = Gray400,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

//@Preview(showBackground = true, name = "Light Mode")
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
//@Composable
//fun ProfileItemPageRowPreview() {
//    MetanMobileTheme {
//        ProfileItemPageRow(
//            title = R.string.text_faq,
//        )
//    }
//}