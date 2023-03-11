 package com.ngapp.metanmobile.data.model.dto.faq

import com.ngapp.metanmobile.app.extension.formatDateStringToUnix
import com.ngapp.metanmobile.data.parsing.CategoryValues
import com.prof.rssparser.Article

fun Article.toFaqDto() = FaqDto(
    id = categories[CategoryValues.CATEGORY_ID].toInt(),
    code = categories[CategoryValues.CATEGORY_CODE],
    isPinned = if (categories[CategoryValues.CATEGORY_PINNED] == "pinned") 1 else 0,
    title = title,
    dateCreated = formatDateStringToUnix(pubDate),
    content = content
)

fun List<Article>.toFaqDtoList() = map { it.toFaqDto() }

