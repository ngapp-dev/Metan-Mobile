package com.ngapp.metanmobile.domain.usecase.contacts

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.paging.PagingConfig
import com.ngapp.framework.network.DataState
import com.ngapp.framework.network.apiCall
import com.ngapp.framework.usecase.DataStateUseCase
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.data.model.dto.contacts.ContactsDto
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.repository.contacts.ContactsRepository
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import kotlinx.coroutines.flow.FlowCollector
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType

class GetContacts @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val contactsRepo: ContactsRepository
) : DataStateUseCase<Unit, List<ContactsDto>>() {

    override suspend fun FlowCollector<DataState<List<ContactsDto>>>.execute(params: Unit) {

        val getContactsList = contactsRepo.getContactsList()

        val service = apiCall { getContactsList }
        emit(service)
    }
}