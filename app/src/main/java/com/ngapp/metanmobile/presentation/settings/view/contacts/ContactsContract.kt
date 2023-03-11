package com.ngapp.metanmobile.presentation.settings.view.contacts

import com.ngapp.metanmobile.data.model.dto.contacts.ContactsDto
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ContactsViewState(
    val contactsData: List<ContactsDto> = emptyList()
)

sealed class ContactsEvent {
    object LoadContacts : ContactsEvent()
}