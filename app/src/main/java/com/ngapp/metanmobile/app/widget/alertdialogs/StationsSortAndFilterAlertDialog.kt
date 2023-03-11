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
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.parsing.StationType


@Composable
fun StationsSortAndFilterAlertDialog(
    show: Boolean,
    stationsItems: List<StationDto>?,
    currentLocation: LocationDto?,
    locationPermissionGranted: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (List<StationDto>) -> Unit
) {
    var sortedStations by remember { mutableStateOf(stationsItems ?: emptyList()) }

    val comparators = mutableListOf<Pair<Comparator<StationDto>, Comparator<StationDto>>>(
        Pair(titleComparator, titleComparatorDesc)
    )

    val sortTypes = mutableListOf(
        Pair(stringResource(R.string.button_name), comparators[0])
    )
    var comparator by remember { mutableStateOf(titleComparatorDesc) }
    val isDesc by remember { mutableStateOf(true) }

    if (currentLocation != null && locationPermissionGranted) {
        comparators.add(
            Pair(distanceComparator, distanceComparatorDesc)
        )
        sortTypes.add(
            Pair(stringResource(R.string.button_distance), comparators[1])
        )
    }
    val sortAscDesc = listOf(
        Pair(stringResource(R.string.button_descending), isDesc),
        Pair(stringResource(R.string.button_ascending), !isDesc)
    )
    val (selectedType, setSelectedType) = remember { mutableStateOf(sortTypes[0]) }
    val (selectedDesc, setSelectedDesc) = remember { mutableStateOf(sortAscDesc[0]) }

    val cngCheckedState = remember { mutableStateOf(true) }
    val clfsCheckedState = remember { mutableStateOf(true) }

    if (show) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(R.string.title_filter_and_sort),
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

                    Text(
                        text = stringResource(R.string.text_sort_by),
                        style = MetanMobileTypography.h6
                    )
                    SortTypesRadioGroup(
                        items = sortTypes,
                        selected = selectedType,
                        setSelected = setSelectedType
                    )
                    MetanMobileDivider()
                    SortAscDescRadioGroup(
                        items = sortAscDesc,
                        selected = selectedDesc,
                        setSelected = setSelectedDesc
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        sortedStations = if (cngCheckedState.value) {
                            val list = stationsItems?.filter { it.type == StationType.CNG.typeName }
                                ?: emptyList()
                            sortedStations.union(list).toList()
                        } else {
                            sortedStations - stationsItems?.filter { it.type == StationType.CNG.typeName }!!
                                .toSet()
                        }
                        sortedStations = if (clfsCheckedState.value) {
                            val list =
                                stationsItems?.filter { it.type == StationType.CLFS.typeName }
                                    ?: emptyList()
                            sortedStations.union(list).toList()
                        } else {
                            sortedStations - stationsItems?.filter { it.type == StationType.CLFS.typeName }!!
                                .toSet()
                        }
                        comparator =
                            if (selectedDesc.second) selectedType.second.second else selectedType.second.first
                        sortedStations = sortedStations.sortedWith(comparator)

                        onConfirm.invoke(sortedStations)
                    }
                )
                {
                    Text(text = stringResource(R.string.button_apply))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss)
                {
                    Text(text = stringResource(R.string.button_cancel))
                }
            },
            onDismissRequest = onDismiss
        )
    }
}

@Composable
private fun SortTypesRadioGroup(
    modifier: Modifier = Modifier,
    items: List<Pair<String, Pair<Comparator<StationDto>, Comparator<StationDto>>>>,
    selected: Pair<String, Pair<Comparator<StationDto>, Comparator<StationDto>>>,
    setSelected: (selected: Pair<String, Pair<Comparator<StationDto>, Comparator<StationDto>>>) -> Unit,
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
                    text = item.first,
                    style = MetanMobileTypography.subtitle1
                )
            }
        }
    }
}

@Composable
private fun SortAscDescRadioGroup(
    modifier: Modifier = Modifier,
    items: List<Pair<String, Boolean>>,
    selected: Pair<String, Boolean>,
    setSelected: (selected: Pair<String, Boolean>) -> Unit,
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
                    text = item.first,
                    style = MetanMobileTypography.subtitle1
                )
            }
        }
    }
}

private val titleComparator = Comparator<StationDto> { left, right ->
    left.title.compareTo(right?.title!!)
}

private val titleComparatorDesc = Comparator<StationDto> { left, right ->
    right.title.compareTo(left?.title!!)
}

private val distanceComparator = Comparator<StationDto> { left, right ->
    left.distanceBetween.compareTo(right.distanceBetween)
}

private val distanceComparatorDesc = Comparator<StationDto> { left, right ->
    right.distanceBetween.compareTo(left.distanceBetween)
}