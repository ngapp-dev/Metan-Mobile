package com.ngapp.metanmobile.domain.usecase.news

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class ShareNews @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val newsRepo: NewsRepository
) : LocalUseCase<ShareNews.Params, Unit>() {

    data class Params(
        val newsDto: NewsDto?
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        newsRepo.shareNews(params.newsDto)
        emit(Unit)
    }
}