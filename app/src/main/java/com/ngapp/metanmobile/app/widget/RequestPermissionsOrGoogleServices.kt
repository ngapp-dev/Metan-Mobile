package com.ngapp.metanmobile.app.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.*

@Composable
internal fun RequestPermissionOrGoogleServices(
    modifier: Modifier = Modifier,
    titleText: Int,
    buttonText: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = stringResource(id = titleText),
                overflow = TextOverflow.Ellipsis,
                style = MetanMobileTypography.h6
            )
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = onClick,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
                shape = MetanMobileShapes.large,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MetanMobileColors.permissionButtonColor
                ),
            ) {
                Text(text = stringResource(id = buttonText))
            }
        }
    }
}