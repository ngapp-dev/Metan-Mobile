package com.ngapp.metanmobile.presentation.welcome

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.ngapp.jetframework.SetupSystemUi
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.presentation.welcome.navgraph.WelcomeNavGraph
import com.ngapp.metanmobile.provider.LanguageProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeActivity : FragmentActivity() {
    @Inject
    lateinit var languageProvider: LanguageProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            languageProvider.setLocale(languageProvider.getLanguageCode(), LocalContext.current)
            WelcomeRoot()
        }
    }
}

@Composable
private fun WelcomeRoot() {
    MetanMobileTheme {
        SetupSystemUi(rememberSystemUiController(), MetanMobileColors.primary)
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MetanMobileColors.background
        ) {
            WelcomeNavGraph()
        }
    }
}