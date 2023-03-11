package com.ngapp.metanmobile.data.repository.news

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.news.toNewsDtoList
import com.prof.rssparser.Parser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NewsRepository
@Inject
constructor(
    @ApplicationContext val context: Context,
) {

    private val parser = Parser.Builder().context(context).build()

    suspend fun shareNews(newsDto: NewsDto?) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, newsDto?.title + context.getString(R.string.text_share_via) + context.getString(R.string.app_name))
            putExtra(
                Intent.EXTRA_TEXT, newsDto?.title + "\n${newsDto?.description}" + "\n${newsDto?.url}"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(shareIntent)
    }

    suspend fun getNewsList(): List<NewsDto> {
        val channel = parser.getChannel(URL_NEWS)

        val response = channel.articles
        val newsList: List<NewsDto> = response.toNewsDtoList()
        return newsList
    }

    suspend fun getNews(newsId: Int): NewsDto {
        val channel = parser.getChannel(URL_NEWS)

        val response = channel.articles
        val newsList = response.toNewsDtoList()
        return newsList.find { it.id == newsId } ?: NewsDto.init()
    }

    companion object {
        private const val URL_NEWS: String = "https://metan.by/news/rss/"
    }
}