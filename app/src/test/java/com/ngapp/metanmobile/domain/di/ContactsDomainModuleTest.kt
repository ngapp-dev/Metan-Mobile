package com.ngapp.metanmobile.domain.di

import com.ngapp.metanmobile.data.repository.contacts.ContactsRepository
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ContactsDomainModuleTest : MockkUnitTest() {
    private lateinit var module: ContactsDomainModule

    override fun onCreate() {
        super.onCreate()
        module = ContactsDomainModule()
    }

    @Test
    fun verifyProvideGetContacts() {
        val repository = mockk<ContactsRepository>()
        val getContacts = module.provideGetContacts(repository)

        Assert.assertEquals(repository, getContacts.contactsRepo)
    }
}