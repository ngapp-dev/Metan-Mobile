package com.ngapp.metanmobile.domain.usecase.station.favorite

import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationEntity
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.metanmobile.domain.mockdata.MockData
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddStationFavoriteTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: StationsRepository

    @SpyK
    @InjectMockKs
    private lateinit var addFavorite: AddStationFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<StationDto>()

        // Act (When)
        val params = AddStationFavorite.Params(dto)
        addFavorite.invoke(params)

        // Assert (Then)
        coVerify { addFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val dto = MockData.getStationDto()
        val params = AddStationFavorite.Params(dto)

        // Act (When)
        addFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.saveFavorite(dto.toStationEntity())
        }
    }
}