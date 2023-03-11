package com.ngapp.metanmobile.presentation.news.list

import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent

data class NewsViewState(
    val newsData: List<NewsDto> = emptyList(),
    val favorList: List<NewsDto> = emptyList()
)

sealed class NewsEvent {
    object LoadNews : NewsEvent()
    data class AddOrRemoveFavorite(val newsDto: NewsDto) : NewsEvent()
    object LoadFavorites : NewsEvent()
    data class DeleteFavorite(val id: Int) : NewsEvent()
    object OpenAlertDialog: NewsEvent()
    object ConfirmAlertDialog: NewsEvent()
    object DismissAlertDialog: NewsEvent()
    data class UpdateSearchQuery(val input: String) : NewsEvent()
}