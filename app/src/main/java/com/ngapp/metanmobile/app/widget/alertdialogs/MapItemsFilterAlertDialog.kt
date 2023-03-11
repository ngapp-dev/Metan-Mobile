package com.ngapp.metanmobile.app.widget.alertdialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.parsing.StationType


@Composable
fun MapItemsFilterAlertDialog(
    show: Boolean,
    stationsItems: List<StationDto>?,
    onDismiss: () -> Unit,
    onConfirm: (List<StationDto>) -> Unit
) {
    var sortedStations by remember { mutableStateOf(stationsItems ?: emptyList()) }

    val cngCheckedState = remember { mutableStateOf(true) }
    val clfsCheckedState = remember { mutableStateOf(true) }

    if (show) {
        AlertDialog(
            modifier = Modifier.padding(12.dp),
            title = {
                Text(
                    text = stringResource(R.string.title_filter_stations),
                    style = MetanMobileTypography.h2
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.text_items_display),
                        style = MetanMobileTypography.h6
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = cngCheckedState.value,
                            onCheckedChange = { isChecked ->
                                cngCheckedState.value = isChecked
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Blue,
                                uncheckedColor = Blue,
                                checkmarkColor = White
                            ),
                        )
                        Text(
                            text = stringResource(R.string.text_agnks),
                            modifier = Modifier
                                .padding(horizontal = 4.dp),
                        )
                        Checkbox(
                            checked = clfsCheckedState.value,
                            onCheckedChange = { isChecked ->
                                clfsCheckedState.value = isChecked
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Blue,
                                uncheckedColor = Blue,
                                checkmarkColor = White
                            ),
                        )
                        Text(
                            text = stringResource(R.string.text_clfs),
                            modifier = Modifier
                                .padding(horizontal = 4.dp),
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        sortedStations = if (cngCheckedState.value) {
                            sortedStations + stationsItems?.filter { it.type == StationType.CNG.typeName }?.distinct()!!
                        } else {
                            sortedStations - stationsItems?.filter { it.type == StationType.CNG.typeName }!!
                                .toSet()
                        }
                        sortedStations = if (clfsCheckedState.value) {
                            sortedStations + stationsItems?.filter { it.type == StationType.CLFS.typeName }
                                ?.distinct()!!
                        } else {
                            sortedStations - stationsItems?.filter { it.type == StationType.CLFS.typeName }!!
                                .toSet()
                        }
                        onConfirm.invoke(sortedStations)
                    }
                )
                {
                    Text(text = stringResource(id = R.string.button_apply))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss)
                {
                    Text(text = stringResource(id = R.string.button_cancel))
                }
            },
            onDismissRequest = onDismiss
        )
    }
}