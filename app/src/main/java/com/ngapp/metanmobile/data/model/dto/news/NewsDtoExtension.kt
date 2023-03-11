package com.ngapp.metanmobile.data.model.dto.news

import com.ngapp.metanmobile.app.extension.formatDateStringToUnix
import com.ngapp.metanmobile.data.parsing.CategoryValues
import com.prof.rssparser.Article

fun Article.toNewsDto(): NewsDto {
    val contentImage =
        this.content?.substringAfter("src=\"")?.substringBefore(".jpg")

    return NewsDto(
        id = categories[CategoryValues.CATEGORY_ID].toInt(),
        code = categories[CategoryValues.CATEGORY_CODE],
        isPinned = if (categories[CategoryValues.CATEGORY_PINNED] == "pinned") 1 else 0,
        previewPicture = categories[CategoryValues.CATEGORY_PREVIEW_PICTURE],
        detailPicture = categories[CategoryValues.CATEGORY_DETAIL_PICTURE].ifEmpty { "https://metan.by$contentImage.jpg" },
        isActive = if (categories[CategoryValues.CATEGORY_ACTIVE] == "active") 1 else 0,
        isOperate = if (categories[CategoryValues.CATEGORY_OPERATE] == "Работает") 1 else 0,
        labelNews = categories[CategoryValues.CATEGORY_LABEL_NEWS],
        title = title ?: "",
        dateCreated = formatDateStringToUnix(pubDate),
        description = description,
        content = content,
        url = link,
        readStatus = 0,
        isSearchable = 1
    )
}

fun List<Article>.toNewsDtoList() = map { it.toNewsDto() }

