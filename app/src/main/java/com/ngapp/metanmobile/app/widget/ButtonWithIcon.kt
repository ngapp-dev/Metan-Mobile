package com.ngapp.metanmobile.app.widget

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.MetanMobileShapes
import com.ngapp.metanmobile.app.theme.MetanMobileTypography

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color = Color.Unspecified,
    @StringRes buttonText: Int,
    onClick: (isEnabled: Boolean) -> Unit = {},
    enable: Boolean = true,
    buttonBackgroundColor: Color,
    fontColor: Color,
    borderStrokeColor: Color,
) {
    Button(
        onClick = { onClick(enable) },
        modifier = modifier
            .fillMaxWidth()
            .shadow(0.dp)
            .clickable(enabled = false) { onClick(enable) },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = MetanMobileShapes.large,
        contentPadding = PaddingValues(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonBackgroundColor,
            contentColor = fontColor
        ),
        border = BorderStroke(1.dp, borderStrokeColor)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = icon,
                    modifier = Modifier.size(18.dp),
                    contentDescription = "drawable_icons",
                    tint = iconTint
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = buttonText),
                color = fontColor,
                textAlign = TextAlign.Center,
                style = MetanMobileTypography.h4,
            )
        }
    }
}