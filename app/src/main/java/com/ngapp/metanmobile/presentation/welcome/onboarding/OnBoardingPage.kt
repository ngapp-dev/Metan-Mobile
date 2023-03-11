package com.ngapp.metanmobile.presentation.welcome.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ngapp.metanmobile.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    object First : OnBoardingPage(
        image = R.drawable.logo_one_solid,
        title = R.string.onboarding_title_news,
        description = R.string.onboarding_description_news
    )

    object Second : OnBoardingPage(
        image = R.drawable.logo_one_solid,
        title = R.string.onboarding_title_stations,
        description = R.string.onboarding_description_stations
    )

    object Third : OnBoardingPage(
        image = R.drawable.logo_one_solid,
        title = R.string.onboarding_title_favorites,
        description = R.string.onboarding_description_favorites
    )
}