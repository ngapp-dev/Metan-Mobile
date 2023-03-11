package com.ngapp.metanmobile.presentation.welcome.navgraph

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.google.firebase.analytics.FirebaseAnalytics
import com.ngapp.metanmobile.presentation.welcome.onboarding.OnBoardingScreen

@Composable
fun WelcomeNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WelcomeScreen.OnBoarding.route
    ) {
        composable(
            route = WelcomeScreen.OnBoarding.route
        ) {
            OnBoardingScreen()
        }
    }
}