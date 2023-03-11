package com.ngapp.metanmobile.presentation.news.detail

import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.app.tools.Analytics
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.domain.usecase.news.GetNewsDetail
import com.ngapp.metanmobile.domain.usecase.news.ShareNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getNewsDetail: GetNewsDetail,
    private val shareNews: ShareNews,
    private val analytics: Analytics
) : MviViewModel<BaseViewState<NewsDetailViewState>, NewsDetailEvent>() {

    override fun onTriggerEvent(eventType: NewsDetailEvent) {
        when (eventType) {
            is NewsDetailEvent.LoadDetail -> onLoadDetail(eventType.id)
            is NewsDetailEvent.ShareNews -> onShareNews()
        }
    }

    private val _shareNewsDto: MutableStateFlow<NewsDto?> = MutableStateFlow(null)
    var shareNewsDto: NewsDto? = _shareNewsDto.value

    private fun onShareNews() = safeLaunch {
        val params = ShareNews.Params(shareNewsDto)
        call(shareNews(params))
    }


    private fun onLoadDetail(id: Int) = safeLaunch {
        analytics.trackScreenView(
            screenName = "NewsDetail->onLoadDetail",
            className = "NewsDetailScreen"
        )
        setState(BaseViewState.Loading)
        val params = GetNewsDetail.Params(detailId = id)
        execute(getNewsDetail(params)) { dto ->
            setState(BaseViewState.Data(NewsDetailViewState(news = dto)))
        }
    }
}