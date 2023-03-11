package com.ngapp.metanmobile.presentation.home

import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Assert
import org.junit.Test

class HomeContractTest {
    private lateinit var event: HomeEvent

    private lateinit var state: HomeViewState

    @Test
    fun verifyEventLoadHome() {
        val profileId = 1
        event = HomeEvent.LoadHome(profileId)

        val eventLoadDetail = event as HomeEvent.LoadHome
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyStateHome() {
        val newsData: List<NewsDto> = emptyList()
        val faqData: Flow<List<FaqDto>> = emptyFlow()
        val profileData: Flow<ProfileDto?> = emptyFlow()
        val stationsData: Flow<List<StationDto>> = emptyFlow()

        state = HomeViewState(newsData, faqData, profileData, stationsData)

        Assert.assertEquals(newsData, state.newsData)
        Assert.assertEquals(faqData, state.faqData)
        Assert.assertEquals(profileData, state.profileData)
        Assert.assertEquals(stationsData, state.stationsData)
    }
}