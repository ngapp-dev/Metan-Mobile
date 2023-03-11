package com.ngapp.metanmobile.data.model.dto.contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactsDto(
    var id: Int,
    var dateCreated: Long,
    var content: String?,
    var isSearchable: Int,
) : Parcelable {
    companion object {
        fun init() = ContactsDto(
            id = 1,
            dateCreated = System.currentTimeMillis(),
            content = "Lorem Ipsum Lorem Ipsum Lorem Ipsum",
            isSearchable = 1,
        )
    }
}