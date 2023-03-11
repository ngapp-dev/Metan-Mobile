package com.ngapp.metanmobile.presentation.current

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.ngapp.metanmobile.R

enum class BottomBarCurrentItem(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(
        title = R.string.bottom_menu_home,
        icon = Icons.Filled.Home
    ),
    STATIONS(
        title = R.string.bottom_menu_stations,
        icon = Icons.Filled.Map
    ),
    NEWS(
        title = R.string.bottom_menu_news,
        icon = Icons.Filled.LibraryBooks
    ),
    FAVORITES(
        title = R.string.bottom_menu_favorites,
        icon = Icons.Filled.Favorite
    )
}