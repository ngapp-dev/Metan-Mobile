package com.ngapp.metanmobile.domain.usecase.news

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.paging.PagingConfig
import com.ngapp.framework.network.DataState
import com.ngapp.framework.network.apiCall
import com.ngapp.framework.usecase.DataStateUseCase
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import kotlinx.coroutines.flow.FlowCollector
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType

class GetNews @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val newsRepo: NewsRepository
) : DataStateUseCase<Unit, List<NewsDto>>() {

    override suspend fun FlowCollector<DataState<List<NewsDto>>>.execute(params: Unit) {

        val getNewsList = newsRepo.getNewsList()

        val service = apiCall { getNewsList }
        emit(service)
    }
}