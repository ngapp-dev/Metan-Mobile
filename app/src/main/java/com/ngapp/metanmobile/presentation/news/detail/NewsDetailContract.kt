package com.ngapp.metanmobile.presentation.news.detail


import com.ngapp.metanmobile.data.model.dto.news.NewsDto

data class NewsDetailViewState(
    val news: NewsDto? = null
)

sealed class NewsDetailEvent {
    data class LoadDetail(val id: Int) : NewsDetailEvent()
    object ShareNews: NewsDetailEvent()
}