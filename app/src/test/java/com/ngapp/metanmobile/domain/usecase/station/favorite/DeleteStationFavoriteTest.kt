package com.ngapp.metanmobile.domain.usecase.station.favorite

import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteStationFavoriteTest : MockkUnitTest() {
    @MockK(relaxed = true)
    lateinit var repository: StationsRepository

    @SpyK
    @InjectMockKs
    private lateinit var deleteFavorite: DeleteStationFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        val params = DeleteStationFavorite.Params(id)
        deleteFavorite.invoke(params)

        // Assert (Then)
        coVerify { deleteFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val id = 1
        val params = DeleteStationFavorite.Params(id)

        // Act (When)
        deleteFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.deleteFavoriteById(id)
        }
    }
}