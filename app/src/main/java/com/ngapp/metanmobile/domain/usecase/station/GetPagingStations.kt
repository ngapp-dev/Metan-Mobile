package com.ngapp.metanmobile.domain.usecase.station

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ngapp.framework.usecase.FlowPagingUseCase
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.prof.rssparser.Parser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingStations @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: StationsRepository,
    @ApplicationContext context: Context,
) : FlowPagingUseCase<GetPagingStations.Params, StationDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>,
    )

    private val parser = Parser.Builder().context(context).build()

    override fun execute(params: Params): Flow<PagingData<StationDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = {
                StationPagingSource(
                    repository,
                    params.options,
                    parser,
                    URL_STATIONS
                )
            }
        ).flow
    }

    companion object {
        private const val URL_STATIONS: String = "https://metan.by/ecogas-map/rss/"
    }
}