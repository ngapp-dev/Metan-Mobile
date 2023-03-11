package com.ngapp.metanmobile.presentation.news.list.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.widget.alertdialogs.NewsSortAlertDialog
import com.ngapp.metanmobile.presentation.news.list.NewsEvent
import com.ngapp.metanmobile.presentation.news.list.NewsViewModel
import com.ngapp.metanmobile.presentation.news.list.NewsViewState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsContent(
    viewModel: NewsViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    viewState: NewsViewState,
    selectItem: (Int) -> Unit = {}
) {
    val newsItems = viewState.newsData
    val pinnedNewsItems = viewState.newsData.filter { it.isPinned == 1 }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var sortedNews by remember { mutableStateOf(newsItems.sortedByDescending { it.dateCreated }) }

    val showDialogState: Boolean by viewModel.showDialog.collectAsState()

    viewModel.sortedNews = sortedNews

    NewsSortAlertDialog(
        show = showDialogState,
        newsItems = newsItems,
        onDismiss = { viewModel.onTriggerEvent(NewsEvent.DismissAlertDialog) },
        onConfirm = { selected ->
            viewModel.onTriggerEvent(NewsEvent.ConfirmAlertDialog)
            sortedNews = selected
            coroutineScope.launch {
                listState.animateScrollToItem(index = 0)
            }
        }
    )

    LazyColumn(
        state = listState,
        contentPadding = paddingValues,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item("contentHeader") {
            PinnedNewsHeaderView(
                modifier = Modifier.padding(bottom = 8.dp),
                pinnedNews = pinnedNewsItems,
                selectItem = { selectItem(it) }
            )
        }
        item {
            NewsListBodyView(
                modifier = Modifier
                    .fillParentMaxSize()
                    .animateItemPlacement(),
                sortedNews = sortedNews,
                onDetailClick = selectItem,
            )
        }
    }
}