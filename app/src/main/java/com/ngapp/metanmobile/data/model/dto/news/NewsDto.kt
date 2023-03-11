package com.ngapp.metanmobile.data.model.dto.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDto(
    var id: Int,
    var code: String,
    var isPinned: Int,
    var previewPicture: String?,
    var detailPicture: String?,
    var isActive: Int,
    var isOperate: Int,
    var labelNews: String?,
    var title: String,
    var dateCreated: Long,
    var description: String?,
    var content: String?,
    var url: String?,
    var readStatus: Int,
    var isSearchable: Int,
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = NewsDto(
            id = 1,
            code = "",
            isPinned = 0,
            previewPicture = null,
            detailPicture = null,
            isActive = 1,
            isOperate = 1,
            labelNews = "",
            title = "Не возможно загрузить новые компрессоры на АГНКС Гродно-2",
            dateCreated = System.currentTimeMillis(),
            description = "Lorem Ipsum",
            content = "Lorem Ipsum Lorem Ipsum Lorem Ipsum",
            url = "",
            readStatus = 0,
            isSearchable = 1,
            isFavorite = false
        )
    }
}