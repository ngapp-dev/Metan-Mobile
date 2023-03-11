package com.ngapp.metanmobile.presentation.settings.view.profile.view

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.saveImage
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileEvent
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileViewModel
import timber.log.Timber

@Composable
fun ProfileHeaderView(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    profile: ProfileDto?,
) {
    val context = LocalContext.current

    var selectImages by remember { mutableStateOf(profile?.profileImage) }

    selectImages = profile?.profileImage

    val result = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        result.value = it
    }
    result.value?.let { image ->
        Image(image.asImageBitmap(), null, modifier = Modifier.fillMaxWidth())
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { selectedImageUri ->

            selectedImageUri?.let {
                val bitmapImage = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, selectedImageUri)
                } else {
                    val source =
                        ImageDecoder.createSource(context.contentResolver, selectedImageUri)
                    ImageDecoder.decodeBitmap(source)
                }
                val link = saveImage(context, bitmapImage, "avatar.jpg") + "/avatar.jpg"
                selectImages = link
                viewModel.onTriggerEvent(ProfileEvent.UpdateProfile(profile?.copy(profileImage = link)!!))
            }
        }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(132.dp),
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
                    .data(selectImages)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.logo_solid_mono_invert),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .clickable {
//                        launcher.launch(null)
                        galleryLauncher.launch("image/*")
                    }
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = (viewModel.firstName + " " + viewModel.lastName),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
            }
        }
    }
}