@file:JvmName("LangScreenKt")

package com.ngapp.metanmobile.presentation.settings.view.language

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.jetframework.SetLanguage
import com.ngapp.jetframework.clickableSingle
import com.ngapp.jetframework.setAppLocale
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.theme.Red700
import com.ngapp.metanmobile.app.theme.cardBackgroundColor
import com.ngapp.metanmobile.app.widget.MetanMobileToolbarWithNavIcon
import com.ngapp.metanmobile.presentation.settings.SettingsViewModel
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Destination
@Composable
fun LangScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    var langs by remember { viewModel.langs }
    val scope = rememberCoroutineScope()

    SetLanguage(langs.find { it.isSelected }?.code.toString())

    Scaffold(
        topBar = {
            MetanMobileToolbarWithNavIcon(
                R.string.toolbar_app_language_title,
                pressOnBack = {
                    navigator.navigateUp()
                },
                elevation = 0.dp
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .animateContentSize()
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .background(color = MetanMobileColors.cardBackgroundColor)
                .fillMaxWidth()
        ) {
            LazyColumn {
                items(items = langs) { lang ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(MetanMobileColors.cardBackgroundColor)
                            .clickableSingle {
                                scope.launch {
                                    viewModel.saveLanguageCode(lang.code)
                                }
                                langs = langs.map { dto ->
                                    if (lang.id == dto.id) {
                                        dto.copy(isSelected = true)
                                    } else {
                                        dto.copy(isSelected = false)
                                    }
                                }
                            }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = lang.name, style = MetanMobileTypography.subtitle1)
                        AnimatedVisibility(lang.isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Red700,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}