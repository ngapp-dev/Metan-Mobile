package com.ngapp.metanmobile.domain.usecase.station.favorite

import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationEntity
import com.ngapp.metanmobile.data.model.local.station.StationEntity
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.metanmobile.domain.mockdata.MockData
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateStationFavoriteTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: StationsRepository

    @SpyK
    @MockK
    lateinit var entity: StationEntity

    @SpyK
    @InjectMockKs
    private lateinit var updateFavorite: UpdateStationFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<StationDto>()

        // Act (When)
        val params = UpdateStationFavorite.Params(dto)
        updateFavorite.invoke(params)

        // Assert (Then)
        coVerify { updateFavorite.invoke(any()) }
    }

    @Test
    fun collectExecuteSave() = runTest {
        // Arrange (Given)
        val dto = MockData.getStationDto()
        val params = UpdateStationFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns null

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.saveFavorite(dto.toStationEntity()) }
    }

    @Test
    fun collectExecuteDelete() = runTest {
        // Arrange (Given)
        val dto = MockData.getStationDto()
        val params = UpdateStationFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns entity

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.deleteFavoriteById(dto.id.orZero()) }
    }
}