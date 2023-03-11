package com.ngapp.metanmobile.app.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val Transparent = Color(0x00000000)
val Blue = Color(0xFF009CDE)
val BluePale = Color(0x16009cde)
val DarkBlue = Color(0xFF252941)
val BlueDark = Color(0xFF05060B)
val Red = Color(0xFFEB5757)
val RedDark = Color(0xFF982626)
val Green = Color(0xFF09af84)
val GreenPale = Color(0x1600af85)

val Gray25 = Color(0xFFF8F8F8)
val Gray50 = Color(0xFFF1F1F1)
val Gray75 = Color(0xFFECECEC)
val Gray100 = Color(0xFFE1E1E1)
val Gray200 = Color(0xFFEEEEEE)
val Gray300 = Color(0xFFACACAC)
val Gray400 = Color(0xFF919191)
val Gray500 = Color(0xFF6E6E6E)
val Gray600 = Color(0xFF535353)
val Gray700 = Color(0xFF616161)
val Gray800 = Color(0xFF292929)
val Gray900 = Color(0xFF212121)
val Gray950 = Color(0xFF141414)

//val CardDark = Color(0xFF3B3E43)
val CardDark = DarkBlue
val CardLight = White

val SmallWidgetLight = Gray50

val BackgroundLight = Color(0xFFF5F2F5)
val BackgroundDark = Color(0xFF24292E)

val DividerLight = Color(0xFFE0E0E0)
val DividerDark = Color(0xFF6E6E6E)

val GrayCircle = Color(0xFF919191)
val RedCircle = Color(0xFFD50000)
val GreenCircle = Color(0xFF00C853)
val BorderLine = Color(0xFFE5E5EA)

val Red700 = Color(0xFFD32F2F)



val selectedBottomItemColor = Blue
val unselectedBottomItemColor = Gray500

val toolbarIconDark = White
val toolbarIconLight = Gray500

val textColorLight = White
val textColorDark = Black

val permissionButtonLight = Gray200
val permissionButtonDark = Gray800

val Colors.textColor: Color
    @Composable get() = if (isLight) textColorDark else textColorLight

val Colors.textColorInverted: Color
    @Composable get() = if (!isLight) textColorDark else textColorLight

val Colors.toolbarIconColor: Color
    @Composable get() = if (isLight) toolbarIconLight else toolbarIconDark

val Colors.dividerColor: Color
    @Composable get() = if (isLight) DividerLight else DividerDark

val Colors.backgroundColor: Color
    @Composable get() = if (isLight) BackgroundLight else BackgroundDark

val Colors.cardBackgroundColor: Color
    @Composable get() = if (isLight) CardLight else CardDark

val Colors.smallWidgetBackgroundColor: Color
    @Composable get() = if (isLight) SmallWidgetLight else MaterialTheme.colors.onSurface

val Colors.permissionButtonColor: Color
    @Composable get() = if (isLight) permissionButtonLight else permissionButtonDark