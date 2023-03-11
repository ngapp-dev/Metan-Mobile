package com.ngapp.metanmobile.presentation.news.detail

import com.ngapp.metanmobile.domain.mockdata.MockData
import org.junit.Assert
import org.junit.Test

class NewsDetailContractTest {
    private lateinit var event: NewsDetailEvent

    private lateinit var state: NewsDetailViewState

    @Test
    fun verifyEventLoadDetail() {
        val id = 1
        event = NewsDetailEvent.LoadDetail(id)

        val eventLoadDetail = event as NewsDetailEvent.LoadDetail
        Assert.assertEquals(id, eventLoadDetail.id)
    }

    @Test
    fun verifyState() {
        val dto = MockData.getNewsDto()
        state = NewsDetailViewState(dto)

        Assert.assertEquals(dto, state.news)
    }
}