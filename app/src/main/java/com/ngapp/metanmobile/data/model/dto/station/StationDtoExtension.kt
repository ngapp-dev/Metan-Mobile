 package com.ngapp.metanmobile.data.model.dto.station

import com.ngapp.metanmobile.app.extension.formatDateStringToUnix
import com.ngapp.metanmobile.data.model.local.station.StationEntity
import com.ngapp.metanmobile.data.parsing.CategoryValues
import com.prof.rssparser.Article

fun Article.toStationDto() = StationDto(
    id = categories[CategoryValues.CATEGORY_ID].toInt(),
    code = categories[CategoryValues.CATEGORY_CODE],
    previewPicture = categories[CategoryValues.CATEGORY_PREVIEW_PICTURE],
    detailPicture = categories[CategoryValues.CATEGORY_DETAIL_PICTURE],
    isActive = if (categories[CategoryValues.CATEGORY_ACTIVE] == "active") 1 else 0,
    isOperate = if (categories[CategoryValues.CATEGORY_OPERATE] == "Работает") 1 else 0,
    type = categories[CategoryValues.CATEGORY_TYPE],
    address = categories[CategoryValues.CATEGORY_ADDRESS],
    region = categories[CategoryValues.CATEGORY_REGION],
    phone = categories[CategoryValues.CATEGORY_PHONE],
    service = categories[CategoryValues.CATEGORY_SERVICE],
    workingTime = categories[CategoryValues.CATEGORY_WORKING_TIME],
    payment = categories[CategoryValues.CATEGORY_PAYMENT],
    latitude = categories[CategoryValues.CATEGORY_COORDINATE_TAGS].substringAfter(","),
    longitude = categories[CategoryValues.CATEGORY_COORDINATE_TAGS].substringBefore(","),
    busyOnMonday = categories[CategoryValues.CATEGORY_MONDAY],
    busyOnTuesday = categories[CategoryValues.CATEGORY_TUESDAY],
    busyOnWednesday = categories[CategoryValues.CATEGORY_WEDNESDAY],
    busyOnThursday = categories[CategoryValues.CATEGORY_THURSDAY],
    busyOnFriday = categories[CategoryValues.CATEGORY_FRIDAY],
    busyOnSaturday = categories[CategoryValues.CATEGORY_SATURDAY],
    busyOnSunday = categories[CategoryValues.CATEGORY_SUNDAY],
    googleTag = categories[CategoryValues.CATEGORY_GOOGLE_TAG],
    googleMapsTag = categories[CategoryValues.CATEGORY_GOOGLE_MAPS_TAG],
    yandexTag = categories[CategoryValues.CATEGORY_YANDEX_TAG],
    title = title ?: "",
    dateCreated = formatDateStringToUnix(pubDate),
    url = link
)

fun List<Article>.toStationDtoList() = map { it.toStationDto() }

fun StationEntity.toStationDto() = StationDto(
    id = id,
    code = code,
    previewPicture = previewPicture,
    detailPicture = detailPicture,
    isActive = isActive,
    isOperate = isOperate,
    type = type,
    address = address,
    region = region,
    phone = phone,
    service = service,
    workingTime = workingTime,
    payment = payment,
    latitude = latitude,
    longitude = longitude,
    busyOnMonday = busyOnMonday,
    busyOnTuesday = busyOnTuesday,
    busyOnWednesday = busyOnWednesday,
    busyOnThursday = busyOnThursday,
    busyOnFriday = busyOnFriday,
    busyOnSaturday = busyOnSaturday,
    busyOnSunday = busyOnSunday,
    googleTag = googleTag,
    googleMapsTag = googleMapsTag,
    yandexTag = yandexTag,
    title = title,
    dateCreated = dateCreated,
    url = url
)

fun List<StationEntity>.toFavoriteDtoList() = map { it.toStationDto() }

fun StationDto.toStationEntity() = StationEntity(
    id = id,
    code = code,
    previewPicture = previewPicture,
    detailPicture = detailPicture,
    isActive = isActive,
    isOperate = isOperate,
    type = type,
    address = address,
    region = region,
    phone = phone,
    service = service,
    workingTime = workingTime,
    payment = payment,
    latitude = latitude,
    longitude = longitude,
    busyOnMonday = busyOnMonday,
    busyOnTuesday = busyOnTuesday,
    busyOnWednesday = busyOnWednesday,
    busyOnThursday = busyOnThursday,
    busyOnFriday = busyOnFriday,
    busyOnSaturday = busyOnSaturday,
    busyOnSunday = busyOnSunday,
    googleTag = googleTag,
    googleMapsTag = googleMapsTag,
    yandexTag = yandexTag,
    title = title,
    dateCreated = dateCreated,
    url = url
)

