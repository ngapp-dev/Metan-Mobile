package com.ngapp.metanmobile.presentation.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.ngapp.metanmobile.app.extension.haveS
import com.ngapp.metanmobile.presentation.main.MainActivity
import com.ngapp.metanmobile.presentation.welcome.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class StartActivity : FragmentActivity() {

    private val viewModel by viewModels<StartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (haveS()) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }


        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.startWelcome.collectLatest {
                delay(800)
                if (it) navigateWelcomeActivity() else navigateMainActivity()
            }
        }
    }

    private fun navigateMainActivity() {
        val intent = Intent(this@StartActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateWelcomeActivity() {
        val intent = Intent(this@StartActivity, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}