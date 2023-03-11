package com.ngapp.metanmobile.app.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.theme.Blue
import com.ngapp.metanmobile.app.theme.Gray400
import com.ngapp.metanmobile.app.theme.Red700

@Composable
fun CarTypesRadioGroup(
    items: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == item,
                    onClick = {
                        setSelected(item)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Red700,
                        unselectedColor = Blue,
                        disabledColor = Gray400
                    )
                )
                Text(
                    text = item,
                    style = MetanMobileTypography.subtitle1
                )
            }
        }
    }
}