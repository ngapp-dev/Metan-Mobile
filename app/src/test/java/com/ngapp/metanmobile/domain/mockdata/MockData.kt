package com.ngapp.metanmobile.domain.mockdata

import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.remote.base.Status

object MockData {

    fun getNewsDto(): NewsDto {
        return NewsDto.init()
    }

    fun getNewsDtoList(): List<NewsDto> {
        return listOf(
            NewsDto.init()
        )
    }

    fun getProfileDto(): ProfileDto {
        return ProfileDto.init()
    }

    fun getFaqDto(): FaqDto {
        return FaqDto.init()
    }

    fun getFaqDtoList(): List<FaqDto> {
        return listOf(
            FaqDto.init()
        )
    }

    fun getStationDto(): StationDto {
        return StationDto.init()
    }

    fun getStationDtoList(): List<StationDto> {
        return listOf(
            StationDto.init()
        )
    }

    fun getLocationDto(): LocationDto {
        return LocationDto.init()
    }

    fun getLocationDtoList(): List<LocationDto> {
        return listOf(
            LocationDto.init()
        )
    }
}