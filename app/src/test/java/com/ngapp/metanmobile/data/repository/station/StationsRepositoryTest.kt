package com.ngapp.metanmobile.data.repository.station

import android.content.Context
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.metanmobile.data.local.mockdata.LocalMockData
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationDtoList
import com.ngapp.metanmobile.data.model.local.station.StationEntity
import com.ngapp.testutils.MockkUnitTest
import com.prof.rssparser.Parser
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class StationsRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var dao: StationFavoriteDao

    @MockK(relaxed = true)
    lateinit var context: Context

    private lateinit var repository: StationsRepository

    override fun onCreate() {
        super.onCreate()
        repository = StationsRepository(dao, context)
    }

    @Test
    fun getStationList() = runTest {
        // Given
        val parser = Parser.Builder().context(context).build()
        val channel = parser.getChannel("https://metan.by/ecogas-map/rss/")

        // When
        repository.getStationList()

        // Then
        coVerify {
            val response = channel.articles
            val stationList = response.toStationDtoList()
        }

    }

    @Test
    fun getStation() = runTest {
        // Given
        val parser = Parser.Builder().context(context).build()
        val channel = parser.getChannel("https://metan.by/ecogas-map/rss/")

        val stationCode = "agnks_grodno2"
        val code = slot<String>()

        // When
        repository.getStation(
            stationCode = stationCode
        )

        // Then
        coVerify {
            val response = channel.articles
            val stationList = response.toStationDtoList()
            stationList.find { it.code == stationCode } ?: StationDto.init()
        }

        Assert.assertEquals(stationCode, code.captured)
    }

    @Test
    fun getFavoriteList() = runTest {
        repository.getFavoriteList()

        coVerify { dao.getFavoriteList() }
    }

    @Test
    fun getFavorite() = runTest {
        val idToFind = 1
        val idCaptor = slot<Int>()

        repository.getFavorite(idToFind)

        coVerify { dao.getFavorite(capture(idCaptor)) }

        Assert.assertEquals(idToFind, idCaptor.captured)
    }

    @Test
    fun deleteFavoriteById() = runTest {
        val idToDelete = 1
        val idCaptor = slot<Int>()
        repository.deleteFavoriteById(idToDelete)

        coVerify {
            dao.deleteFavoriteById(capture(idCaptor))
        }
        Assert.assertEquals(idToDelete, idCaptor.captured)
    }

    @Test
    fun saveFavorite() = runTest {
        val favoriteToInsert = StationEntity(
            id = 0,
            code = "agnks_grodno1",
            previewPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg",
            detailPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg",
            isActive = 1,
            isOperate = 1,
            type = "АГНКС",
            address = "г. Гродно, пр-т. Космонавтов, 87",
            region = "Гродненская область",
            phone = "+375 (152) 55-50-07, +375 (152) 55-50-08",
            service = "заправка КПГ",
            workingTime = "круглосуточно, без выходных,<br>\n" +
                    "перерывы: 09:00-09:30, 13:50-14:05, 21:00-21:30, 01:50-02:05",
            payment = "наличный расчет, по заключенным договорам, банковские карточки",
            latitude = "53.671096",
            longitude = "23.882400",
            busyOnMonday = "7,4,2,1,3,11,13,16,12,15,11,14,17,16,14,21,16,20,17,16,14,9,5,3",
            busyOnTuesday = "4,3,1,5,7,8,7,11,12,15,11,13,12,17,18,23,22,12,25,14,10,10,7,8",
            busyOnWednesday = "3,9,5,1,4,5,16,9,8,17,16,11,13,10,19,29,27,11,23,15,7,21,5,3",
            busyOnThursday = "8,6,1,4,2,14,12,16,14,20,16,17,10,21,13,14,16,23,22,10,11,13,13,7",
            busyOnFriday = "6,8,7,5,4,13,13,13,14,21,14,11,7,14,16,24,19,13,29,24,12,18,7,5",
            busyOnSaturday = "7,8,6,10,9,14,20,10,10,12,6,15,15,19,14,17,19,21,24,23,15,11,5,6",
            busyOnSunday = "10,5,12,9,6,17,12,9,7,13,8,7,8,14,12,17,21,26,17,9,7,15,4,13",
            googleTag = "AGNKS-Grodno",
            googleMapsTag = "16671412901506656138",
            yandexTag = "1028225046",
            title = "АГНКС Гродно-1",
            dateCreated = System.currentTimeMillis(),
            url = "https://metan.by/ecogas-map/agnks_grodno1/"
        )

        val entityCaptor = slot<StationEntity>()
        repository.saveFavorite(favoriteToInsert)

        coVerify { dao.insert(capture(entityCaptor)) }
        Assert.assertEquals(favoriteToInsert, entityCaptor.captured)
    }

    @Test
    fun saveFavoriteList() = runTest {
        val favoritesToInsert = LocalMockData.getStationFavoriteList()
        val entityCaptor = slot<List<StationEntity>>()
        repository.saveFavoriteList(favoritesToInsert)

        coVerify { dao.insert(capture(entityCaptor)) }
        Assert.assertEquals(favoritesToInsert, entityCaptor.captured)
    }
}