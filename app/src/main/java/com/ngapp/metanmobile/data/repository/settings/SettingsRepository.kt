package com.ngapp.metanmobile.data.repository.settings

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.model.dto.faq.toFaqDtoList
import com.ngapp.metanmobile.data.remote.service.GithubService
import com.prof.rssparser.Parser
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val service: GithubService,
    context: Context
) {

    private val parser = Parser.Builder().context(context).build()

    suspend fun getFaqList(): List<FaqDto> {
        val channel = parser.getChannel(URL_FAQ)

        val response = channel.articles
        return response.toFaqDtoList()
    }

    suspend fun getGithubUser() = service.getGithubUser()

    companion object {
        private const val URL_FAQ: String = "https://metan.by/faq/rss/"
    }
}