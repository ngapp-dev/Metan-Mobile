package com.ngapp.metanmobile.data.model.dto.faq

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FaqDto(
    var id: Int,
    var code: String = "",
    var isPinned: Int,
    var title: String?,
    var dateCreated: Long,
    var content: String?,
) : Parcelable {
    companion object {
        fun init() = FaqDto(
            id = 1,
            code = "",
            isPinned = 0,
            title = "Сколько стоит метан на заправках в Беларуси?",
            dateCreated = System.currentTimeMillis(),
            content = "Цена метана на заправках в Беларуси 1.06 руб. за м3.",
        )
    }
}