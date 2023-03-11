package com.ngapp.metanmobile.presentation.news.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.domain.usecase.news.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNews
) : MviViewModel<BaseViewState<NewsViewState>, NewsEvent>() {

    override fun onTriggerEvent(eventType: NewsEvent) {
        when (eventType) {
            is NewsEvent.LoadNews -> onLoadNews()
            is NewsEvent.OpenAlertDialog -> onOpenAlertDialogClicked()
            is NewsEvent.ConfirmAlertDialog -> onAlertDialogConfirm()
            is NewsEvent.DismissAlertDialog -> onAlertDialogDismiss()
            is NewsEvent.UpdateSearchQuery -> onUpdateSearchQuery(eventType.input)
            else -> throw IllegalStateException("NewsViewModel")
        }
    }

    var sortedNews by mutableStateOf<List<NewsDto>>(emptyList())
    var searchQuery by mutableStateOf<String>("")


    private fun onUpdateSearchQuery(input: String) {
        searchQuery = input
    }

    fun onClearClick() {
        searchQuery = ""
    }

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private fun onOpenAlertDialogClicked() {
        _showDialog.value = true
    }

    private  fun onAlertDialogConfirm() {
        _showDialog.value = false
    }

    private fun onAlertDialogDismiss() {
        _showDialog.value = false
    }

    private fun onLoadNews() = safeLaunch {
        execute(getNews(Unit)) { newsListDto ->
            setState(BaseViewState.Data(NewsViewState(newsData = newsListDto)))
        }
    }
}