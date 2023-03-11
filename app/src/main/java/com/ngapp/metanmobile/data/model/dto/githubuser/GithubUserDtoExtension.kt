package com.ngapp.metanmobile.data.model.dto.githubuser

import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.data.remote.githubuser.GithubUserInfo

fun GithubUserInfo.toGitHubUserDto() = GithubUserDto(
    id = id.orZero(),
    login = login.orEmpty(),
    avatarUrl = avatarUrl,
    url = url.orEmpty(),
    htmlUrl = htmlUrl.orEmpty(),
    name = name,
    company = company,
    blog = blog,
    location = location,
    email = email,
    bio = bio,
    twitterUsername = twitterUsername
)