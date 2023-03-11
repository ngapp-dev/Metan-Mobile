package com.ngapp.metanmobile.presentation.settings.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.SettingsViewModel


@Composable
fun SettingsBodyView(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    profile: ProfileDto?,
    themeCheckedState: MutableState<Boolean>,
    onUpdateProfile: (ProfileDto?) -> Unit = {},
    onFaqPageClick: () -> Unit,
    onContactsClick: () -> Unit,
    onCalculatorsPageClick: () -> Unit,
    onAppLanguagePageClick: () -> Unit,
    onAboutPageClick: () -> Unit,
    onLegalRegulationsPageClick: () -> Unit
) {
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
                    .padding(top = 12.dp)
            ) {
                ProfileItemPageRow(
                    profile = profile,
                    onProfileItemClick = onUpdateProfile
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItemPageRow(
                    title = R.string.text_faq,
                    onPageItemClick = onFaqPageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItemPageRow(
                    title = R.string.text_contacts,
                    onPageItemClick = onContactsClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItemPageRow(
                    title = R.string.text_calculators,
                    onPageItemClick = onCalculatorsPageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                ThemeModeItemPageRow(
                    title = R.string.text_theme_mode,
                    viewModel = viewModel,
                    themeCheckedState = themeCheckedState
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItemPageRow(
                    title = R.string.text_app_language,
                    onPageItemClick = onAppLanguagePageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItemPageRow(
                    title = R.string.text_about,
                    onPageItemClick = onAboutPageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                LegalRegulationsItemPageRow(
                    title = R.string.text_legal_regulations,
                    onPageItemClick = onLegalRegulationsPageClick
                )
            }
        }
    }
}