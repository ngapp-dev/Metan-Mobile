package com.ngapp.metanmobile.presentation.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.animation.doOnEnd
import androidx.fragment.app.FragmentActivity
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.ngapp.framework.extension.toast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.haveS
import com.ngapp.metanmobile.app.tools.MetanMobileAnalytics
import com.ngapp.metanmobile.provider.LanguageProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var languageProvider: LanguageProvider

    private var backPressed = 0L

    private val finish: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            toast(getString(R.string.app_exit_label))
        }
        backPressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MetanMobileAnalytics(applicationContext)
        MobileAds.initialize(this)

        // Optional if you want to add test device
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("DEVICE ID"))
            .build()
        MobileAds.setRequestConfiguration(configuration)

        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            languageProvider.setLocale(languageProvider.getLanguageCode(), LocalContext.current)
            MainRoot(finish = finish)
        }
    }
}