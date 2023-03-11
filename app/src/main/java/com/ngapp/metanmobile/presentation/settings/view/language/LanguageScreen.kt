package com.ngapp.metanmobile.presentation.settings.view.language

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.jetframework.SetLanguage
import com.ngapp.jetframework.setAppLocale
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.MetanMobileToolbarWithNavIcon
import com.ngapp.metanmobile.presentation.settings.SettingsViewModel
import com.ngapp.metanmobile.presentation.settings.view.language.view.LanguageContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    viewModel: SettingsViewModel = hiltViewModel(),
) {

    val langs = remember { viewModel.langs }

    SetLanguage(langs.value.find { it.isSelected }?.code.toString())

    LanguageBody(modifier, navigator) { padding ->
        LanguageContent(
            paddingValues = padding,
            langs = langs,
        )
    }
}

@Composable
private fun LanguageBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MetanMobileToolbarWithNavIcon(
                titleResId = R.string.toolbar_app_language_title,
                pressOnBack = {
                    navigator.navigateUp()
                },
                elevation = 0.dp,
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun LanguageScreen() {
    MetanMobileTheme {
        Surface {
//            LanguageBody {
//            }
        }
    }
}