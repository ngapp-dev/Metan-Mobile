package com.ngapp.metanmobile.data.model.dto.profile

import com.ngapp.metanmobile.data.model.local.profile.ProfileEntity

fun ProfileEntity.toProfileDto() = ProfileDto(
    id = id,
    name = name,
    email = email,
    password = password,
    checkword = checkword,
    nickname = nickname,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    profileImage = profileImage,
    bio = bio,
    location = location,
    createDate = createDate,
    modDate = modDate,
    isActive = isActive,
    lastLogin = lastLogin,
    permissionLevel = permissionLevel
)

fun List<ProfileEntity>.toProfileDtoList() = map { it.toProfileDto() }

fun ProfileDto.toProfileEntity() = ProfileEntity(
    id = id,
    name = name,
    email = email,
    password = password,
    checkword = checkword,
    nickname = nickname,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    profileImage = profileImage,
    bio = bio,
    location = location,
    createDate = createDate,
    modDate = modDate,
    isActive = isActive,
    lastLogin = lastLogin,
    permissionLevel = permissionLevel
)


