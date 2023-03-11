package com.ngapp.metanmobile.app.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = DarkBlue,
    onPrimary = White,
    secondary = Blue,
    secondaryVariant = RedDark,
    onSecondary = Black,

    background = BackgroundDark,
    onBackground = BackgroundDark,

    surface = CardDark,
    onSurface = CardDark
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Red,
    onPrimary = Black,
    secondary = Blue,
    secondaryVariant = Red,
    onSecondary = Black,

    background = BackgroundLight,
    onBackground = BackgroundLight,

    surface = White,
    onSurface = White
)

val MetanMobileColors: Colors
    @Composable get() = MaterialTheme.colors

val MetanMobileShapes: Shapes
    @Composable get() = MaterialTheme.shapes

val MetanMobileTypography: Typography
    @Composable get() = MaterialTheme.typography

@Composable
fun MetanMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = if (darkTheme) {
        DarkTypography
    } else {
        LightTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}