package com.ngapp.metanmobile.data.repository.station

import android.content.Context
import android.content.Intent
import androidx.annotation.VisibleForTesting
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationDtoList
import com.ngapp.metanmobile.data.model.local.station.StationEntity
import com.prof.rssparser.Parser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StationsRepository
@Inject
constructor(
//    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
//    internal val service: StationService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dao: StationFavoriteDao,
    @ApplicationContext val context: Context,
) {

    private val parser = Parser.Builder().context(context).build()

    suspend fun shareStation(stationDto: StationDto?) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TITLE, stationDto?.title + context.getString(R.string.text_share_via) + context.getString(
                    R.string.app_name))
            putExtra(
                Intent.EXTRA_TEXT, stationDto?.title +
                        "\n${stationDto?.address}" + "\n${stationDto?.url}"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(shareIntent)
    }

    suspend fun getStationList(): List<StationDto> {

        val channel = parser.getChannel(URL_STATIONS)

        val response = channel.articles
        val stationList: List<StationDto> = response.orEmpty().toStationDtoList()

        return stationList ?: listOf(StationDto.init())
    }

    suspend fun getStation(stationCode: String): StationDto {
        val channel = parser.getChannel(URL_STATIONS)

        val response = channel.articles
        val stationList = response.toStationDtoList()
        return stationList.find { it.code == stationCode } ?: StationDto.init()
    }

    suspend fun getFavoriteList() = dao.getFavoriteList()

    suspend fun getFavorite(favoriteId: Int) = dao.getFavorite(favoriteId)

    suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    suspend fun saveFavorite(entity: StationEntity) = dao.insert(entity)

    suspend fun saveFavoriteList(entityList: List<StationEntity>) = dao.insert(entityList)

    companion object {
        private const val URL_STATIONS: String = "https://metan.by/ecogas-map/rss/"
    }
}