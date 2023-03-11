package com.ngapp.metanmobile.presentation.current

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.selectedBottomItemColor
import com.ngapp.metanmobile.app.theme.unselectedBottomItemColor
import com.ngapp.metanmobile.provider.NavigationProvider
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.ngapp.metanmobile.presentation.favorites.FavoritesScreen
import com.ngapp.metanmobile.presentation.home.HomeScreen
import com.ngapp.metanmobile.presentation.news.list.NewsScreen
import com.ngapp.metanmobile.presentation.stations.list.StationsScreen
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun CurrentScreen(navigator: NavigationProvider) {
    val scaffoldState = rememberScaffoldState()
    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable {
        mutableStateOf(BottomBarCurrentItem.HOME)
    }
    val stationsBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    Crossfade(currentBottomTab) { bottomTab ->
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { CurrentBottomNavigation(bottomTab, setCurrentBottomTab) },
            content = {
                val modifier = Modifier.padding(it)
                when (bottomTab) {
                    BottomBarCurrentItem.HOME -> HomeScreen(
                        modifier = modifier,
                        navigator = navigator
                    )
                    BottomBarCurrentItem.STATIONS -> StationsScreen(
                        modifier = modifier,
                        navigator = navigator
                    )
                    BottomBarCurrentItem.NEWS -> NewsScreen(
                        modifier = modifier,
                        navigator = navigator
                    )
                    BottomBarCurrentItem.FAVORITES -> FavoritesScreen(
                        modifier = modifier,
                        navigator = navigator,
                        stationsBottomSheetState = stationsBottomSheetState
                    )
                }
            }
        )
    }
}

@Composable
private fun CurrentBottomNavigation(
    bottomTab: BottomBarCurrentItem,
    setCurrentBottomTab: (BottomBarCurrentItem) -> Unit,
) {
    val bottomBarHeight = 56.dp
    val pages = BottomBarCurrentItem.values()

    BottomNavigation(
        backgroundColor = MetanMobileColors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsHeight(bottomBarHeight)
    ) {
        pages.forEach { page ->
            val selected = page == bottomTab
            val selectedLabelColor = if (selected) {
                selectedBottomItemColor
            } else {
                unselectedBottomItemColor
            }
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = page.icon),
                        contentDescription = stringResource(page.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(page.title),
                        color = selectedLabelColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                selected = selected,
                onClick = {
                    setCurrentBottomTab.invoke(page)
                },
                selectedContentColor = selectedBottomItemColor,
                unselectedContentColor = unselectedBottomItemColor,
                alwaysShowLabel = true,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}