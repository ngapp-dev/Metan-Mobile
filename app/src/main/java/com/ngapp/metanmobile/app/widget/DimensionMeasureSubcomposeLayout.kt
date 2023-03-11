package com.ngapp.metanmobile.app.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize

@Composable
fun DimensionMeasureSubcomposeLayout(
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    dependentContent: @Composable (IntSize, Constraints) -> Unit
) {

    SubcomposeLayout(modifier = modifier) { constraints ->

        // Subcompose(compose only a section) main content and get Placeable
        val mainPlaceables: List<Placeable> = subcompose(SlotsEnum.Main, mainContent)
            .map {
                it.measure(constraints)
            }

        // Get max width and height of main component
        var maxWidth = 0
        var maxHeight = 0

        mainPlaceables.forEach { placeable: Placeable ->
            maxWidth += placeable.width
            maxHeight = placeable.height
        }

        val maxSize = IntSize(maxWidth, maxHeight)

        val dependentPlaceables = subcompose(SlotsEnum.Dependent) {
            dependentContent(maxSize, constraints)
        }.map {
            it.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            dependentPlaceables.forEach { placeable: Placeable ->
                placeable.placeRelative(0, 0)
            }
        }
    }
}

enum class SlotsEnum { Main, Dependent }