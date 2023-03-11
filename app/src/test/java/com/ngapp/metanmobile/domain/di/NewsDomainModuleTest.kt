package com.ngapp.metanmobile.domain.di

import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class NewsDomainModuleTest : MockkUnitTest() {
    private lateinit var module: NewsDomainModule

    override fun onCreate() {
        super.onCreate()
        module = NewsDomainModule()
    }

    @Test
    fun verifyProvideGetNews() {
        val repository = mockk<NewsRepository>()
        val getNews = module.provideGetNewsList(repository)

        Assert.assertEquals(repository, getNews.newsRepo)
    }

    @Test
    fun verifyProvideGetNewsDetail() {
        val repository = mockk<NewsRepository>()
        val getNewsDetail = module.provideGetNewsDetail(repository)

        Assert.assertEquals(repository, getNewsDetail.newsRepo)
    }
}