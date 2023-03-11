package com.ngapp.metanmobile.data.model.dto

import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import org.junit.Assert
import org.junit.Test

class NewsDtoTest {

    @Test
    fun checkCorrectAttributes() {
        val newsId = 9914
        val previewPicture = "https://metan.by/upload/iblock/903/9035d3975a6da118d7084cf52c8ca0ed.jpg"
        val detailPicture = "https://metan.by/upload/medialibrary/098/09837965dfd480a611785a8eed5ecf8f.jpg"

        val dto = NewsDto(
            id = 9914,
            code = "",
            isPinned = 0,
            previewPicture = previewPicture,
            detailPicture = detailPicture,
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

        Assert.assertEquals(newsId, dto.id)
        Assert.assertEquals(previewPicture, dto.previewPicture)
        Assert.assertEquals(detailPicture, dto.detailPicture)
    }
}