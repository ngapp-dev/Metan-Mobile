package com.ngapp.metanmobile.domain.usecase.station

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationDtoList
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.prof.rssparser.Parser
import java.io.IOException

class StationPagingSource(
    internal val repository: StationsRepository,
    internal val options: Map<String, String>,
    private val parser: Parser,
    private val url: String
) : PagingSource<Int, StationDto>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, StationDto>): Int? {
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

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StationDto> {
        // for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {

            val channel = parser.getChannel(url)

            val response = channel.articles
            val stationList = response.orEmpty().toStationDtoList()

            stationList.map {
                val stationFav = repository.getFavorite(it.id.orZero())
                it.isFavorite = stationFav != null
            }

            LoadResult.Page(
                data = stationList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (stationList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            // IOException for network failures.
            return LoadResult.Error(exception)
        }
    }
}