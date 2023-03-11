package com.ngapp.metanmobile.data.model.dto

import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import org.junit.Assert
import org.junit.Test

class FaqDtoTest {

    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val title = "Сколько стоит метан на заправках в Беларуси?"
        val content = "Цена метана на заправках в Беларуси 1.06 руб. за м3."

        val dto = FaqDto(
            id = id,
            code = "",
            isPinned = 0,
            title = title,
            dateCreated = System.currentTimeMillis(),
            content = content,
        )

        Assert.assertEquals(id, dto.id)
        Assert.assertEquals(title, dto.title)
        Assert.assertEquals(content, dto.content)
    }
}