package com.ngapp.metanmobile.data.repository.contacts

import android.content.Context
import com.ngapp.metanmobile.data.model.dto.contacts.ContactsDto
import com.ngapp.metanmobile.data.model.dto.contacts.toContactsDtoList
import com.prof.rssparser.Parser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContactsRepository
@Inject
constructor(
    @ApplicationContext val context: Context,
) {

    private val parser = Parser.Builder().context(context).build()

    suspend fun getContactsList(): List<ContactsDto> {
        val channel = parser.getChannel(URL_NEWS)

        val response = channel.articles
        val contactsList: List<ContactsDto> = response.toContactsDtoList()
        return contactsList
    }

    companion object {
        private const val URL_NEWS: String = "https://metan.by/contacts/rss/"
    }
}