package com.ngapp.metanmobile.domain.usecase.news

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.extension.orZero
import com.ngapp.framework.network.DataState
import com.ngapp.framework.network.apiCall
import com.ngapp.framework.usecase.DataStateUseCase
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetNewsDetail @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val newsRepo: NewsRepository,
//    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
//    internal val episodeRepo: EpisodeRepository,
) : DataStateUseCase<GetNewsDetail.Params, NewsDto>() {

    data class Params(
        val url: String? = null,
        val detailId: Int? = null,
    )

    override suspend fun FlowCollector<DataState<NewsDto>>.execute(params: Params) {
        val getNews =
            newsRepo.getNews(newsId = params.detailId.orZero())
//            if (params.detailId.isNull()) {
//                stationRepo.getStation(url = params.url.orEmpty()).toStationDto()
//            } else {
//                stationRepo.getStation(stationId = params.detailId.orZero()).toStationDto()
//            }
        val service = apiCall { getNews }

        emit(service)
    }
}