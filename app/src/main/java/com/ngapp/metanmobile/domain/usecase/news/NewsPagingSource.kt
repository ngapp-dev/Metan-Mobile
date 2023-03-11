package com.ngapp.metanmobile.domain.usecase.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.news.toNewsDtoList
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.prof.rssparser.Parser
import java.io.IOException

class NewsPagingSource(
    internal val repository: NewsRepository,
    internal val options: Map<String, String>,
    private val parser: Parser,
    private val url: String
) : PagingSource<Int, NewsDto>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, NewsDto>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsDto> {
        // for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {

            val channel = parser.getChannel(url)

            val response = channel.articles
            val newsList = response.toNewsDtoList()

            LoadResult.Page(
                data = newsList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (newsList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            // IOException for network failures.
            return LoadResult.Error(exception)
        }
    }
}