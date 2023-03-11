package com.ngapp.metanmobile.presentation.settings.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.widget.ThemeSwitch
import com.ngapp.metanmobile.presentation.settings.SettingsViewModel

@Composable
fun ThemeModeItemPageRow(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    viewModel: SettingsViewModel = hiltViewModel(),
    themeCheckedState: MutableState<Boolean>
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
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
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = title),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp),
                style = MetanMobileTypography.h6
            )
            AndroidView(
                factory = { context ->
                    ThemeSwitch(context).apply {
                        isChecked = themeCheckedState.value
                        setOnCheckedChangeListener { _, isChecked ->
                            themeCheckedState.value = isChecked
                            viewModel.saveThemeMode(isChecked)
                        }
                    }
                }
            )
        }
    }
}