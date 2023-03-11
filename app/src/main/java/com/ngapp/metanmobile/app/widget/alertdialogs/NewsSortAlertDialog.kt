package com.ngapp.metanmobile.app.widget.alertdialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.parsing.StationType


@Composable
fun NewsSortAlertDialog(
    show: Boolean,
    newsItems: List<NewsDto>?,
    onDismiss: () -> Unit,
    onConfirm: (List<NewsDto>) -> Unit
) {
    var sortedNews by remember { mutableStateOf(newsItems ?: emptyList()) }

    val comparators = mutableListOf<Pair<Comparator<NewsDto>, Comparator<NewsDto>>>(
        Pair(titleComparator, titleComparatorDesc),
        Pair(dateComparator, dateComparatorDesc),
    )

    val sortTypes = mutableListOf(
        Pair(stringResource(R.string.button_name), comparators[0]),
        Pair(stringResource(R.string.button_date), comparators[1])
    )
    var comparator by remember { mutableStateOf(dateComparatorDesc) }
    val isDesc by remember { mutableStateOf(true) }

    val sortAscDesc = listOf(
        Pair(stringResource(R.string.button_descending), isDesc),
        Pair(stringResource(R.string.button_ascending), !isDesc)
    )
    val (selectedType, setSelectedType) = remember { mutableStateOf(sortTypes[1]) }
    val (selectedDesc, setSelectedDesc) = remember { mutableStateOf(sortAscDesc[0]) }


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
                        comparator =
                            if (selectedDesc.second) selectedType.second.second else selectedType.second.first
                        sortedNews = sortedNews.sortedWith(comparator)

                        onConfirm.invoke(sortedNews)
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
    items: List<Pair<String, Pair<Comparator<NewsDto>, Comparator<NewsDto>>>>,
    selected: Pair<String, Pair<Comparator<NewsDto>, Comparator<NewsDto>>>,
    setSelected: (selected: Pair<String, Pair<Comparator<NewsDto>, Comparator<NewsDto>>>) -> Unit,
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

private val titleComparator = Comparator<NewsDto> { left, right ->
    left.title.compareTo(right?.title!!)
}

private val titleComparatorDesc = Comparator<NewsDto> { left, right ->
    right.title.compareTo(left?.title!!)
}

private val dateComparator = Comparator<NewsDto> { left, right ->
    left.dateCreated.compareTo(right?.dateCreated!!)
}

private val dateComparatorDesc = Comparator<NewsDto> { left, right ->
    right.dateCreated.compareTo(left?.dateCreated!!)
}