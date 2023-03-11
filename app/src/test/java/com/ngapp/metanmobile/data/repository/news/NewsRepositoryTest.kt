package com.ngapp.metanmobile.data.repository.news

import android.content.Context
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.news.toNewsDtoList
import com.ngapp.testutils.MockkUnitTest
import com.prof.rssparser.Parser
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var context: Context

    private lateinit var repository: NewsRepository

    override fun onCreate() {
        super.onCreate()
        repository = NewsRepository(context)
    }

    @Test
    fun getNewsList() = runTest {
        // Given
        val parser = Parser.Builder().context(context).build()
        val channel = parser.getChannel("https://metan.by/news/rss/")

        // When
        repository.getNewsList()

        // Then
        coVerify {
            val response = channel.articles
            val newsList: List<NewsDto> = response.toNewsDtoList()
        }
    }

    @Test
    fun getNews() = runTest {
        // Given
        val parser = Parser.Builder().context(context).build()
        val channel = parser.getChannel("https://metan.by/news/rss/")

        val newsId = 1
        val id = slot<Int>()


        // When
        repository.getNews(newsId)

        // Then
        coVerify {
            val response = channel.articles
            val newsList = response.toNewsDtoList()
            newsList.find { it.id == newsId } ?: NewsDto.init()
        }

        assertEquals(newsId, id.captured)
    }
}