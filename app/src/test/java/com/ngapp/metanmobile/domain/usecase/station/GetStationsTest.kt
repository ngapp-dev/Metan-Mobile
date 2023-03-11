package com.ngapp.metanmobile.domain.usecase.station

import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetStationsTest : MockkUnitTest() {
    @MockK(relaxed = true)
    lateinit var stationsRepo: StationsRepository

    @MockK(relaxed = true)
    lateinit var locationRepo: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var getStations: GetStations

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)


        // Act (When)
        getStations.invoke(Unit)

        // Assert (Then)
        coVerify {
            stationsRepo.getStationList()
        }
    }
}