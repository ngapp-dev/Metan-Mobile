package com.ngapp.metanmobile.presentation.news.list

import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import org.junit.Assert
import org.junit.Test

class NewsContractTest {
    private lateinit var event: NewsEvent

    private lateinit var state: NewsViewState

    @Test
    fun verifyEventLoadNews() {
        event = NewsEvent.LoadNews

        val eventLoadDetail = event as NewsEvent.LoadNews
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyState() {
        val newsData: List<NewsDto> = emptyList()
        state = NewsViewState(newsData, emptyList())

        Assert.assertEquals(newsData, state.newsData)
        Assert.assertEquals(0, state.favorList.size)
    }
}