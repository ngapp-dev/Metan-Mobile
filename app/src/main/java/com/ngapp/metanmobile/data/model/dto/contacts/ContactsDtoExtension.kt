package com.ngapp.metanmobile.data.model.dto.contacts

import com.ngapp.metanmobile.app.extension.formatDateStringToUnix
import com.ngapp.metanmobile.data.parsing.CategoryValues
import com.prof.rssparser.Article

fun Article.toContactsDto(): ContactsDto {

    return ContactsDto(
        id = categories[CategoryValues.CATEGORY_ID].toInt(),
        dateCreated = formatDateStringToUnix(pubDate),
        content = content,
        isSearchable = 1
    )
}

fun List<Article>.toContactsDtoList() = map { it.toContactsDto() }

