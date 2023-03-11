package com.ngapp.metanmobile.presentation.settings.view.contacts

import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.domain.usecase.contacts.GetContacts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContacts: GetContacts
) : MviViewModel<BaseViewState<ContactsViewState>, ContactsEvent>() {

    override fun onTriggerEvent(eventType: ContactsEvent) {
        when (eventType) {
            is ContactsEvent.LoadContacts -> onLoadContacts()
        }
    }

    private fun onLoadContacts() = safeLaunch {
        setState(BaseViewState.Loading)
        execute(getContacts(Unit)) { ContactsDto ->
            setState(BaseViewState.Data(ContactsViewState(contactsData = ContactsDto)))
        }

    }
}