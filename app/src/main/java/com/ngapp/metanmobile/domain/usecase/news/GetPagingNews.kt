package com.ngapp.metanmobile.domain.usecase.news

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ngapp.framework.usecase.FlowPagingUseCase
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.prof.rssparser.Parser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingNews @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: NewsRepository,
    @ApplicationContext context: Context,
) : FlowPagingUseCase<GetPagingNews.Params, NewsDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>,
    )

    private val parser = Parser.Builder().context(context).build()

    override fun execute(params: Params): Flow<PagingData<NewsDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = {
                NewsPagingSource(
                    repository,
                    params.options,
                    parser,
                    URL_NEWS
                )
            }
        ).flow
    }

    companion object {
        private const val URL_NEWS: String = "https://metan.by/news/rss/"
    }
}