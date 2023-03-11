package com.ngapp.metanmobile.presentation.settings.view.language.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.ngapp.jetframework.SetLanguage
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.ads.ListBannersAds
import com.ngapp.metanmobile.app.component.SmallSpacer
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.language.LanguageDto
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.SettingsViewModel
import kotlinx.coroutines.launch


@Composable
fun LanguageBodyView(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    langs: MutableState<List<LanguageDto>>,
    onLanguageChange: (MutableState<List<LanguageDto>>) -> Unit = {},
) {

    val scope = rememberCoroutineScope()

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
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 6.dp)
            ) {
                langs.value.forEachIndexed { i, languageDto ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            LanguageItemRow(
                                language = languageDto,
                                onLanguageItemClick = {
                                    scope.launch {
                                        viewModel.saveLanguageCode(languageDto.code)
                                    }
                                    langs.value = langs.value.map { dto ->
                                        if (languageDto.id == dto.id) {
                                            dto.copy(isSelected = true)
                                        } else {
                                            dto.copy(isSelected = false)
                                        }
                                    }
                                    onLanguageChange.invoke(langs)
                                }
                            )

                            if (i < langs.value.size - 1) {
                                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                            } else {
                                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp),
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(
                                        bottomStart = 0.dp,
                                        bottomEnd = 0.dp,
                                        topStart = 0.dp,
                                        topEnd = 0.dp
                                    )
                                ) {
                                    AndroidView(
                                        modifier = Modifier.fillMaxWidth(),
                                        factory = { context ->
                                            AdView(context).apply {
                                                setAdSize(AdSize.BANNER)
                                                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                                                loadAd(AdRequest.Builder().build())
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}