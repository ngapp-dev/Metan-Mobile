package com.ngapp.metanmobile.domain.usecase.station.favorite

import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetStationFavoritesTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: StationsRepository

    @SpyK
    @InjectMockKs
    private lateinit var getFavorites: GetStationFavorites

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getFavorites.invoke(Unit)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getFavorites.invoke(Unit).single()

        // Assert (Then)
        coVerify { repository.getFavoriteList() }
    }
}