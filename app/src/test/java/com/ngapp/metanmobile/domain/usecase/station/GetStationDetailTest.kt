package com.ngapp.metanmobile.domain.usecase.station

import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetStationDetailTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var stationsRepo: StationsRepository

    @RelaxedMockK
    lateinit var locationRepo: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var getStationDetail: GetStationDetail

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val stationCode = "agnks_grodno2"

        // Act (When)
        val params = GetStationDetail.Params(stationCode = stationCode)
        getStationDetail.invoke(params)

        // Assert (Then)
        coVerify { getStationDetail.invoke(any()) }
    }
}